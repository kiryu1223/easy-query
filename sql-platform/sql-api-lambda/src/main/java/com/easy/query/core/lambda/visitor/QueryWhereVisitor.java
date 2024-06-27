package com.easy.query.core.lambda.visitor;

import com.easy.query.api.lambda.sqlext.SqlFunctions;
import com.easy.query.core.expression.parser.core.base.WherePredicate;
import com.easy.query.core.lambda.visitor.context.*;
import io.github.kiryu1223.expressionTree.expressions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

import static com.easy.query.core.lambda.util.ExpressionUtil.hasParameter;
import static com.easy.query.core.lambda.util.ExpressionUtil.isVoid;
import static com.easy.query.core.lambda.util.SqlUtil.fieldName;

public class QueryWhereVisitor extends Visitor
{
    private final List<WherePredicate<?>> wherePredicates;

    public QueryWhereVisitor(List<WherePredicate<?>> wherePredicates)
    {
        this.wherePredicates = wherePredicates;
    }

    @Override
    public void visit(LambdaExpression<?> lambdaExpression)
    {
        List<ParameterExpression> parameters = lambdaExpression.getParameters();
        Expression body = lambdaExpression.getBody();
        SqlContext round = round(body, parameters);
        round.revWhere(wherePredicates.get(0));
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
                if (!isVoid(callMethod.getReturnType()))
                {
                    int index = parameters.indexOf(parameter);
                    return new SqlPropertyContext(fieldName(callMethod), wherePredicates.get(index));
                }
                else
                {
                    throw new RuntimeException();
                }
            }
            else if (Collection.class.isAssignableFrom(callMethod.getDeclaringClass()))
            {
                if (callMethod.getName().equals("contains"))
                {
                    Expression p = methodCallExpression.getArgs().get(0);
                    Expression collection = methodCallExpression.getExpr();
                    return new SqlBinaryContext(SqlOperator.IN,round(p,parameters),round(collection,parameters));
                }
                else
                {
                    throw new RuntimeException(expression.toString());
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
                if (hasParameter(fieldSelectExpression)) throw new RuntimeException();
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
            return new SqlBinaryContext(javaOperatortoSqlOperator(operatorType), left, right);
        }
        else if (expression instanceof ParensExpression)
        {
            ParensExpression parensExpression = (ParensExpression) expression;
            SqlContext round = round(parensExpression.getExpr(), parameters);
            return new SqlParensContext(round);
        }
        else if (expression instanceof UnaryExpression)
        {
            UnaryExpression unaryExpression = (UnaryExpression) expression;
            return new SqlUnaryContext(javaOperatortoSqlOperator(unaryExpression.getOperatorType()), round(unaryExpression.getOperand(), parameters));
        }
        throw new RuntimeException("不支持的表达式: " + expression.getKind() + " " + expression);
    }

    private SqlOperator javaOperatortoSqlOperator(OperatorType type)
    {
        switch (type)
        {
            case NOT:
                return SqlOperator.NOT;
            case OR:
                return SqlOperator.OR;
            case AND:
                return SqlOperator.AND;
            case EQ:
                return SqlOperator.EQ;
            case NE:
                return SqlOperator.NE;
            case LT:
                return SqlOperator.LT;
            case GT:
                return SqlOperator.GT;
            case LE:
                return SqlOperator.LE;
            case GE:
                return SqlOperator.GE;
            case PLUS:
                return SqlOperator.PLUS;
            case MINUS:
                return SqlOperator.MINUS;
            case MUL:
                return SqlOperator.MUL;
            case DIV:
                return SqlOperator.DIV;
            default:
                throw new RuntimeException("不支持的sql运算符:" + type);
        }
    }
}
