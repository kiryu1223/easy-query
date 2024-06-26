package com.easy.query.api.lambda.crud.read.group;

import com.easy.query.api.lambda.sqlext.SqlFunctionInvokeException;
import io.github.kiryu1223.expressionTree.delegate.Func8;
import io.github.kiryu1223.expressionTree.expressions.Expr;
import io.github.kiryu1223.expressionTree.expressions.ExprTree;

import java.math.BigDecimal;

public abstract class SqlAggregation8<T1, T2, T3, T4, T5, T6, T7, T8> implements IAggregation
{
    public <R> long count(Func8<T1, T2, T3, T4, T5, T6, T7, T8, R> expr)
    {
        throw new SqlFunctionInvokeException();
    }

    public <R> BigDecimal sum(Func8<T1, T2, T3, T4, T5, T6, T7, T8, R> expr)
    {
        throw new SqlFunctionInvokeException();
    }

    public <R> BigDecimal avg(Func8<T1, T2, T3, T4, T5, T6, T7, T8, R> expr)
    {
        throw new SqlFunctionInvokeException();
    }

    public <R> R max(Func8<T1, T2, T3, T4, T5, T6, T7, T8, R> expr)
    {
        throw new SqlFunctionInvokeException();
    }

    public <R> R min(Func8<T1, T2, T3, T4, T5, T6, T7, T8, R> expr)
    {
        throw new SqlFunctionInvokeException();
    }

}
