package com.easy.query.core.lambda.condition.select;

import com.easy.query.api.lambda.crud.read.QueryData;
import com.easy.query.core.basic.api.select.*;
import com.easy.query.core.basic.api.select.provider.SQLExpressionProvider;
import com.easy.query.core.expression.lambda.*;
import com.easy.query.core.expression.parser.core.base.ColumnAsSelector;
import com.easy.query.core.lambda.condition.criteria.Criteria;
import com.easy.query.core.lambda.visitor.SelectVisitor;
import com.easy.query.core.lambda.visitor.SelectVisitorV2;
import com.easy.query.core.lambda.visitor.SqlValue;
import io.github.kiryu1223.expressionTree.expressions.LambdaExpression;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Select extends Criteria
{
    private final LambdaExpression<?> expression;

    public Select(LambdaExpression<?> lambdaExpression)
    {
        this.expression = lambdaExpression;
    }

    // region [AutoInclude]

    public <T, R> Query<R> analysisAutoInclude(ClientQueryable<T> queryable, QueryData queryData)
    {
        if (queryable instanceof ClientQueryable10)
        {
            ClientQueryable10 clientQueryable10 = (ClientQueryable10) queryable;
            SQLExpressionProvider<T> sqlExpressionProvider1 = queryable.getSQLExpressionProvider1();

            SQLExpression10<ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>> selectExpression = (s1, s2, s3, s4, s5, s6, s7, s8, s9, s10) ->
                    analysis0(Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10));
            return clientQueryable10.selectAutoInclude(expression.getReturnType(), selectExpression,false);
        }
        else if (queryable instanceof ClientQueryable9)
        {
            ClientQueryable9 clientQueryable9 = (ClientQueryable9) queryable;
            SQLExpression9<ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>> selectExpression = (s1, s2, s3, s4, s5, s6, s7, s8, s9) ->
                    analysis0(Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8, s9));
            return clientQueryable9.selectAutoInclude(expression.getReturnType(), selectExpression,false);
        }
        else if (queryable instanceof ClientQueryable8)
        {
            ClientQueryable8 clientQueryable8 = (ClientQueryable8) queryable;
            SQLExpression8<ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>> selectExpression = (s1, s2, s3, s4, s5, s6, s7, s8) ->
                    analysis0(Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8));
            return clientQueryable8.selectAutoInclude(expression.getReturnType(), selectExpression,false);
        }
        else if (queryable instanceof ClientQueryable7)
        {
            ClientQueryable7 clientQueryable7 = (ClientQueryable7) queryable;
            SQLExpression7<ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>> selectExpression = (s1, s2, s3, s4, s5, s6, s7) ->
                    analysis0(Arrays.asList(s1, s2, s3, s4, s5, s6, s7));
            return clientQueryable7.selectAutoInclude(expression.getReturnType(), selectExpression,false);
        }
        else if (queryable instanceof ClientQueryable6)
        {
            ClientQueryable6 clientQueryable6 = (ClientQueryable6) queryable;
            SQLExpression6<ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>> selectExpression = (s1, s2, s3, s4, s5, s6) ->
                    analysis0(Arrays.asList(s1, s2, s3, s4, s5, s6));
            return clientQueryable6.selectAutoInclude(expression.getReturnType(), selectExpression,false);
        }
        else if (queryable instanceof ClientQueryable5)
        {
            ClientQueryable5 clientQueryable5 = (ClientQueryable5) queryable;
            SQLExpression5<ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>> selectExpression = (s1, s2, s3, s4, s5) ->
                    analysis0(Arrays.asList(s1, s2, s3, s4, s5));
            return clientQueryable5.selectAutoInclude(expression.getReturnType(), selectExpression,false);
        }
        else if (queryable instanceof ClientQueryable4)
        {
            ClientQueryable4 clientQueryable4 = (ClientQueryable4) queryable;
            SQLExpression4<ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>> selectExpression = (s1, s2, s3, s4) ->
                    analysis0(Arrays.asList(s1, s2, s3, s4));
            return clientQueryable4.selectAutoInclude(expression.getReturnType(), selectExpression,false);
        }
        else if (queryable instanceof ClientQueryable3)
        {
            ClientQueryable3 clientQueryable3 = (ClientQueryable3) queryable;
            SQLExpression3<ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>> selectExpression = (s1, s2, s3) ->
                    analysis0(Arrays.asList(s1, s2, s3));
            return clientQueryable3.selectAutoInclude(expression.getReturnType(), selectExpression,false);
        }
        else if (queryable instanceof ClientQueryable2)
        {
            ClientQueryable2 clientQueryable2 = (ClientQueryable2) queryable;
            SQLExpression2<ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>> selectExpression = (s1, s2) ->
                    analysis0(Arrays.asList(s1, s2));
            return clientQueryable2.selectAutoInclude(expression.getReturnType(), selectExpression,false);
        }
        else
        {
            SQLExpression1<ColumnAsSelector<T, R>> selectExpression = (s1) ->
                    analysis0(Collections.singletonList(s1));
            return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), selectExpression,false);
        }
    }

//    public <T, R> Query<R> analysisAutoInclude(ClientQueryable<T> queryable, QueryData queryData)
//    {
//        SelectVisitor select = new SelectVisitor(expression.getParameters(), queryData);
//        expression.getBody().accept(select);
//        if (select.getData().isEmpty())
//        {
//            return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), w -> w.columnAll(), false);
//        }
//        return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), w -> w.sqlNativeSegment(select.getData(), s ->
//        {
//            for (SqlValue sqlValue : select.getSqlValue())
//            {
//                switch (sqlValue.type)
//                {
//                    case value:
//                        s.value(sqlValue.value);
//                        break;
//                    case property:
//                        if (sqlValue.index == 0)
//                        {
//                            s.expression(sqlValue.value.toString());
//                        }
//                        break;
//                }
//            }
//        }), false);
//    }
//
//    public <T1, T2, R> Query<R> analysisAutoInclude(ClientQueryable2<T1, T2> queryable, QueryData queryData)
//    {
//        SelectVisitor select = new SelectVisitor(expression.getParameters(), queryData);
//        expression.getBody().accept(select);
//        if (select.getData().isEmpty())
//        {
//            switch (select.getParIndex())
//            {
//                case 0:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1) -> w0.columnAll(), false);
//                case 1:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1) -> w1.columnAll(), false);
//            }
//        }
//        return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1) -> w0.sqlNativeSegment(select.getData(), s ->
//        {
//            for (SqlValue sqlValue : select.getSqlValue())
//            {
//                switch (sqlValue.type)
//                {
//                    case value:
//                        s.value(sqlValue.value);
//                        break;
//                    case property:
//                        if (sqlValue.index == 0)
//                        {
//                            s.expression(w0, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 1)
//                        {
//                            s.expression(w1, sqlValue.value.toString());
//                        }
//                        break;
//                }
//            }
//        }), false);
//    }
//
//    public <T1, T2, T3, R> Query<R> analysisAutoInclude(ClientQueryable3<T1, T2, T3> queryable, QueryData queryData)
//    {
//        SelectVisitor select = new SelectVisitor(expression.getParameters(), queryData);
//        expression.getBody().accept(select);
//        if (select.getData().isEmpty())
//        {
//            switch (select.getParIndex())
//            {
//                case 0:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2) -> w0.columnAll(), false);
//                case 1:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2) -> w1.columnAll(), false);
//                case 2:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2) -> w2.columnAll(), false);
//            }
//        }
//        return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2) -> w0.sqlNativeSegment(select.getData(), s ->
//        {
//            for (SqlValue sqlValue : select.getSqlValue())
//            {
//                switch (sqlValue.type)
//                {
//                    case value:
//                        s.value(sqlValue.value);
//                        break;
//                    case property:
//                        if (sqlValue.index == 0)
//                        {
//                            s.expression(w0, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 1)
//                        {
//                            s.expression(w1, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 2)
//                        {
//                            s.expression(w2, sqlValue.value.toString());
//                        }
//                        break;
//                }
//            }
//        }), false);
//    }
//
//    public <T1, T2, T3, T4, R> Query<R> analysisAutoInclude(ClientQueryable4<T1, T2, T3, T4> queryable, QueryData queryData)
//    {
//        SelectVisitor select = new SelectVisitor(expression.getParameters(), queryData);
//        expression.getBody().accept(select);
//        if (select.getData().isEmpty())
//        {
//            switch (select.getParIndex())
//            {
//                case 0:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3) -> w0.columnAll(), false);
//                case 1:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3) -> w1.columnAll(), false);
//                case 2:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3) -> w2.columnAll(), false);
//                case 3:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3) -> w3.columnAll(), false);
//            }
//        }
//        return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3) -> w0.sqlNativeSegment(select.getData(), s ->
//        {
//            for (SqlValue sqlValue : select.getSqlValue())
//            {
//                switch (sqlValue.type)
//                {
//                    case value:
//                        s.value(sqlValue.value);
//                        break;
//                    case property:
//                        if (sqlValue.index == 0)
//                        {
//                            s.expression(w0, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 1)
//                        {
//                            s.expression(w1, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 2)
//                        {
//                            s.expression(w2, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 3)
//                        {
//                            s.expression(w3, sqlValue.value.toString());
//                        }
//                        break;
//                }
//            }
//        }), false);
//    }
//
//    public <T1, T2, T3, T4, T5, R> Query<R> analysisAutoInclude(ClientQueryable5<T1, T2, T3, T4, T5> queryable, QueryData queryData)
//    {
//        SelectVisitor select = new SelectVisitor(expression.getParameters(), queryData);
//        expression.getBody().accept(select);
//        if (select.getData().isEmpty())
//        {
//            switch (select.getParIndex())
//            {
//                case 0:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4) -> w0.columnAll(), false);
//                case 1:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4) -> w1.columnAll(), false);
//                case 2:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4) -> w2.columnAll(), false);
//                case 3:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4) -> w3.columnAll(), false);
//                case 4:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4) -> w4.columnAll(), false);
//            }
//        }
//        return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4) -> w0.sqlNativeSegment(select.getData(), s ->
//        {
//            for (SqlValue sqlValue : select.getSqlValue())
//            {
//                switch (sqlValue.type)
//                {
//                    case value:
//                        s.value(sqlValue.value);
//                        break;
//                    case property:
//                        if (sqlValue.index == 0)
//                        {
//                            s.expression(w0, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 1)
//                        {
//                            s.expression(w1, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 2)
//                        {
//                            s.expression(w2, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 3)
//                        {
//                            s.expression(w3, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 4)
//                        {
//                            s.expression(w4, sqlValue.value.toString());
//                        }
//                        break;
//                }
//            }
//        }), false);
//    }
//
//    public <T1, T2, T3, T4, T5, T6, R> Query<R> analysisAutoInclude(ClientQueryable6<T1, T2, T3, T4, T5, T6> queryable, QueryData queryData)
//    {
//        SelectVisitor select = new SelectVisitor(expression.getParameters(), queryData);
//        expression.getBody().accept(select);
//        if (select.getData().isEmpty())
//        {
//            switch (select.getParIndex())
//            {
//                case 0:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5) -> w0.columnAll(), false);
//                case 1:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5) -> w1.columnAll(), false);
//                case 2:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5) -> w2.columnAll(), false);
//                case 3:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5) -> w3.columnAll(), false);
//                case 4:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5) -> w4.columnAll(), false);
//                case 5:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5) -> w5.columnAll(), false);
//            }
//        }
//        return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5) -> w0.sqlNativeSegment(select.getData(), s ->
//        {
//            for (SqlValue sqlValue : select.getSqlValue())
//            {
//                switch (sqlValue.type)
//                {
//                    case value:
//                        s.value(sqlValue.value);
//                        break;
//                    case property:
//                        if (sqlValue.index == 0)
//                        {
//                            s.expression(w0, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 1)
//                        {
//                            s.expression(w1, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 2)
//                        {
//                            s.expression(w2, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 3)
//                        {
//                            s.expression(w3, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 4)
//                        {
//                            s.expression(w4, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 5)
//                        {
//                            s.expression(w5, sqlValue.value.toString());
//                        }
//                        break;
//                }
//            }
//        }), false);
//    }
//
//    public <T1, T2, T3, T4, T5, T6, T7, R> Query<R> analysisAutoInclude(ClientQueryable7<T1, T2, T3, T4, T5, T6, T7> queryable, QueryData queryData)
//    {
//        SelectVisitor select = new SelectVisitor(expression.getParameters(), queryData);
//        expression.getBody().accept(select);
//        if (select.getData().isEmpty())
//        {
//            switch (select.getParIndex())
//            {
//                case 0:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6) -> w0.columnAll(), false);
//                case 1:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6) -> w1.columnAll(), false);
//                case 2:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6) -> w2.columnAll(), false);
//                case 3:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6) -> w3.columnAll(), false);
//                case 4:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6) -> w4.columnAll(), false);
//                case 5:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6) -> w5.columnAll(), false);
//                case 6:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6) -> w6.columnAll(), false);
//            }
//        }
//        return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6) -> w0.sqlNativeSegment(select.getData(), s ->
//        {
//            for (SqlValue sqlValue : select.getSqlValue())
//            {
//                switch (sqlValue.type)
//                {
//                    case value:
//                        s.value(sqlValue.value);
//                        break;
//                    case property:
//                        if (sqlValue.index == 0)
//                        {
//                            s.expression(w0, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 1)
//                        {
//                            s.expression(w1, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 2)
//                        {
//                            s.expression(w2, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 3)
//                        {
//                            s.expression(w3, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 4)
//                        {
//                            s.expression(w4, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 5)
//                        {
//                            s.expression(w5, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 6)
//                        {
//                            s.expression(w6, sqlValue.value.toString());
//                        }
//                        break;
//                }
//            }
//        }), false);
//    }
//
//    public <T1, T2, T3, T4, T5, T6, T7, T8, R> Query<R> analysisAutoInclude(ClientQueryable8<T1, T2, T3, T4, T5, T6, T7, T8> queryable, QueryData queryData)
//    {
//        SelectVisitor select = new SelectVisitor(expression.getParameters(), queryData);
//        expression.getBody().accept(select);
//        if (select.getData().isEmpty())
//        {
//            switch (select.getParIndex())
//            {
//                case 0:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7) -> w0.columnAll(), false);
//                case 1:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7) -> w1.columnAll(), false);
//                case 2:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7) -> w2.columnAll(), false);
//                case 3:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7) -> w3.columnAll(), false);
//                case 4:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7) -> w4.columnAll(), false);
//                case 5:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7) -> w5.columnAll(), false);
//                case 6:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7) -> w6.columnAll(), false);
//                case 7:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7) -> w7.columnAll(), false);
//            }
//        }
//        return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7) -> w0.sqlNativeSegment(select.getData(), s ->
//        {
//            for (SqlValue sqlValue : select.getSqlValue())
//            {
//                switch (sqlValue.type)
//                {
//                    case value:
//                        s.value(sqlValue.value);
//                        break;
//                    case property:
//                        if (sqlValue.index == 0)
//                        {
//                            s.expression(w0, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 1)
//                        {
//                            s.expression(w1, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 2)
//                        {
//                            s.expression(w2, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 3)
//                        {
//                            s.expression(w3, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 4)
//                        {
//                            s.expression(w4, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 5)
//                        {
//                            s.expression(w5, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 6)
//                        {
//                            s.expression(w6, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 7)
//                        {
//                            s.expression(w7, sqlValue.value.toString());
//                        }
//                        break;
//                }
//            }
//        }), false);
//    }
//
//    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Query<R> analysisAutoInclude(ClientQueryable9<T1, T2, T3, T4, T5, T6, T7, T8, T9> queryable, QueryData queryData)
//    {
//        SelectVisitor select = new SelectVisitor(expression.getParameters(), queryData);
//        expression.getBody().accept(select);
//        if (select.getData().isEmpty())
//        {
//            switch (select.getParIndex())
//            {
//                case 0:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8) -> w0.columnAll(), false);
//                case 1:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8) -> w1.columnAll(), false);
//                case 2:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8) -> w2.columnAll(), false);
//                case 3:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8) -> w3.columnAll(), false);
//                case 4:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8) -> w4.columnAll(), false);
//                case 5:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8) -> w5.columnAll(), false);
//                case 6:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8) -> w6.columnAll(), false);
//                case 7:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8) -> w7.columnAll(), false);
//                case 8:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8) -> w8.columnAll(), false);
//            }
//        }
//        return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8) -> w0.sqlNativeSegment(select.getData(), s ->
//        {
//            for (SqlValue sqlValue : select.getSqlValue())
//            {
//                switch (sqlValue.type)
//                {
//                    case value:
//                        s.value(sqlValue.value);
//                        break;
//                    case property:
//                        if (sqlValue.index == 0)
//                        {
//                            s.expression(w0, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 1)
//                        {
//                            s.expression(w1, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 2)
//                        {
//                            s.expression(w2, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 3)
//                        {
//                            s.expression(w3, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 4)
//                        {
//                            s.expression(w4, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 5)
//                        {
//                            s.expression(w5, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 6)
//                        {
//                            s.expression(w6, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 7)
//                        {
//                            s.expression(w7, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 8)
//                        {
//                            s.expression(w8, sqlValue.value.toString());
//                        }
//                        break;
//                }
//            }
//        }), false);
//    }
//
//    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> Query<R> analysisAutoInclude(ClientQueryable10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> queryable, QueryData queryData)
//    {
//        SelectVisitor select = new SelectVisitor(expression.getParameters(), queryData);
//        expression.getBody().accept(select);
//        if (select.getData().isEmpty())
//        {
//            switch (select.getParIndex())
//            {
//                case 0:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) -> w0.columnAll(), false);
//                case 1:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) -> w1.columnAll(), false);
//                case 2:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) -> w2.columnAll(), false);
//                case 3:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) -> w3.columnAll(), false);
//                case 4:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) -> w4.columnAll(), false);
//                case 5:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) -> w5.columnAll(), false);
//                case 6:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) -> w6.columnAll(), false);
//                case 7:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) -> w7.columnAll(), false);
//                case 8:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) -> w8.columnAll(), false);
//                case 9:
//                    return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) -> w9.columnAll(), false);
//            }
//        }
//        return queryable.selectAutoInclude((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) -> w0.sqlNativeSegment(select.getData(), s ->
//        {
//            for (SqlValue sqlValue : select.getSqlValue())
//            {
//                switch (sqlValue.type)
//                {
//                    case value:
//                        s.value(sqlValue.value);
//                        break;
//                    case property:
//                        if (sqlValue.index == 0)
//                        {
//                            s.expression(w0, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 1)
//                        {
//                            s.expression(w1, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 2)
//                        {
//                            s.expression(w2, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 3)
//                        {
//                            s.expression(w3, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 4)
//                        {
//                            s.expression(w4, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 5)
//                        {
//                            s.expression(w5, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 6)
//                        {
//                            s.expression(w6, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 7)
//                        {
//                            s.expression(w7, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 8)
//                        {
//                            s.expression(w8, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 9)
//                        {
//                            s.expression(w9, sqlValue.value.toString());
//                        }
//                        break;
//                }
//            }
//        }), false);
//    }

    // endregion

    // region [normal]

    public <T, R> ClientQueryable<R> analysis(ClientQueryable<T> queryable, QueryData queryData)
    {
        if (queryable instanceof ClientQueryable10)
        {
            ClientQueryable10 clientQueryable10 = (ClientQueryable10) queryable;
            SQLExpressionProvider<T> sqlExpressionProvider1 = queryable.getSQLExpressionProvider1();

            SQLExpression10<ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>> selectExpression = (s1, s2, s3, s4, s5, s6, s7, s8, s9, s10) ->
                    analysis0(Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10));
            return clientQueryable10.select(expression.getReturnType(), selectExpression);
        }
        else if (queryable instanceof ClientQueryable9)
        {
            ClientQueryable9 clientQueryable9 = (ClientQueryable9) queryable;
            SQLExpression9<ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>> selectExpression = (s1, s2, s3, s4, s5, s6, s7, s8, s9) ->
                    analysis0(Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8, s9));
            return clientQueryable9.select(expression.getReturnType(), selectExpression);
        }
        else if (queryable instanceof ClientQueryable8)
        {
            ClientQueryable8 clientQueryable8 = (ClientQueryable8) queryable;
            SQLExpression8<ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>> selectExpression = (s1, s2, s3, s4, s5, s6, s7, s8) ->
                    analysis0(Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8));
            return clientQueryable8.select(expression.getReturnType(), selectExpression);
        }
        else if (queryable instanceof ClientQueryable7)
        {
            ClientQueryable7 clientQueryable7 = (ClientQueryable7) queryable;
            SQLExpression7<ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>> selectExpression = (s1, s2, s3, s4, s5, s6, s7) ->
                    analysis0(Arrays.asList(s1, s2, s3, s4, s5, s6, s7));
            return clientQueryable7.select(expression.getReturnType(), selectExpression);
        }
        else if (queryable instanceof ClientQueryable6)
        {
            ClientQueryable6 clientQueryable6 = (ClientQueryable6) queryable;
            SQLExpression6<ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>> selectExpression = (s1, s2, s3, s4, s5, s6) ->
                    analysis0(Arrays.asList(s1, s2, s3, s4, s5, s6));
            return clientQueryable6.select(expression.getReturnType(), selectExpression);
        }
        else if (queryable instanceof ClientQueryable5)
        {
            ClientQueryable5 clientQueryable5 = (ClientQueryable5) queryable;
            SQLExpression5<ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>> selectExpression = (s1, s2, s3, s4, s5) ->
                    analysis0(Arrays.asList(s1, s2, s3, s4, s5));
            return clientQueryable5.select(expression.getReturnType(), selectExpression);
        }
        else if (queryable instanceof ClientQueryable4)
        {
            ClientQueryable4 clientQueryable4 = (ClientQueryable4) queryable;
            SQLExpression4<ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>> selectExpression = (s1, s2, s3, s4) ->
                    analysis0(Arrays.asList(s1, s2, s3, s4));
            return clientQueryable4.select(expression.getReturnType(), selectExpression);
        }
        else if (queryable instanceof ClientQueryable3)
        {
            ClientQueryable3 clientQueryable3 = (ClientQueryable3) queryable;
            SQLExpression3<ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>> selectExpression = (s1, s2, s3) ->
                    analysis0(Arrays.asList(s1, s2, s3));
            return clientQueryable3.select(expression.getReturnType(), selectExpression);
        }
        else if (queryable instanceof ClientQueryable2)
        {
            ClientQueryable2 clientQueryable2 = (ClientQueryable2) queryable;
            SQLExpression2<ColumnAsSelector<?, ?>, ColumnAsSelector<?, ?>> selectExpression = (s1, s2) ->
                    analysis0(Arrays.asList(s1, s2));
            return clientQueryable2.select(expression.getReturnType(), selectExpression);
        }
        else
        {
            SQLExpression1<ColumnAsSelector<T, R>> selectExpression = (s1) ->
                    analysis0(Collections.singletonList(s1));
            return queryable.select((Class<R>) expression.getReturnType(), selectExpression);
        }
    }

    private void analysis0(List<ColumnAsSelector<?, ?>> columns)
    {
        SelectVisitorV2 selectVisitorV2 = new SelectVisitorV2(expression.getParameters(), columns);
        selectVisitorV2.visit(expression.getBody());
    }

//    public <T, R> ClientQueryable<R> analysis(ClientQueryable<T> queryable, QueryData queryData)
//    {
//        SelectVisitor select = new SelectVisitor(expression.getParameters(), queryData);
//        expression.getBody().accept(select);
//        if (select.getData().isEmpty())
//        {
//            return queryable.select((Class<R>) expression.getReturnType(), w -> w.columnAll());
//        }
//        return queryable.select((Class<R>) expression.getReturnType(), w -> w.sqlNativeSegment(select.getData(), s ->
//        {
//            for (SqlValue sqlValue : select.getSqlValue())
//            {
//                switch (sqlValue.type)
//                {
//                    case value:
//                        s.value(sqlValue.value);
//                        break;
//                    case property:
//                        if (sqlValue.index == 0)
//                        {
//                            s.expression(sqlValue.value.toString());
//                        }
//                        break;
//                }
//            }
//        }));
//    }

//    public <T1, T2, R> ClientQueryable<R> analysis(ClientQueryable2<T1, T2> queryable, QueryData queryData)
//    {
//        SelectVisitor select = new SelectVisitor(expression.getParameters(), queryData);
//        expression.getBody().accept(select);
//        if (select.getData().isEmpty())
//        {
//            switch (select.getParIndex())
//            {
//                case 0:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1) -> w0.columnAll());
//                case 1:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1) -> w1.columnAll());
//            }
//        }
//        return queryable.select((Class<R>) expression.getReturnType(), (w0, w1) -> w0.sqlNativeSegment(select.getData(), s ->
//        {
//            for (SqlValue sqlValue : select.getSqlValue())
//            {
//                switch (sqlValue.type)
//                {
//                    case value:
//                        s.value(sqlValue.value);
//                        break;
//                    case property:
//                        if (sqlValue.index == 0)
//                        {
//                            s.expression(w0, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 1)
//                        {
//                            s.expression(w1, sqlValue.value.toString());
//                        }
//                        break;
//                }
//            }
//        }));
//    }
//
//    public <T1, T2, T3, R> ClientQueryable<R> analysis(ClientQueryable3<T1, T2, T3> queryable, QueryData queryData)
//    {
//        SelectVisitor select = new SelectVisitor(expression.getParameters(), queryData);
//        expression.getBody().accept(select);
//        if (select.getData().isEmpty())
//        {
//            switch (select.getParIndex())
//            {
//                case 0:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2) -> w0.columnAll());
//                case 1:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2) -> w1.columnAll());
//                case 2:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2) -> w2.columnAll());
//            }
//        }
//        return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2) -> w0.sqlNativeSegment(select.getData(), s ->
//        {
//            for (SqlValue sqlValue : select.getSqlValue())
//            {
//                switch (sqlValue.type)
//                {
//                    case value:
//                        s.value(sqlValue.value);
//                        break;
//                    case property:
//                        if (sqlValue.index == 0)
//                        {
//                            s.expression(w0, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 1)
//                        {
//                            s.expression(w1, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 2)
//                        {
//                            s.expression(w2, sqlValue.value.toString());
//                        }
//                        break;
//                }
//            }
//        }));
//    }
//
//    public <T1, T2, T3, T4, R> ClientQueryable<R> analysis(ClientQueryable4<T1, T2, T3, T4> queryable, QueryData queryData)
//    {
//        SelectVisitor select = new SelectVisitor(expression.getParameters(), queryData);
//        expression.getBody().accept(select);
//        if (select.getData().isEmpty())
//        {
//            switch (select.getParIndex())
//            {
//                case 0:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3) -> w0.columnAll());
//                case 1:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3) -> w1.columnAll());
//                case 2:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3) -> w2.columnAll());
//                case 3:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3) -> w3.columnAll());
//            }
//        }
//        return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3) -> w0.sqlNativeSegment(select.getData(), s ->
//        {
//            for (SqlValue sqlValue : select.getSqlValue())
//            {
//                switch (sqlValue.type)
//                {
//                    case value:
//                        s.value(sqlValue.value);
//                        break;
//                    case property:
//                        if (sqlValue.index == 0)
//                        {
//                            s.expression(w0, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 1)
//                        {
//                            s.expression(w1, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 2)
//                        {
//                            s.expression(w2, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 3)
//                        {
//                            s.expression(w3, sqlValue.value.toString());
//                        }
//                        break;
//                }
//            }
//        }));
//    }
//
//    public <T1, T2, T3, T4, T5, R> ClientQueryable<R> analysis(ClientQueryable5<T1, T2, T3, T4, T5> queryable, QueryData queryData)
//    {
//        SelectVisitor select = new SelectVisitor(expression.getParameters(), queryData);
//        expression.getBody().accept(select);
//        if (select.getData().isEmpty())
//        {
//            switch (select.getParIndex())
//            {
//                case 0:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4) -> w0.columnAll());
//                case 1:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4) -> w1.columnAll());
//                case 2:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4) -> w2.columnAll());
//                case 3:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4) -> w3.columnAll());
//                case 4:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4) -> w4.columnAll());
//            }
//        }
//        return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4) -> w0.sqlNativeSegment(select.getData(), s ->
//        {
//            for (SqlValue sqlValue : select.getSqlValue())
//            {
//                switch (sqlValue.type)
//                {
//                    case value:
//                        s.value(sqlValue.value);
//                        break;
//                    case property:
//                        if (sqlValue.index == 0)
//                        {
//                            s.expression(w0, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 1)
//                        {
//                            s.expression(w1, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 2)
//                        {
//                            s.expression(w2, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 3)
//                        {
//                            s.expression(w3, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 4)
//                        {
//                            s.expression(w4, sqlValue.value.toString());
//                        }
//                        break;
//                }
//            }
//        }));
//    }
//
//    public <T1, T2, T3, T4, T5, T6, R> ClientQueryable<R> analysis(ClientQueryable6<T1, T2, T3, T4, T5, T6> queryable, QueryData queryData)
//    {
//        SelectVisitor select = new SelectVisitor(expression.getParameters(), queryData);
//        expression.getBody().accept(select);
//        if (select.getData().isEmpty())
//        {
//            switch (select.getParIndex())
//            {
//                case 0:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5) -> w0.columnAll());
//                case 1:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5) -> w1.columnAll());
//                case 2:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5) -> w2.columnAll());
//                case 3:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5) -> w3.columnAll());
//                case 4:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5) -> w4.columnAll());
//                case 5:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5) -> w5.columnAll());
//            }
//        }
//        return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5) -> w0.sqlNativeSegment(select.getData(), s ->
//        {
//            for (SqlValue sqlValue : select.getSqlValue())
//            {
//                switch (sqlValue.type)
//                {
//                    case value:
//                        s.value(sqlValue.value);
//                        break;
//                    case property:
//                        if (sqlValue.index == 0)
//                        {
//                            s.expression(w0, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 1)
//                        {
//                            s.expression(w1, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 2)
//                        {
//                            s.expression(w2, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 3)
//                        {
//                            s.expression(w3, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 4)
//                        {
//                            s.expression(w4, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 5)
//                        {
//                            s.expression(w5, sqlValue.value.toString());
//                        }
//                        break;
//                }
//            }
//        }));
//    }
//
//    public <T1, T2, T3, T4, T5, T6, T7, R> ClientQueryable<R> analysis(ClientQueryable7<T1, T2, T3, T4, T5, T6, T7> queryable, QueryData queryData)
//    {
//        SelectVisitor select = new SelectVisitor(expression.getParameters(), queryData);
//        expression.getBody().accept(select);
//        if (select.getData().isEmpty())
//        {
//            switch (select.getParIndex())
//            {
//                case 0:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6) -> w0.columnAll());
//                case 1:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6) -> w1.columnAll());
//                case 2:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6) -> w2.columnAll());
//                case 3:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6) -> w3.columnAll());
//                case 4:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6) -> w4.columnAll());
//                case 5:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6) -> w5.columnAll());
//                case 6:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6) -> w6.columnAll());
//            }
//        }
//        return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6) -> w0.sqlNativeSegment(select.getData(), s ->
//        {
//            for (SqlValue sqlValue : select.getSqlValue())
//            {
//                switch (sqlValue.type)
//                {
//                    case value:
//                        s.value(sqlValue.value);
//                        break;
//                    case property:
//                        if (sqlValue.index == 0)
//                        {
//                            s.expression(w0, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 1)
//                        {
//                            s.expression(w1, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 2)
//                        {
//                            s.expression(w2, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 3)
//                        {
//                            s.expression(w3, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 4)
//                        {
//                            s.expression(w4, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 5)
//                        {
//                            s.expression(w5, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 6)
//                        {
//                            s.expression(w6, sqlValue.value.toString());
//                        }
//                        break;
//                }
//            }
//        }));
//    }
//
//    public <T1, T2, T3, T4, T5, T6, T7, T8, R> ClientQueryable<R> analysis(ClientQueryable8<T1, T2, T3, T4, T5, T6, T7, T8> queryable, QueryData queryData)
//    {
//        SelectVisitor select = new SelectVisitor(expression.getParameters(), queryData);
//        expression.getBody().accept(select);
//        if (select.getData().isEmpty())
//        {
//            switch (select.getParIndex())
//            {
//                case 0:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7) -> w0.columnAll());
//                case 1:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7) -> w1.columnAll());
//                case 2:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7) -> w2.columnAll());
//                case 3:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7) -> w3.columnAll());
//                case 4:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7) -> w4.columnAll());
//                case 5:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7) -> w5.columnAll());
//                case 6:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7) -> w6.columnAll());
//                case 7:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7) -> w7.columnAll());
//            }
//        }
//        return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7) -> w0.sqlNativeSegment(select.getData(), s ->
//        {
//            for (SqlValue sqlValue : select.getSqlValue())
//            {
//                switch (sqlValue.type)
//                {
//                    case value:
//                        s.value(sqlValue.value);
//                        break;
//                    case property:
//                        if (sqlValue.index == 0)
//                        {
//                            s.expression(w0, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 1)
//                        {
//                            s.expression(w1, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 2)
//                        {
//                            s.expression(w2, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 3)
//                        {
//                            s.expression(w3, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 4)
//                        {
//                            s.expression(w4, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 5)
//                        {
//                            s.expression(w5, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 6)
//                        {
//                            s.expression(w6, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 7)
//                        {
//                            s.expression(w7, sqlValue.value.toString());
//                        }
//                        break;
//                }
//            }
//        }));
//    }
//
//    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> ClientQueryable<R> analysis(ClientQueryable9<T1, T2, T3, T4, T5, T6, T7, T8, T9> queryable, QueryData queryData)
//    {
//        SelectVisitor select = new SelectVisitor(expression.getParameters(), queryData);
//        expression.getBody().accept(select);
//        if (select.getData().isEmpty())
//        {
//            switch (select.getParIndex())
//            {
//                case 0:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8) -> w0.columnAll());
//                case 1:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8) -> w1.columnAll());
//                case 2:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8) -> w2.columnAll());
//                case 3:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8) -> w3.columnAll());
//                case 4:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8) -> w4.columnAll());
//                case 5:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8) -> w5.columnAll());
//                case 6:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8) -> w6.columnAll());
//                case 7:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8) -> w7.columnAll());
//                case 8:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8) -> w8.columnAll());
//            }
//        }
//        return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8) -> w0.sqlNativeSegment(select.getData(), s ->
//        {
//            for (SqlValue sqlValue : select.getSqlValue())
//            {
//                switch (sqlValue.type)
//                {
//                    case value:
//                        s.value(sqlValue.value);
//                        break;
//                    case property:
//                        if (sqlValue.index == 0)
//                        {
//                            s.expression(w0, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 1)
//                        {
//                            s.expression(w1, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 2)
//                        {
//                            s.expression(w2, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 3)
//                        {
//                            s.expression(w3, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 4)
//                        {
//                            s.expression(w4, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 5)
//                        {
//                            s.expression(w5, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 6)
//                        {
//                            s.expression(w6, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 7)
//                        {
//                            s.expression(w7, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 8)
//                        {
//                            s.expression(w8, sqlValue.value.toString());
//                        }
//                        break;
//                }
//            }
//        }));
//    }
//
//    public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> ClientQueryable<R> analysis(ClientQueryable10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> queryable, QueryData queryData)
//    {
//        SelectVisitor select = new SelectVisitor(expression.getParameters(), queryData);
//        expression.getBody().accept(select);
//        if (select.getData().isEmpty())
//        {
//            switch (select.getParIndex())
//            {
//                case 0:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) -> w0.columnAll());
//                case 1:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) -> w1.columnAll());
//                case 2:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) -> w2.columnAll());
//                case 3:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) -> w3.columnAll());
//                case 4:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) -> w4.columnAll());
//                case 5:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) -> w5.columnAll());
//                case 6:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) -> w6.columnAll());
//                case 7:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) -> w7.columnAll());
//                case 8:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) -> w8.columnAll());
//                case 9:
//                    return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) -> w9.columnAll());
//            }
//        }
//        return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) -> w0.sqlNativeSegment(select.getData(), s ->
//        {
//            for (SqlValue sqlValue : select.getSqlValue())
//            {
//                switch (sqlValue.type)
//                {
//                    case value:
//                        s.value(sqlValue.value);
//                        break;
//                    case property:
//                        if (sqlValue.index == 0)
//                        {
//                            s.expression(w0, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 1)
//                        {
//                            s.expression(w1, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 2)
//                        {
//                            s.expression(w2, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 3)
//                        {
//                            s.expression(w3, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 4)
//                        {
//                            s.expression(w4, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 5)
//                        {
//                            s.expression(w5, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 6)
//                        {
//                            s.expression(w6, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 7)
//                        {
//                            s.expression(w7, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 8)
//                        {
//                            s.expression(w8, sqlValue.value.toString());
//                        }
//                        else if (sqlValue.index == 9)
//                        {
//                            s.expression(w9, sqlValue.value.toString());
//                        }
//                        break;
//                }
//            }
//        }));
//    }

    // endregion
}
