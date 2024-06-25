package com.easy.query.core.lambda.visitor.context;

import io.github.kiryu1223.expressionTree.expressions.OperatorType;

public class SqlUnaryContext extends SqlContext
{
    private final OperatorType operatorType;
    private final SqlContext context;

    public SqlUnaryContext(OperatorType operatorType, SqlContext context)
    {
        this.operatorType = operatorType;
        this.context = context;
    }

    public OperatorType getOperatorType()
    {
        return operatorType;
    }

    public SqlContext getContext()
    {
        return context;
    }
}
