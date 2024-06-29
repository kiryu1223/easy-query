package com.easy.query.core.lambda.visitor;

import com.easy.query.api.lambda.crud.read.QueryData;
import com.easy.query.api.lambda.crud.read.group.IGroup;
import com.easy.query.core.expression.parser.core.base.ColumnAsSelector;
import com.easy.query.core.func.SQLFunc;
import com.easy.query.core.lambda.visitor.context.SqlContext;
import com.easy.query.core.lambda.visitor.context.SqlFuncContext;
import com.easy.query.core.lambda.visitor.context.SqlPropertyContext;
import io.github.kiryu1223.expressionTree.expressions.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static com.easy.query.core.lambda.util.ExpressionUtil.isGroupKey;
import static com.easy.query.core.lambda.util.SqlUtil.fieldName;

public class SelectVisitorV2 extends BaseVisitorV2
{
    private final List<ParameterExpression> parameters;
    private final List<ColumnAsSelector<?, ?>> columnAsSelectors;
    private final ColumnAsSelector<?, ?> columnAsSelector;
    private final SQLFunc fx;
    private final QueryData queryData;
    private ParameterExpression cur;

    public SelectVisitorV2(List<ParameterExpression> parameters, List<ColumnAsSelector<?, ?>> columnAsSelectors, QueryData queryData)
    {
        super(columnAsSelectors);
        this.parameters = parameters;
        this.columnAsSelectors = columnAsSelectors;
        this.fx = columnAsSelectors.get(0).fx();
        this.queryData = queryData;
        this.columnAsSelector = columnAsSelectors.get(0);
    }

    @Override
    public void visit(NewExpression newExpression)
    {
        BlockExpression classBody = newExpression.getClassBody();
        if (classBody == null) return;
        for (Expression expression : classBody.getExpressions())
        {
            if (expression.getKind() == Kind.Variable)
            {
                VariableExpression variable = (VariableExpression) expression;
                Expression init = variable.getInit();
                setValueOrGroup(init,variable.getName());
            }
        }
    }

    @Override
    public void visit(BlockExpression block)
    {
        for (Expression expression : block.getExpressions())
        {
            if (expression.getKind() == Kind.Variable)
            {
                VariableExpression var = (VariableExpression) expression;
                cur = var.getParameter();
            }
            else if (expression.getKind() == Kind.MethodCall)
            {
                MethodCallExpression methodCall = (MethodCallExpression) expression;
                if (methodCall.getExpr().getKind() == Kind.Parameter && methodCall.getExpr().equals(cur)
                        && methodCall.getMethod().getParameterCount() == 1)
                {
                    Expression arg0 = methodCall.getArgs().get(0);
                    setValueOrGroup(arg0,fieldName(methodCall.getMethod()));
                }
            }
        }
    }

    @Override
    public void visit(MethodCallExpression methodCall)
    {
        setValueOrGroup(methodCall,"");
    }

    @Override
    public void visit(FieldSelectExpression fieldSelect)
    {
        SqlContext context = round(fieldSelect, parameters);
        setValueOrGroup(fieldSelect,"");
    }

    @Override
    public void visit(ParameterExpression parameter)
    {
        if (parameters.contains(parameter))
        {
            int index = parameters.indexOf(parameter);
            ColumnAsSelector<?, ?> column = columnAsSelectors.get(index);
            column.columnAll();
        }
    }

    private void selectValue(String property, SqlContext context, SQLFunc fx)
    {
        if (context instanceof SqlPropertyContext)
        {
            SqlPropertyContext propertyContext = (SqlPropertyContext) context;
            ColumnAsSelector<?, ?> column = (ColumnAsSelector<?, ?>) propertyContext.getTableOwner();
            if (property.isEmpty() || property.equals(propertyContext.getProperty()))
            {
                column.column(propertyContext.getProperty());
            }
            else
            {
                column.columnAs(propertyContext.getProperty(), property);
            }
        }
        else if (context instanceof SqlFuncContext)
        {
            SqlFuncContext sqlFuncContext = (SqlFuncContext) context;
            ColumnAsSelector<?, ?> columnAsSelector = columnAsSelectors.get(0);
            if (property.isEmpty())
            {
                columnAsSelector.sqlFunc(sqlFuncContext.getFunction(fx));
            }
            else
            {
                columnAsSelector.sqlFuncAs(sqlFuncContext.getFunction(fx), property);
            }
        }
        else
        {
            throw new RuntimeException("不支持的context类型: " + context);
        }
    }

    private void setValueOrGroup(Expression expression,String asName)
    {
        if (expression instanceof FieldSelectExpression)
        {
            FieldSelectExpression fieldSelect = (FieldSelectExpression) expression;
            Field field = fieldSelect.getField();
            if (isGroupKey(parameters, fieldSelect.getExpr()))
            {
                Map<String, Integer> indexMap = queryData.getIndexMap();
                columnAsSelector.groupKeysAs(indexMap.get(field.getName()), asName);
            }
            else if (IGroup.class.isAssignableFrom(field.getDeclaringClass())
                    && field.getName().equals("key"))
            {
                Map<String, Integer> indexMap = queryData.getIndexMap();
                columnAsSelector.groupKeysAs(0, asName);
            }
            else
            {
                SqlContext context = round(expression, parameters);
                selectValue(asName, context, fx);
            }
        }
        else
        {
            SqlContext context = round(expression, parameters);
            selectValue(asName, context, fx);
        }
    }
}
