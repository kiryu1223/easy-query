package com.easy.query.core.lambda.visitor.context;

import com.easy.query.core.basic.api.select.ClientQueryable;
import com.easy.query.core.expression.lambda.SQLExpression1;
import com.easy.query.core.expression.parser.core.base.WherePredicate;
import com.easy.query.core.func.SQLFunc;
import io.github.kiryu1223.expressionTree.expressions.OperatorType;

public class SqlBinaryContext extends SqlContext // implements IRevQueryableWhere
{
    private final OperatorType operatorType;
    private final SqlContext left;
    private final SqlContext right;

    public SqlBinaryContext(OperatorType operatorType, SqlContext left, SqlContext right)
    {
        this.operatorType = operatorType;
        this.left = left;
        this.right = right;
    }

    public OperatorType getOperatorType()
    {
        return operatorType;
    }

    public SqlContext getLeft()
    {
        return left;
    }

    public SqlContext getRight()
    {
        return right;
    }


}
