package com.easy.query.core.lambda.condition.having;

import com.easy.query.api.lambda.crud.read.QueryData;
import com.easy.query.core.basic.api.select.*;
import com.easy.query.core.enums.AggregatePredicateCompare;
import com.easy.query.core.expression.builder.AggregateFilter;
import com.easy.query.core.expression.func.ColumnFunction;
import com.easy.query.core.expression.parser.core.base.WhereAggregatePredicate;
import com.easy.query.core.lambda.condition.criteria.Criteria;
import com.easy.query.core.lambda.visitor.HavingVisitorV2;
import io.github.kiryu1223.expressionTree.expressions.LambdaExpression;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Having extends Criteria
{
    private final LambdaExpression<?> expression;

    public Having(LambdaExpression<?> expression)
    {
        checkExprBody(expression);
        this.expression = expression;
    }

    public void analysis(ClientQueryable<?> queryable, QueryData queryData)
    {
//        HavingVisitor having = new HavingVisitor(expression.getParameters(), queryData);
//        expression.getBody().accept(having);
//        analysisHaving(queryable,queryData,having.getData(),having.getSqlValue());
        List<WhereAggregatePredicate<?>> whereAggregatePredicates = getWhereAggregatePredicates(queryable);

        HavingVisitorV2 havingVisitorV2 = new HavingVisitorV2(whereAggregatePredicates);
        havingVisitorV2.visit(expression);
    }

    private List<WhereAggregatePredicate<?>> getWhereAggregatePredicates(ClientQueryable<?> queryable)
    {
        if (queryable instanceof ClientQueryable10)
        {
            ClientQueryable10 clientQueryable = (ClientQueryable10) queryable;
            WhereAggregatePredicate<?> g1 = clientQueryable.getSQLExpressionProvider1().getAggregatePredicate();
            WhereAggregatePredicate<?> g2 = clientQueryable.getSQLExpressionProvider2().getAggregatePredicate();
            WhereAggregatePredicate<?> g3 = clientQueryable.getSQLExpressionProvider3().getAggregatePredicate();
            WhereAggregatePredicate<?> g4 = clientQueryable.getSQLExpressionProvider4().getAggregatePredicate();
            WhereAggregatePredicate<?> g5 = clientQueryable.getSQLExpressionProvider5().getAggregatePredicate();
            WhereAggregatePredicate<?> g6 = clientQueryable.getSQLExpressionProvider6().getAggregatePredicate();
            WhereAggregatePredicate<?> g7 = clientQueryable.getSQLExpressionProvider7().getAggregatePredicate();
            WhereAggregatePredicate<?> g8 = clientQueryable.getSQLExpressionProvider8().getAggregatePredicate();
            WhereAggregatePredicate<?> g9 = clientQueryable.getSQLExpressionProvider9().getAggregatePredicate();
            WhereAggregatePredicate<?> g10 = clientQueryable.getSQLExpressionProvider10().getAggregatePredicate();
            return Arrays.asList(g1, g2, g3, g4, g5, g6, g7, g8, g9, g10);
        }
        else if (queryable instanceof ClientQueryable9)
        {
            ClientQueryable9 clientQueryable = (ClientQueryable9) queryable;
            WhereAggregatePredicate<?> g1 = clientQueryable.getSQLExpressionProvider1().getAggregatePredicate();
            WhereAggregatePredicate<?> g2 = clientQueryable.getSQLExpressionProvider2().getAggregatePredicate();
            WhereAggregatePredicate<?> g3 = clientQueryable.getSQLExpressionProvider3().getAggregatePredicate();
            WhereAggregatePredicate<?> g4 = clientQueryable.getSQLExpressionProvider4().getAggregatePredicate();
            WhereAggregatePredicate<?> g5 = clientQueryable.getSQLExpressionProvider5().getAggregatePredicate();
            WhereAggregatePredicate<?> g6 = clientQueryable.getSQLExpressionProvider6().getAggregatePredicate();
            WhereAggregatePredicate<?> g7 = clientQueryable.getSQLExpressionProvider7().getAggregatePredicate();
            WhereAggregatePredicate<?> g8 = clientQueryable.getSQLExpressionProvider8().getAggregatePredicate();
            WhereAggregatePredicate<?> g9 = clientQueryable.getSQLExpressionProvider9().getAggregatePredicate();
            return Arrays.asList(g1, g2, g3, g4, g5, g6, g7, g8, g9);
        }
        else if (queryable instanceof ClientQueryable8)
        {
            ClientQueryable8 clientQueryable = (ClientQueryable8) queryable;
            WhereAggregatePredicate<?> g1 = clientQueryable.getSQLExpressionProvider1().getAggregatePredicate();
            WhereAggregatePredicate<?> g2 = clientQueryable.getSQLExpressionProvider2().getAggregatePredicate();
            WhereAggregatePredicate<?> g3 = clientQueryable.getSQLExpressionProvider3().getAggregatePredicate();
            WhereAggregatePredicate<?> g4 = clientQueryable.getSQLExpressionProvider4().getAggregatePredicate();
            WhereAggregatePredicate<?> g5 = clientQueryable.getSQLExpressionProvider5().getAggregatePredicate();
            WhereAggregatePredicate<?> g6 = clientQueryable.getSQLExpressionProvider6().getAggregatePredicate();
            WhereAggregatePredicate<?> g7 = clientQueryable.getSQLExpressionProvider7().getAggregatePredicate();
            WhereAggregatePredicate<?> g8 = clientQueryable.getSQLExpressionProvider8().getAggregatePredicate();
            return Arrays.asList(g1, g2, g3, g4, g5, g6, g7, g8);
        }
        else if (queryable instanceof ClientQueryable7)
        {
            ClientQueryable7 clientQueryable = (ClientQueryable7) queryable;
            WhereAggregatePredicate<?> g1 = clientQueryable.getSQLExpressionProvider1().getAggregatePredicate();
            WhereAggregatePredicate<?> g2 = clientQueryable.getSQLExpressionProvider2().getAggregatePredicate();
            WhereAggregatePredicate<?> g3 = clientQueryable.getSQLExpressionProvider3().getAggregatePredicate();
            WhereAggregatePredicate<?> g4 = clientQueryable.getSQLExpressionProvider4().getAggregatePredicate();
            WhereAggregatePredicate<?> g5 = clientQueryable.getSQLExpressionProvider5().getAggregatePredicate();
            WhereAggregatePredicate<?> g6 = clientQueryable.getSQLExpressionProvider6().getAggregatePredicate();
            WhereAggregatePredicate<?> g7 = clientQueryable.getSQLExpressionProvider7().getAggregatePredicate();
            return Arrays.asList(g1, g2, g3, g4, g5, g6, g7);
        }
        else if (queryable instanceof ClientQueryable6)
        {
            ClientQueryable6 clientQueryable = (ClientQueryable6) queryable;
            WhereAggregatePredicate<?> g1 = clientQueryable.getSQLExpressionProvider1().getAggregatePredicate();
            WhereAggregatePredicate<?> g2 = clientQueryable.getSQLExpressionProvider2().getAggregatePredicate();
            WhereAggregatePredicate<?> g3 = clientQueryable.getSQLExpressionProvider3().getAggregatePredicate();
            WhereAggregatePredicate<?> g4 = clientQueryable.getSQLExpressionProvider4().getAggregatePredicate();
            WhereAggregatePredicate<?> g5 = clientQueryable.getSQLExpressionProvider5().getAggregatePredicate();
            WhereAggregatePredicate<?> g6 = clientQueryable.getSQLExpressionProvider6().getAggregatePredicate();
            return Arrays.asList(g1, g2, g3, g4, g5, g6);
        }
        else if (queryable instanceof ClientQueryable5)
        {
            ClientQueryable5 clientQueryable = (ClientQueryable5) queryable;
            WhereAggregatePredicate<?> g1 = clientQueryable.getSQLExpressionProvider1().getAggregatePredicate();
            WhereAggregatePredicate<?> g2 = clientQueryable.getSQLExpressionProvider2().getAggregatePredicate();
            WhereAggregatePredicate<?> g3 = clientQueryable.getSQLExpressionProvider3().getAggregatePredicate();
            WhereAggregatePredicate<?> g4 = clientQueryable.getSQLExpressionProvider4().getAggregatePredicate();
            WhereAggregatePredicate<?> g5 = clientQueryable.getSQLExpressionProvider5().getAggregatePredicate();
            return Arrays.asList(g1, g2, g3, g4, g5);
        }
        else if (queryable instanceof ClientQueryable4)
        {
            ClientQueryable4 clientQueryable = (ClientQueryable4) queryable;
            WhereAggregatePredicate<?> g1 = clientQueryable.getSQLExpressionProvider1().getAggregatePredicate();
            WhereAggregatePredicate<?> g2 = clientQueryable.getSQLExpressionProvider2().getAggregatePredicate();
            WhereAggregatePredicate<?> g3 = clientQueryable.getSQLExpressionProvider3().getAggregatePredicate();
            WhereAggregatePredicate<?> g4 = clientQueryable.getSQLExpressionProvider4().getAggregatePredicate();
            return Arrays.asList(g1, g2, g3, g4);
        }
        else if (queryable instanceof ClientQueryable3)
        {
            ClientQueryable3 clientQueryable = (ClientQueryable3) queryable;
            WhereAggregatePredicate<?> g1 = clientQueryable.getSQLExpressionProvider1().getAggregatePredicate();
            WhereAggregatePredicate<?> g2 = clientQueryable.getSQLExpressionProvider2().getAggregatePredicate();
            WhereAggregatePredicate<?> g3 = clientQueryable.getSQLExpressionProvider3().getAggregatePredicate();
            return Arrays.asList(g1, g2, g3);
        }
        else if (queryable instanceof ClientQueryable2)
        {
            ClientQueryable2 clientQueryable = (ClientQueryable2) queryable;
            WhereAggregatePredicate<?> g1 = clientQueryable.getSQLExpressionProvider1().getAggregatePredicate();
            WhereAggregatePredicate<?> g2 = clientQueryable.getSQLExpressionProvider2().getAggregatePredicate();
            return Arrays.asList(g1, g2);
        }
        else
        {
            WhereAggregatePredicate<?> g1 = queryable.getSQLExpressionProvider1().getAggregatePredicate();
            return Collections.singletonList(g1);
        }
    }
}
