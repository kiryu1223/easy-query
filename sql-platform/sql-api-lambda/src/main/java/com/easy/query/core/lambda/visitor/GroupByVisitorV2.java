package com.easy.query.core.lambda.visitor;

import com.easy.query.core.expression.parser.core.base.ColumnGroupSelector;
import com.easy.query.core.func.SQLFunc;
import com.easy.query.core.lambda.visitor.context.SqlContext;
import com.easy.query.core.lambda.visitor.context.SqlFuncContext;
import com.easy.query.core.lambda.visitor.context.SqlPropertyContext;
import io.github.kiryu1223.expressionTree.expressions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupByVisitorV2 extends BaseVisitorV2
{
    private final List<ParameterExpression> parameters;
    private final ColumnGroupSelector<?> columnGroupSelector;
    private final Map<String, Integer> indexMap = new HashMap<>();

    public Map<String, Integer> getIndexMap()
    {
        return indexMap;
    }

    public GroupByVisitorV2(List<ColumnGroupSelector<?>> owners, List<ParameterExpression> parameters)
    {
        super(owners);
        this.columnGroupSelector = owners.get(0);
        this.parameters = parameters;
    }

    @Override
    public void visit(NewExpression newExpression)
    {
        BlockExpression classBody = newExpression.getClassBody();
        if (classBody == null) return;
        int index = 0;
        for (Expression expression : classBody.getExpressions())
        {
            if (expression.getKind() == Kind.Variable)
            {
                VariableExpression variable = (VariableExpression) expression;
                SqlContext context = round(variable.getInit(), parameters);
                setGroup(context, columnGroupSelector.fx());
                indexMap.put(variable.getName(), index++);
            }
        }
    }

    @Override
    public void visit(MethodCallExpression methodCall)
    {
        if (methodCall.getExpr().getKind() == Kind.Parameter)
        {
            SqlContext context = round(methodCall, parameters);
            setGroup(context, columnGroupSelector.fx());
        }
    }

    @Override
    public void visit(FieldSelectExpression fieldSelect)
    {
        if (fieldSelect.getExpr().getKind() == Kind.Parameter)
        {
            SqlContext context = round(fieldSelect, parameters);
            setGroup(context, columnGroupSelector.fx());
        }
    }

    private void setGroup(SqlContext context, SQLFunc fx)
    {
        if (context instanceof SqlPropertyContext)
        {
            SqlPropertyContext propertyContext = (SqlPropertyContext) context;
            ColumnGroupSelector<?> columnGroupSelector = (ColumnGroupSelector<?>) propertyContext.getTableOwner();
            columnGroupSelector.column(propertyContext.getProperty());
        }
        else if (context instanceof SqlFuncContext)
        {
            SqlFuncContext funcContext = (SqlFuncContext) context;
            columnGroupSelector.sqlFunc(funcContext.getFunction(fx));
        }
        else
        {
            throw new RuntimeException("不支持的context类型: " + context);
        }
    }
}
