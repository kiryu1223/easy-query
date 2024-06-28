package com.easy.query.core.lambda.condition.criteria;

import com.easy.query.api.lambda.crud.read.QueryData;
import com.easy.query.core.basic.api.select.*;
import com.easy.query.core.basic.api.select.provider.SQLExpressionProvider;
import com.easy.query.core.expression.parser.core.EntitySQLTableOwner;
import com.easy.query.core.expression.parser.core.base.WherePredicate;
import com.easy.query.core.lambda.exception.IllegalExpressionException;
import com.easy.query.core.lambda.visitor.GroupByVisitor;
import com.easy.query.core.lambda.visitor.SqlValue;
import io.github.kiryu1223.expressionTree.expressions.Kind;
import io.github.kiryu1223.expressionTree.expressions.LambdaExpression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Criteria
{
    protected void checkExprBody(LambdaExpression<?> lambdaExpression)
    {
        if (lambdaExpression.getBody().getKind() == Kind.Block)
        {
            throw new IllegalExpressionException(lambdaExpression);
        }
    }

    protected void analysisGroupBy(ClientQueryable<?> clientQueryable, QueryData queryData, String sql, List<SqlValue> sqlValues)
    {
        if (clientQueryable instanceof ClientQueryable10)
        {
            ClientQueryable10<?, ?, ?, ?, ?, ?, ?, ?, ?, ?> queryable = (ClientQueryable10<?, ?, ?, ?, ?, ?, ?, ?, ?, ?>) clientQueryable;
            queryable.groupBy((w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                                case 3:
                                    s.expression(w3, sqlValue.value.toString());
                                    break;
                                case 4:
                                    s.expression(w4, sqlValue.value.toString());
                                    break;
                                case 5:
                                    s.expression(w5, sqlValue.value.toString());
                                    break;
                                case 6:
                                    s.expression(w6, sqlValue.value.toString());
                                    break;
                                case 7:
                                    s.expression(w7, sqlValue.value.toString());
                                    break;
                                case 8:
                                    s.expression(w8, sqlValue.value.toString());
                                    break;
                                case 9:
                                    s.expression(w9, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable9)
        {
            ClientQueryable9<?, ?, ?, ?, ?, ?, ?, ?, ?> queryable = (ClientQueryable9<?, ?, ?, ?, ?, ?, ?, ?, ?>) clientQueryable;
            queryable.groupBy((w0, w1, w2, w3, w4, w5, w6, w7, w8) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                                case 3:
                                    s.expression(w3, sqlValue.value.toString());
                                    break;
                                case 4:
                                    s.expression(w4, sqlValue.value.toString());
                                    break;
                                case 5:
                                    s.expression(w5, sqlValue.value.toString());
                                    break;
                                case 6:
                                    s.expression(w6, sqlValue.value.toString());
                                    break;
                                case 7:
                                    s.expression(w7, sqlValue.value.toString());
                                    break;
                                case 8:
                                    s.expression(w8, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable8)
        {
            ClientQueryable8<?, ?, ?, ?, ?, ?, ?, ?> queryable = (ClientQueryable8<?, ?, ?, ?, ?, ?, ?, ?>) clientQueryable;
            queryable.groupBy((w0, w1, w2, w3, w4, w5, w6, w7) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                                case 3:
                                    s.expression(w3, sqlValue.value.toString());
                                    break;
                                case 4:
                                    s.expression(w4, sqlValue.value.toString());
                                    break;
                                case 5:
                                    s.expression(w5, sqlValue.value.toString());
                                    break;
                                case 6:
                                    s.expression(w6, sqlValue.value.toString());
                                    break;
                                case 7:
                                    s.expression(w7, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable7)
        {
            ClientQueryable7<?, ?, ?, ?, ?, ?, ?> queryable = (ClientQueryable7<?, ?, ?, ?, ?, ?, ?>) clientQueryable;
            queryable.groupBy((w0, w1, w2, w3, w4, w5, w6) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                                case 3:
                                    s.expression(w3, sqlValue.value.toString());
                                    break;
                                case 4:
                                    s.expression(w4, sqlValue.value.toString());
                                    break;
                                case 5:
                                    s.expression(w5, sqlValue.value.toString());
                                    break;
                                case 6:
                                    s.expression(w6, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable6)
        {
            ClientQueryable6<?, ?, ?, ?, ?, ?> queryable = (ClientQueryable6<?, ?, ?, ?, ?, ?>) clientQueryable;
            queryable.groupBy((w0, w1, w2, w3, w4, w5) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                                case 3:
                                    s.expression(w3, sqlValue.value.toString());
                                    break;
                                case 4:
                                    s.expression(w4, sqlValue.value.toString());
                                    break;
                                case 5:
                                    s.expression(w5, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable5)
        {
            ClientQueryable5<?, ?, ?, ?, ?> queryable = (ClientQueryable5<?, ?, ?, ?, ?>) clientQueryable;
            queryable.groupBy((w0, w1, w2, w3, w4) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                                case 3:
                                    s.expression(w3, sqlValue.value.toString());
                                    break;
                                case 4:
                                    s.expression(w4, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable4)
        {
            ClientQueryable4<?, ?, ?, ?> queryable = (ClientQueryable4<?, ?, ?, ?>) clientQueryable;
            queryable.groupBy((w0, w1, w2, w3) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                                case 3:
                                    s.expression(w3, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable3)
        {
            ClientQueryable3<?, ?, ?> queryable = (ClientQueryable3<?, ?, ?>) clientQueryable;
            queryable.groupBy((w0, w1, w2) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable2)
        {
            ClientQueryable2<?, ?> queryable = (ClientQueryable2<?, ?>) clientQueryable;
            queryable.groupBy((w0, w1) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else
        {
            clientQueryable.groupBy(w0 -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            if (sqlValue.index == 0)
                            {
                                s.expression(w0, sqlValue.value.toString());
                            }
                            break;
                    }
                }
            }));
        }
    }

    protected void analysisHaving(ClientQueryable<?> clientQueryable, QueryData queryData, String sql, List<SqlValue> sqlValues)
    {
        if (clientQueryable instanceof ClientQueryable10)
        {
            ClientQueryable10<?, ?, ?, ?, ?, ?, ?, ?, ?, ?> queryable = (ClientQueryable10<?, ?, ?, ?, ?, ?, ?, ?, ?, ?>) clientQueryable;
            queryable.having((w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                                case 3:
                                    s.expression(w3, sqlValue.value.toString());
                                    break;
                                case 4:
                                    s.expression(w4, sqlValue.value.toString());
                                    break;
                                case 5:
                                    s.expression(w5, sqlValue.value.toString());
                                    break;
                                case 6:
                                    s.expression(w6, sqlValue.value.toString());
                                    break;
                                case 7:
                                    s.expression(w7, sqlValue.value.toString());
                                    break;
                                case 8:
                                    s.expression(w8, sqlValue.value.toString());
                                    break;
                                case 9:
                                    s.expression(w9, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable9)
        {
            ClientQueryable9<?, ?, ?, ?, ?, ?, ?, ?, ?> queryable = (ClientQueryable9<?, ?, ?, ?, ?, ?, ?, ?, ?>) clientQueryable;
            queryable.having((w0, w1, w2, w3, w4, w5, w6, w7, w8) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                                case 3:
                                    s.expression(w3, sqlValue.value.toString());
                                    break;
                                case 4:
                                    s.expression(w4, sqlValue.value.toString());
                                    break;
                                case 5:
                                    s.expression(w5, sqlValue.value.toString());
                                    break;
                                case 6:
                                    s.expression(w6, sqlValue.value.toString());
                                    break;
                                case 7:
                                    s.expression(w7, sqlValue.value.toString());
                                    break;
                                case 8:
                                    s.expression(w8, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable8)
        {
            ClientQueryable8<?, ?, ?, ?, ?, ?, ?, ?> queryable = (ClientQueryable8<?, ?, ?, ?, ?, ?, ?, ?>) clientQueryable;
            queryable.having((w0, w1, w2, w3, w4, w5, w6, w7) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                                case 3:
                                    s.expression(w3, sqlValue.value.toString());
                                    break;
                                case 4:
                                    s.expression(w4, sqlValue.value.toString());
                                    break;
                                case 5:
                                    s.expression(w5, sqlValue.value.toString());
                                    break;
                                case 6:
                                    s.expression(w6, sqlValue.value.toString());
                                    break;
                                case 7:
                                    s.expression(w7, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable7)
        {
            ClientQueryable7<?, ?, ?, ?, ?, ?, ?> queryable = (ClientQueryable7<?, ?, ?, ?, ?, ?, ?>) clientQueryable;
            queryable.having((w0, w1, w2, w3, w4, w5, w6) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                                case 3:
                                    s.expression(w3, sqlValue.value.toString());
                                    break;
                                case 4:
                                    s.expression(w4, sqlValue.value.toString());
                                    break;
                                case 5:
                                    s.expression(w5, sqlValue.value.toString());
                                    break;
                                case 6:
                                    s.expression(w6, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable6)
        {
            ClientQueryable6<?, ?, ?, ?, ?, ?> queryable = (ClientQueryable6<?, ?, ?, ?, ?, ?>) clientQueryable;
            queryable.having((w0, w1, w2, w3, w4, w5) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                                case 3:
                                    s.expression(w3, sqlValue.value.toString());
                                    break;
                                case 4:
                                    s.expression(w4, sqlValue.value.toString());
                                    break;
                                case 5:
                                    s.expression(w5, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable5)
        {
            ClientQueryable5<?, ?, ?, ?, ?> queryable = (ClientQueryable5<?, ?, ?, ?, ?>) clientQueryable;
            queryable.having((w0, w1, w2, w3, w4) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                                case 3:
                                    s.expression(w3, sqlValue.value.toString());
                                    break;
                                case 4:
                                    s.expression(w4, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable4)
        {
            ClientQueryable4<?, ?, ?, ?> queryable = (ClientQueryable4<?, ?, ?, ?>) clientQueryable;
            queryable.having((w0, w1, w2, w3) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                                case 3:
                                    s.expression(w3, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable3)
        {
            ClientQueryable3<?, ?, ?> queryable = (ClientQueryable3<?, ?, ?>) clientQueryable;
            queryable.having((w0, w1, w2) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable2)
        {
            ClientQueryable2<?, ?> queryable = (ClientQueryable2<?, ?>) clientQueryable;
            queryable.having((w0, w1) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else
        {
            clientQueryable.having(w0 -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            if (sqlValue.index == 0)
                            {
                                s.expression(w0, sqlValue.value.toString());
                            }
                            break;
                    }
                }
            }));
        }
    }

    protected void analysisOrderBy(ClientQueryable<?> clientQueryable, QueryData queryData, String sql, List<SqlValue> sqlValues)
    {
        if (clientQueryable instanceof ClientQueryable10)
        {
            ClientQueryable10<?, ?, ?, ?, ?, ?, ?, ?, ?, ?> queryable = (ClientQueryable10<?, ?, ?, ?, ?, ?, ?, ?, ?, ?>) clientQueryable;
            queryable.orderByAsc((w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                                case 3:
                                    s.expression(w3, sqlValue.value.toString());
                                    break;
                                case 4:
                                    s.expression(w4, sqlValue.value.toString());
                                    break;
                                case 5:
                                    s.expression(w5, sqlValue.value.toString());
                                    break;
                                case 6:
                                    s.expression(w6, sqlValue.value.toString());
                                    break;
                                case 7:
                                    s.expression(w7, sqlValue.value.toString());
                                    break;
                                case 8:
                                    s.expression(w8, sqlValue.value.toString());
                                    break;
                                case 9:
                                    s.expression(w9, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable9)
        {
            ClientQueryable9<?, ?, ?, ?, ?, ?, ?, ?, ?> queryable = (ClientQueryable9<?, ?, ?, ?, ?, ?, ?, ?, ?>) clientQueryable;
            queryable.orderByAsc((w0, w1, w2, w3, w4, w5, w6, w7, w8) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                                case 3:
                                    s.expression(w3, sqlValue.value.toString());
                                    break;
                                case 4:
                                    s.expression(w4, sqlValue.value.toString());
                                    break;
                                case 5:
                                    s.expression(w5, sqlValue.value.toString());
                                    break;
                                case 6:
                                    s.expression(w6, sqlValue.value.toString());
                                    break;
                                case 7:
                                    s.expression(w7, sqlValue.value.toString());
                                    break;
                                case 8:
                                    s.expression(w8, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable8)
        {
            ClientQueryable8<?, ?, ?, ?, ?, ?, ?, ?> queryable = (ClientQueryable8<?, ?, ?, ?, ?, ?, ?, ?>) clientQueryable;
            queryable.orderByAsc((w0, w1, w2, w3, w4, w5, w6, w7) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                                case 3:
                                    s.expression(w3, sqlValue.value.toString());
                                    break;
                                case 4:
                                    s.expression(w4, sqlValue.value.toString());
                                    break;
                                case 5:
                                    s.expression(w5, sqlValue.value.toString());
                                    break;
                                case 6:
                                    s.expression(w6, sqlValue.value.toString());
                                    break;
                                case 7:
                                    s.expression(w7, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable7)
        {
            ClientQueryable7<?, ?, ?, ?, ?, ?, ?> queryable = (ClientQueryable7<?, ?, ?, ?, ?, ?, ?>) clientQueryable;
            queryable.orderByAsc((w0, w1, w2, w3, w4, w5, w6) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                                case 3:
                                    s.expression(w3, sqlValue.value.toString());
                                    break;
                                case 4:
                                    s.expression(w4, sqlValue.value.toString());
                                    break;
                                case 5:
                                    s.expression(w5, sqlValue.value.toString());
                                    break;
                                case 6:
                                    s.expression(w6, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable6)
        {
            ClientQueryable6<?, ?, ?, ?, ?, ?> queryable = (ClientQueryable6<?, ?, ?, ?, ?, ?>) clientQueryable;
            queryable.orderByAsc((w0, w1, w2, w3, w4, w5) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                                case 3:
                                    s.expression(w3, sqlValue.value.toString());
                                    break;
                                case 4:
                                    s.expression(w4, sqlValue.value.toString());
                                    break;
                                case 5:
                                    s.expression(w5, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable5)
        {
            ClientQueryable5<?, ?, ?, ?, ?> queryable = (ClientQueryable5<?, ?, ?, ?, ?>) clientQueryable;
            queryable.orderByAsc((w0, w1, w2, w3, w4) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                                case 3:
                                    s.expression(w3, sqlValue.value.toString());
                                    break;
                                case 4:
                                    s.expression(w4, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable4)
        {
            ClientQueryable4<?, ?, ?, ?> queryable = (ClientQueryable4<?, ?, ?, ?>) clientQueryable;
            queryable.orderByAsc((w0, w1, w2, w3) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                                case 3:
                                    s.expression(w3, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable3)
        {
            ClientQueryable3<?, ?, ?> queryable = (ClientQueryable3<?, ?, ?>) clientQueryable;
            queryable.orderByAsc((w0, w1, w2) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable2)
        {
            ClientQueryable2<?, ?> queryable = (ClientQueryable2<?, ?>) clientQueryable;
            queryable.orderByAsc((w0, w1) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else
        {
            clientQueryable.orderByAsc(w0 -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            if (sqlValue.index == 0)
                            {
                                s.expression(w0, sqlValue.value.toString());
                            }
                            break;
                    }
                }
            }));
        }
    }

    protected void analysisWhere(ClientQueryable<?> clientQueryable, QueryData queryData, String sql, List<SqlValue> sqlValues)
    {
        if (clientQueryable instanceof ClientQueryable10)
        {
            ClientQueryable10<?, ?, ?, ?, ?, ?, ?, ?, ?, ?> queryable = (ClientQueryable10<?, ?, ?, ?, ?, ?, ?, ?, ?, ?>) clientQueryable;
            queryable.where((w0, w1, w2, w3, w4, w5, w6, w7, w8, w9) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                                case 3:
                                    s.expression(w3, sqlValue.value.toString());
                                    break;
                                case 4:
                                    s.expression(w4, sqlValue.value.toString());
                                    break;
                                case 5:
                                    s.expression(w5, sqlValue.value.toString());
                                    break;
                                case 6:
                                    s.expression(w6, sqlValue.value.toString());
                                    break;
                                case 7:
                                    s.expression(w7, sqlValue.value.toString());
                                    break;
                                case 8:
                                    s.expression(w8, sqlValue.value.toString());
                                    break;
                                case 9:
                                    s.expression(w9, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable9)
        {
            ClientQueryable9<?, ?, ?, ?, ?, ?, ?, ?, ?> queryable = (ClientQueryable9<?, ?, ?, ?, ?, ?, ?, ?, ?>) clientQueryable;
            queryable.where((w0, w1, w2, w3, w4, w5, w6, w7, w8) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                                case 3:
                                    s.expression(w3, sqlValue.value.toString());
                                    break;
                                case 4:
                                    s.expression(w4, sqlValue.value.toString());
                                    break;
                                case 5:
                                    s.expression(w5, sqlValue.value.toString());
                                    break;
                                case 6:
                                    s.expression(w6, sqlValue.value.toString());
                                    break;
                                case 7:
                                    s.expression(w7, sqlValue.value.toString());
                                    break;
                                case 8:
                                    s.expression(w8, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable8)
        {
            ClientQueryable8<?, ?, ?, ?, ?, ?, ?, ?> queryable = (ClientQueryable8<?, ?, ?, ?, ?, ?, ?, ?>) clientQueryable;
            queryable.where((w0, w1, w2, w3, w4, w5, w6, w7) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                                case 3:
                                    s.expression(w3, sqlValue.value.toString());
                                    break;
                                case 4:
                                    s.expression(w4, sqlValue.value.toString());
                                    break;
                                case 5:
                                    s.expression(w5, sqlValue.value.toString());
                                    break;
                                case 6:
                                    s.expression(w6, sqlValue.value.toString());
                                    break;
                                case 7:
                                    s.expression(w7, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable7)
        {
            ClientQueryable7<?, ?, ?, ?, ?, ?, ?> queryable = (ClientQueryable7<?, ?, ?, ?, ?, ?, ?>) clientQueryable;
            queryable.where((w0, w1, w2, w3, w4, w5, w6) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                                case 3:
                                    s.expression(w3, sqlValue.value.toString());
                                    break;
                                case 4:
                                    s.expression(w4, sqlValue.value.toString());
                                    break;
                                case 5:
                                    s.expression(w5, sqlValue.value.toString());
                                    break;
                                case 6:
                                    s.expression(w6, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable6)
        {
            ClientQueryable6<?, ?, ?, ?, ?, ?> queryable = (ClientQueryable6<?, ?, ?, ?, ?, ?>) clientQueryable;
            queryable.where((w0, w1, w2, w3, w4, w5) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                                case 3:
                                    s.expression(w3, sqlValue.value.toString());
                                    break;
                                case 4:
                                    s.expression(w4, sqlValue.value.toString());
                                    break;
                                case 5:
                                    s.expression(w5, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable5)
        {
            ClientQueryable5<?, ?, ?, ?, ?> queryable = (ClientQueryable5<?, ?, ?, ?, ?>) clientQueryable;
            queryable.where((w0, w1, w2, w3, w4) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                                case 3:
                                    s.expression(w3, sqlValue.value.toString());
                                    break;
                                case 4:
                                    s.expression(w4, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable4)
        {
            ClientQueryable4<?, ?, ?, ?> queryable = (ClientQueryable4<?, ?, ?, ?>) clientQueryable;
            queryable.where((w0, w1, w2, w3) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                                case 3:
                                    s.expression(w3, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable3)
        {
            ClientQueryable3<?, ?, ?> queryable = (ClientQueryable3<?, ?, ?>) clientQueryable;
            queryable.where((w0, w1, w2) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                                case 2:
                                    s.expression(w2, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else if (clientQueryable instanceof ClientQueryable2)
        {
            ClientQueryable2<?, ?> queryable = (ClientQueryable2<?, ?>) clientQueryable;
            queryable.where((w0, w1) -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            switch (sqlValue.index)
                            {
                                case 0:
                                    s.expression(w0, sqlValue.value.toString());
                                    break;
                                case 1:
                                    s.expression(w1, sqlValue.value.toString());
                                    break;
                            }
                            break;
                    }
                }
            }));
        }
        else
        {
            clientQueryable.where(w0 -> w0.sqlNativeSegment(sql, s ->
            {
                for (SqlValue sqlValue : sqlValues)
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            s.value(sqlValue.value);
                            break;
                        case property:
                            if (sqlValue.index == 0)
                            {
                                s.expression(w0, sqlValue.value.toString());
                            }
                            break;
                    }
                }
            }));
        }
    }

    protected List<EntitySQLTableOwner<?>> getOwners(ClientQueryable<?> queryable)
    {
        if (queryable instanceof ClientQueryable10)
        {
            ClientQueryable10 c = (ClientQueryable10) queryable;
            SQLExpressionProvider<?> s1 = c.getSQLExpressionProvider1();
            SQLExpressionProvider<?> s2 = c.getSQLExpressionProvider2();
            SQLExpressionProvider<?> s3 = c.getSQLExpressionProvider3();
            SQLExpressionProvider<?> s4 = c.getSQLExpressionProvider4();
            SQLExpressionProvider<?> s5 = c.getSQLExpressionProvider5();
            SQLExpressionProvider<?> s6 = c.getSQLExpressionProvider6();
            SQLExpressionProvider<?> s7 = c.getSQLExpressionProvider7();
            SQLExpressionProvider<?> s8 = c.getSQLExpressionProvider8();
            SQLExpressionProvider<?> s9 = c.getSQLExpressionProvider9();
            SQLExpressionProvider<?> s10 = c.getSQLExpressionProvider10();
            WherePredicate<?> w1 = s1.getWherePredicate(s1.getWhereFilterContext());
            WherePredicate<?> w2 = s2.getWherePredicate(s2.getWhereFilterContext());
            WherePredicate<?> w3 = s3.getWherePredicate(s3.getWhereFilterContext());
            WherePredicate<?> w4 = s4.getWherePredicate(s4.getWhereFilterContext());
            WherePredicate<?> w5 = s5.getWherePredicate(s5.getWhereFilterContext());
            WherePredicate<?> w6 = s2.getWherePredicate(s6.getWhereFilterContext());
            WherePredicate<?> w7 = s7.getWherePredicate(s7.getWhereFilterContext());
            WherePredicate<?> w8 = s8.getWherePredicate(s8.getWhereFilterContext());
            WherePredicate<?> w9 = s9.getWherePredicate(s9.getWhereFilterContext());
            WherePredicate<?> w10 = s10.getWherePredicate(s10.getWhereFilterContext());
            List<EntitySQLTableOwner<?>> owners=new ArrayList<>(10);
            owners.add(w1);
            owners.add(w2);
            owners.add(w3);
            owners.add(w4);
            owners.add(w5);
            owners.add(w6);
            owners.add(w7);
            owners.add(w8);
            owners.add(w9);
            owners.add(w10);
            return owners;
        }
        else if (queryable instanceof ClientQueryable9)
        {
            ClientQueryable9 c = (ClientQueryable9) queryable;
            SQLExpressionProvider<?> s1 = c.getSQLExpressionProvider1();
            SQLExpressionProvider<?> s2 = c.getSQLExpressionProvider2();
            SQLExpressionProvider<?> s3 = c.getSQLExpressionProvider3();
            SQLExpressionProvider<?> s4 = c.getSQLExpressionProvider4();
            SQLExpressionProvider<?> s5 = c.getSQLExpressionProvider5();
            SQLExpressionProvider<?> s6 = c.getSQLExpressionProvider6();
            SQLExpressionProvider<?> s7 = c.getSQLExpressionProvider7();
            SQLExpressionProvider<?> s8 = c.getSQLExpressionProvider8();
            SQLExpressionProvider<?> s9 = c.getSQLExpressionProvider9();
            WherePredicate<?> w1 = s1.getWherePredicate(s1.getWhereFilterContext());
            WherePredicate<?> w2 = s2.getWherePredicate(s2.getWhereFilterContext());
            WherePredicate<?> w3 = s3.getWherePredicate(s3.getWhereFilterContext());
            WherePredicate<?> w4 = s4.getWherePredicate(s4.getWhereFilterContext());
            WherePredicate<?> w5 = s5.getWherePredicate(s5.getWhereFilterContext());
            WherePredicate<?> w6 = s2.getWherePredicate(s6.getWhereFilterContext());
            WherePredicate<?> w7 = s7.getWherePredicate(s7.getWhereFilterContext());
            WherePredicate<?> w8 = s8.getWherePredicate(s8.getWhereFilterContext());
            WherePredicate<?> w9 = s9.getWherePredicate(s9.getWhereFilterContext());
            List<EntitySQLTableOwner<?>> owners=new ArrayList<>(9);
            owners.add(w1);
            owners.add(w2);
            owners.add(w3);
            owners.add(w4);
            owners.add(w5);
            owners.add(w6);
            owners.add(w7);
            owners.add(w8);
            owners.add(w9);
            return owners;
        }
        else if (queryable instanceof ClientQueryable8)
        {
            ClientQueryable8 c = (ClientQueryable8) queryable;
            SQLExpressionProvider<?> s1 = c.getSQLExpressionProvider1();
            SQLExpressionProvider<?> s2 = c.getSQLExpressionProvider2();
            SQLExpressionProvider<?> s3 = c.getSQLExpressionProvider3();
            SQLExpressionProvider<?> s4 = c.getSQLExpressionProvider4();
            SQLExpressionProvider<?> s5 = c.getSQLExpressionProvider5();
            SQLExpressionProvider<?> s6 = c.getSQLExpressionProvider6();
            SQLExpressionProvider<?> s7 = c.getSQLExpressionProvider7();
            SQLExpressionProvider<?> s8 = c.getSQLExpressionProvider8();
            WherePredicate<?> w1 = s1.getWherePredicate(s1.getWhereFilterContext());
            WherePredicate<?> w2 = s2.getWherePredicate(s2.getWhereFilterContext());
            WherePredicate<?> w3 = s3.getWherePredicate(s3.getWhereFilterContext());
            WherePredicate<?> w4 = s4.getWherePredicate(s4.getWhereFilterContext());
            WherePredicate<?> w5 = s5.getWherePredicate(s5.getWhereFilterContext());
            WherePredicate<?> w6 = s2.getWherePredicate(s6.getWhereFilterContext());
            WherePredicate<?> w7 = s7.getWherePredicate(s7.getWhereFilterContext());
            WherePredicate<?> w8 = s8.getWherePredicate(s8.getWhereFilterContext());
            List<EntitySQLTableOwner<?>> owners=new ArrayList<>(8);
            owners.add(w1);
            owners.add(w2);
            owners.add(w3);
            owners.add(w4);
            owners.add(w5);
            owners.add(w6);
            owners.add(w7);
            owners.add(w8);
            return owners;
        }
        else if (queryable instanceof ClientQueryable7)
        {
            ClientQueryable7 c = (ClientQueryable7) queryable;
            SQLExpressionProvider<?> s1 = c.getSQLExpressionProvider1();
            SQLExpressionProvider<?> s2 = c.getSQLExpressionProvider2();
            SQLExpressionProvider<?> s3 = c.getSQLExpressionProvider3();
            SQLExpressionProvider<?> s4 = c.getSQLExpressionProvider4();
            SQLExpressionProvider<?> s5 = c.getSQLExpressionProvider5();
            SQLExpressionProvider<?> s6 = c.getSQLExpressionProvider6();
            SQLExpressionProvider<?> s7 = c.getSQLExpressionProvider7();
            WherePredicate<?> w1 = s1.getWherePredicate(s1.getWhereFilterContext());
            WherePredicate<?> w2 = s2.getWherePredicate(s2.getWhereFilterContext());
            WherePredicate<?> w3 = s3.getWherePredicate(s3.getWhereFilterContext());
            WherePredicate<?> w4 = s4.getWherePredicate(s4.getWhereFilterContext());
            WherePredicate<?> w5 = s5.getWherePredicate(s5.getWhereFilterContext());
            WherePredicate<?> w6 = s2.getWherePredicate(s6.getWhereFilterContext());
            WherePredicate<?> w7 = s7.getWherePredicate(s7.getWhereFilterContext());
            List<EntitySQLTableOwner<?>> owners=new ArrayList<>(7);
            owners.add(w1);
            owners.add(w2);
            owners.add(w3);
            owners.add(w4);
            owners.add(w5);
            owners.add(w6);
            owners.add(w7);
            return owners;
        }
        else if (queryable instanceof ClientQueryable6)
        {
            ClientQueryable6 c = (ClientQueryable6) queryable;
            SQLExpressionProvider<?> s1 = c.getSQLExpressionProvider1();
            SQLExpressionProvider<?> s2 = c.getSQLExpressionProvider2();
            SQLExpressionProvider<?> s3 = c.getSQLExpressionProvider3();
            SQLExpressionProvider<?> s4 = c.getSQLExpressionProvider4();
            SQLExpressionProvider<?> s5 = c.getSQLExpressionProvider5();
            SQLExpressionProvider<?> s6 = c.getSQLExpressionProvider6();
            WherePredicate<?> w1 = s1.getWherePredicate(s1.getWhereFilterContext());
            WherePredicate<?> w2 = s2.getWherePredicate(s2.getWhereFilterContext());
            WherePredicate<?> w3 = s3.getWherePredicate(s3.getWhereFilterContext());
            WherePredicate<?> w4 = s4.getWherePredicate(s4.getWhereFilterContext());
            WherePredicate<?> w5 = s5.getWherePredicate(s5.getWhereFilterContext());
            WherePredicate<?> w6 = s2.getWherePredicate(s6.getWhereFilterContext());
            List<EntitySQLTableOwner<?>> owners=new ArrayList<>(6);
            owners.add(w1);
            owners.add(w2);
            owners.add(w3);
            owners.add(w4);
            owners.add(w5);
            owners.add(w6);
            return owners;
        }
        else if (queryable instanceof ClientQueryable5)
        {
            ClientQueryable5 c = (ClientQueryable5) queryable;
            SQLExpressionProvider<?> s1 = c.getSQLExpressionProvider1();
            SQLExpressionProvider<?> s2 = c.getSQLExpressionProvider2();
            SQLExpressionProvider<?> s3 = c.getSQLExpressionProvider3();
            SQLExpressionProvider<?> s4 = c.getSQLExpressionProvider4();
            SQLExpressionProvider<?> s5 = c.getSQLExpressionProvider5();
            WherePredicate<?> w1 = s1.getWherePredicate(s1.getWhereFilterContext());
            WherePredicate<?> w2 = s2.getWherePredicate(s2.getWhereFilterContext());
            WherePredicate<?> w3 = s3.getWherePredicate(s3.getWhereFilterContext());
            WherePredicate<?> w4 = s4.getWherePredicate(s4.getWhereFilterContext());
            WherePredicate<?> w5 = s5.getWherePredicate(s5.getWhereFilterContext());
            List<EntitySQLTableOwner<?>> owners=new ArrayList<>(5);
            owners.add(w1);
            owners.add(w2);
            owners.add(w3);
            owners.add(w4);
            owners.add(w5);
            return owners;
        }
        else if (queryable instanceof ClientQueryable4)
        {
            ClientQueryable4 c = (ClientQueryable4) queryable;
            SQLExpressionProvider<?> s1 = c.getSQLExpressionProvider1();
            SQLExpressionProvider<?> s2 = c.getSQLExpressionProvider2();
            SQLExpressionProvider<?> s3 = c.getSQLExpressionProvider3();
            SQLExpressionProvider<?> s4 = c.getSQLExpressionProvider4();
            WherePredicate<?> w1 = s1.getWherePredicate(s1.getWhereFilterContext());
            WherePredicate<?> w2 = s2.getWherePredicate(s2.getWhereFilterContext());
            WherePredicate<?> w3 = s3.getWherePredicate(s3.getWhereFilterContext());
            WherePredicate<?> w4 = s4.getWherePredicate(s4.getWhereFilterContext());
            List<EntitySQLTableOwner<?>> owners=new ArrayList<>(4);
            owners.add(w1);
            owners.add(w2);
            owners.add(w3);
            owners.add(w4);
            return owners;
        }
        else if (queryable instanceof ClientQueryable3)
        {
            ClientQueryable3 c = (ClientQueryable3) queryable;
            SQLExpressionProvider<?> s1 = c.getSQLExpressionProvider1();
            SQLExpressionProvider<?> s2 = c.getSQLExpressionProvider2();
            SQLExpressionProvider<?> s3 = c.getSQLExpressionProvider3();
            WherePredicate<?> w1 = s1.getWherePredicate(s1.getWhereFilterContext());
            WherePredicate<?> w2 = s2.getWherePredicate(s2.getWhereFilterContext());
            WherePredicate<?> w3 = s3.getWherePredicate(s3.getWhereFilterContext());
            List<EntitySQLTableOwner<?>> owners=new ArrayList<>(3);
            owners.add(w1);
            owners.add(w2);
            owners.add(w3);
            return owners;
        }
        else if (queryable instanceof ClientQueryable2)
        {
            ClientQueryable2 c = (ClientQueryable2) queryable;
            SQLExpressionProvider<?> s1 = c.getSQLExpressionProvider1();
            SQLExpressionProvider<?> s2 = c.getSQLExpressionProvider2();
            WherePredicate<?> w1 = s1.getWherePredicate(s1.getWhereFilterContext());
            WherePredicate<?> w2 = s2.getWherePredicate(s2.getWhereFilterContext());
            List<EntitySQLTableOwner<?>> owners=new ArrayList<>(2);
            owners.add(w1);
            owners.add(w2);
            return owners;
        }
        else
        {
            SQLExpressionProvider<?> s1 = queryable.getSQLExpressionProvider1();
            WherePredicate<?> w1 = s1.getWherePredicate(s1.getWhereFilterContext());
            return Collections.singletonList(w1);
        }
    }
}
