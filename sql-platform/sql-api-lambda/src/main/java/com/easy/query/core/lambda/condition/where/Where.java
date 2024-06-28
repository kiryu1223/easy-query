package com.easy.query.core.lambda.condition.where;

import com.easy.query.api.lambda.crud.read.QueryData;
import com.easy.query.core.basic.api.delete.ClientExpressionDeletable;
import com.easy.query.core.basic.api.select.ClientQueryable;
import com.easy.query.core.basic.api.select.ClientQueryable10;
import com.easy.query.core.basic.api.select.ClientQueryable2;
import com.easy.query.core.basic.api.select.provider.SQLExpressionProvider;
import com.easy.query.core.basic.api.update.ClientExpressionUpdatable;
import com.easy.query.core.expression.builder.core.AnyValueFilter;
import com.easy.query.core.expression.builder.impl.FilterImpl;
import com.easy.query.core.expression.parser.core.EntitySQLTableOwner;
import com.easy.query.core.expression.parser.core.base.WherePredicate;
import com.easy.query.core.expression.parser.core.base.core.FilterContext;
import com.easy.query.core.expression.parser.core.base.impl.WherePredicateImpl;
import com.easy.query.core.expression.sql.builder.EntityDeleteExpressionBuilder;
import com.easy.query.core.expression.sql.builder.EntityTableExpressionBuilder;
import com.easy.query.core.expression.sql.builder.EntityUpdateExpressionBuilder;
import com.easy.query.core.lambda.condition.criteria.Criteria;
import com.easy.query.core.lambda.visitor.WhereVisitorV2;
import io.github.kiryu1223.expressionTree.expressions.LambdaExpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Where extends Criteria
{
    private final LambdaExpression<?> expression;

    public Where(LambdaExpression<?> expression)
    {
        checkExprBody(expression);
        this.expression = expression;
    }

    public void analysis(ClientExpressionDeletable<?> deletable, QueryData queryData)
    {
        EntityDeleteExpressionBuilder builder = deletable.getDeleteExpressionBuilder();
        EntityTableExpressionBuilder table = builder.getTable(0);
        WherePredicateImpl<?> wherePredicate = new WherePredicateImpl<>(table.getEntityTable(), new FilterContext(new FilterImpl(builder.getRuntimeContext(), builder.getExpressionContext(), builder.getWhere(), false, AnyValueFilter.DEFAULT)));

        List<EntitySQLTableOwner<?>> owners = Collections.singletonList(wherePredicate);
        WhereVisitorV2 whereVisitorV2 = new WhereVisitorV2(owners, wherePredicate);
        whereVisitorV2.visit(expression);
    }

    public void analysis(ClientExpressionUpdatable<?> updatable, QueryData queryData)
    {
        EntityUpdateExpressionBuilder builder = updatable.getUpdateExpressionBuilder();
        EntityTableExpressionBuilder table = builder.getTable(0);
        WherePredicate<?> wherePredicate = new WherePredicateImpl<>(table.getEntityTable(), new FilterContext(new FilterImpl(builder.getRuntimeContext(), builder.getExpressionContext(), builder.getWhere(), false, AnyValueFilter.DEFAULT)));

        List<EntitySQLTableOwner<?>> owners = Collections.singletonList(wherePredicate);
        WhereVisitorV2 whereVisitorV2 = new WhereVisitorV2(owners, wherePredicate);
        whereVisitorV2.visit(expression);
    }

    public void analysis(ClientQueryable<?> queryable, QueryData queryData)
    {
        SQLExpressionProvider<?> sqlExpressionProvider1 = queryable.getSQLExpressionProvider1();
        WherePredicate<?> wherePredicate = sqlExpressionProvider1.getWherePredicate(sqlExpressionProvider1.getWhereFilterContext());
        WhereVisitorV2 whereVisitorV2 = new WhereVisitorV2(getOwners(queryable), wherePredicate);
        whereVisitorV2.visit(expression);
    }
}
