package com.easy.query.core.lambda.visitor;

import com.easy.query.api.lambda.sqlext.SqlFunctions;
import com.easy.query.core.expression.builder.Filter;
import com.easy.query.core.expression.parser.core.base.WherePredicate;
import com.easy.query.core.func.SQLFunc;
import com.easy.query.core.func.SQLFunction;
import com.easy.query.core.lambda.visitor.context.*;
import io.github.kiryu1223.expressionTree.expressions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.List;

import static com.easy.query.core.lambda.util.ExpressionUtil.*;
import static com.easy.query.core.lambda.util.SqlUtil.fieldName;

public class WhereVisitorV2 extends BaseVisitorV2
{
    private List<ParameterExpression> parameters;

    private final List<WherePredicate<?>> wherePredicates;
    private final Filter filter;
    private final SQLFunc fx;

    private ArrayDeque<SqlContext> sqlContexts = new ArrayDeque<>();

    public WhereVisitorV2(List<WherePredicate<?>> wherePredicates)
    {
        this.wherePredicates = wherePredicates;
        filter = wherePredicates.get(0).getFilter();
        fx = wherePredicates.get(0).fx();
    }

    @Override
    public void visit(LambdaExpression<?> lambdaExpression)
    {
        parameters = lambdaExpression.getParameters();
        visit(lambdaExpression.getBody());
    }

    @Override
    public void visit(BinaryExpression binaryExpression)
    {
        OperatorType operatorType = binaryExpression.getOperatorType();
        Expression left = binaryExpression.getLeft();
        Expression right = binaryExpression.getRight();
        if (isAndorOr(operatorType))
        {
            visit(left);
            if (!sqlContexts.isEmpty())
            {
                SqlContext context = sqlContexts.poll();
                if (context instanceof SqlPropertyContext)
                {
                    SqlPropertyContext property = (SqlPropertyContext) context;
                    filter.eq(property.getTableOwner().getTable(), property.getProperty(), true);
                }
                else
                {
                    throw new RuntimeException();
                }
            }
            switch (operatorType)
            {
                case OR:
                    filter.or();
                    break;
                case AND:
                    filter.and();
                    break;
            }
            visit(right);
            if (!sqlContexts.isEmpty())
            {
                SqlContext context = sqlContexts.poll();
                if (context instanceof SqlPropertyContext)
                {
                    SqlPropertyContext property = (SqlPropertyContext) context;
                    filter.eq(property.getTableOwner().getTable(), property.getProperty(), true);
                }
                else
                {
                    throw new RuntimeException();
                }
            }
        }
        else if (isCompareOperator(operatorType))
        {
            visit(left);
            visit(right);
            SqlContext leftContext = sqlContexts.poll();
            SqlContext rightContext = sqlContexts.poll();
            if (leftContext instanceof SqlPropertyContext) // 左侧是字段
            {
                SqlPropertyContext leftProperty = (SqlPropertyContext) leftContext;
                if (rightContext instanceof SqlValueContext)// 右侧是值
                {
                    SqlValueContext sqlValueContext = (SqlValueContext) rightContext;
                    comparePropertyAndValue(filter, leftProperty.getTableOwner(), leftProperty.getProperty(), sqlValueContext.getValue(), operatorType);
                }
                else if (rightContext instanceof SqlPropertyContext)// 右侧是字段
                {
                    SqlPropertyContext rightProperty = (SqlPropertyContext) rightContext;
                    comparePropertyAndProperty(filter, leftProperty.getTableOwner(), leftProperty.getProperty(), rightProperty.getTableOwner(), rightProperty.getProperty(), operatorType);
                }
                else if (rightContext instanceof SqlFuncContext)// 右侧是函数
                {
                    SqlFuncContext sqlFuncContext = (SqlFuncContext) rightContext;
                    SQLFunction function = sqlFuncContext.getFunction(fx);
                    comparePropertyAndFunc(filter, leftProperty.getTableOwner(), leftProperty.getProperty(), function, operatorType);
                }
            }
            else if (leftContext instanceof SqlValueContext)
            {
                SqlValueContext leftValue = (SqlValueContext) leftContext;
                if (rightContext instanceof SqlValueContext)
                {
                    SqlValueContext rightValue = (SqlValueContext) rightContext;
                    compareValueAndValue(filter, leftValue.getValue(), rightValue.getValue(), operatorType);
                }
                else if (rightContext instanceof SqlPropertyContext)
                {
                    SqlPropertyContext rightProperty = (SqlPropertyContext) rightContext;
                    comparePropertyAndValue(filter, rightProperty.getTableOwner(), rightProperty.getProperty(), leftValue.getValue(), revOp(operatorType));
                }
                else if (rightContext instanceof SqlFuncContext)
                {
                    SqlFuncContext sqlFuncContext = (SqlFuncContext) rightContext;
                    SQLFunction function = sqlFuncContext.getFunction(fx);
                    compareFuncAndValue(filter, wherePredicates.get(0), function, leftValue.getValue(), revOp(operatorType));
                }
            }
            else if (leftContext instanceof SqlFuncContext)
            {
                SqlFuncContext leftFunc = (SqlFuncContext) leftContext;
                if (rightContext instanceof SqlValueContext)
                {
                    SqlValueContext rightValue = (SqlValueContext) rightContext;
                    compareFuncAndValue(filter, wherePredicates.get(0), leftFunc.getFunction(fx), rightValue.getValue(), operatorType);
                }
                else if (rightContext instanceof SqlPropertyContext)
                {
                    SqlPropertyContext rightProperty = (SqlPropertyContext) rightContext;
                    compareFuncAndProperty(filter, wherePredicates.get(0), leftFunc.getFunction(fx), rightProperty.getTableOwner(), rightProperty.getProperty(), operatorType);
                }
                else if (rightContext instanceof SqlFuncContext)
                {
                    SqlFuncContext sqlFuncContext = (SqlFuncContext) rightContext;
                    SQLFunction function = sqlFuncContext.getFunction(fx);
                    compareFuncAndFunc(filter, wherePredicates.get(0), function, wherePredicates.get(0), function, operatorType);
                }
            }
        }
        else
        {
            throw new RuntimeException();
        }
    }

    @Override
    public void visit(MethodCallExpression methodCall)
    {
        if (methodCall.getExpr().getKind() == Kind.Parameter)
        {
            ParameterExpression parameter = (ParameterExpression) methodCall.getExpr();
            Method method = methodCall.getMethod();
            if (parameters.contains(parameter)||!isVoid(method.getReturnType()))
            {
                int index = parameters.indexOf(parameter);
                SqlPropertyContext propertyContext = new SqlPropertyContext(fieldName(method),wherePredicates.get(index));
                sqlContexts.offer(propertyContext);
            }
            else
            {
                throw new RuntimeException(methodCall.toString());
            }
        }
        else if (SqlFunctions.class.isAssignableFrom(methodCall.getMethod().getDeclaringClass()))
        {
            String name = methodCall.getMethod().getName();
        }
    }

    private SqlContext round(Expression expression, List<ParameterExpression> parameters)
    {
        if (expression instanceof MethodCallExpression)
        {
            MethodCallExpression methodCallExpression = (MethodCallExpression) expression;
            Method callMethod = methodCallExpression.getMethod();
            if (methodCallExpression.getExpr().getKind() == Kind.Parameter)
            {
                ParameterExpression parameter = (ParameterExpression) methodCallExpression.getExpr();
                if (parameters.contains(parameter) && !isVoid(callMethod.getReturnType()))
                {
                    int index = parameters.indexOf(parameter);
                    return new SqlPropertyContext(fieldName(callMethod), wherePredicates.get(index));
                }
                else
                {
                    throw new RuntimeException();
                }
            }
            else if (SqlFunctions.class.isAssignableFrom(callMethod.getDeclaringClass()))
            {
                SqlFuncContext sqlFuncContext = new SqlFuncContext(callMethod.getName());
                for (Expression arg : methodCallExpression.getArgs())
                {
                    sqlFuncContext.getArgs().add(round(arg, parameters));
                }
                return sqlFuncContext;
            }
            else
            {
                if (hasParameter(methodCallExpression)) throw new RuntimeException();
                Object value = methodCallExpression.getValue();
                return new SqlValueContext(value);
            }
        }
        else if (expression instanceof FieldSelectExpression)
        {
            FieldSelectExpression fieldSelectExpression = (FieldSelectExpression) expression;
            Field field = fieldSelectExpression.getField();
            if (fieldSelectExpression.getExpr().getKind() == Kind.Parameter)
            {
                ParameterExpression parameter = (ParameterExpression) fieldSelectExpression.getExpr();
                int index = parameters.indexOf(parameter);
                return new SqlPropertyContext(fieldName(field), wherePredicates.get(index));
            }
            else
            {
                return new SqlValueContext(fieldSelectExpression.getValue());
            }
        }
        else if (expression instanceof ReferenceExpression)
        {
            ReferenceExpression referenceExpression = (ReferenceExpression) expression;
            return new SqlValueContext(referenceExpression.getValue());
        }
        else if (expression instanceof ConstantExpression)
        {
            ConstantExpression constantExpression = (ConstantExpression) expression;
            return new SqlValueContext(constantExpression.getValue());
        }
        else if (expression instanceof StaticClassExpression)
        {
            StaticClassExpression staticClassExpression = (StaticClassExpression) expression;
            return new SqlValueContext(staticClassExpression.getType());
        }
        else if (expression instanceof BinaryExpression)
        {
            BinaryExpression binaryExpression = (BinaryExpression) expression;
            OperatorType operatorType = binaryExpression.getOperatorType();
            SqlContext left = round(binaryExpression.getLeft(), parameters);
            SqlContext right = round(binaryExpression.getRight(), parameters);
            return new SqlBinaryContext(operatorType, left, right);
        }
        throw new RuntimeException("不支持的表达式 " + expression.getClass().getSimpleName() + " " + expression);
    }
}
