package com.easy.query.core.lambda.visitor.context;

import com.easy.query.core.expression.parser.core.base.WherePredicate;
import com.easy.query.core.func.SQLFunc;
import com.easy.query.core.func.SQLFunction;

public class SqlUnaryContext extends SqlContext
{
    private final SqlOperator operatorType;
    private final SqlContext context;

    public SqlUnaryContext(SqlOperator operatorType, SqlContext context)
    {
        this.operatorType = operatorType;
        this.context = context;
    }

    public SqlOperator getOperatorType()
    {
        return operatorType;
    }

    public SqlContext getContext()
    {
        return context;
    }

    @Override
    public void revWhere(WherePredicate<?> wherePredicate)
    {
        wherePredicate.sqlFunc(getFunction(wherePredicate.fx()));
    }


    public SQLFunction getFunction(SQLFunc fx)
    {
        if (operatorType == SqlOperator.NOT)
        {
            return fx.not(s -> roundSqlContext(context, s, fx));
        }
        else
        {
            throw new RuntimeException("不支持的一元运算符:" + operatorType);
        }
    }
}
