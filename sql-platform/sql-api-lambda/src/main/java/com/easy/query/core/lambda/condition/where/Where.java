package com.easy.query.core.lambda.condition.where;

import com.easy.query.api.lambda.crud.read.QueryData;
import com.easy.query.core.basic.api.delete.ClientExpressionDeletable;
import com.easy.query.core.basic.api.select.ClientQueryable;
import com.easy.query.core.basic.api.select.ClientQueryable2;
import com.easy.query.core.basic.api.select.provider.SQLExpressionProvider;
import com.easy.query.core.basic.api.update.ClientExpressionUpdatable;
import com.easy.query.core.expression.parser.core.EntitySQLTableOwner;
import com.easy.query.core.expression.parser.core.SQLTableOwner;
import com.easy.query.core.expression.parser.core.base.WherePredicate;
import com.easy.query.core.lambda.condition.criteria.Criteria;
import com.easy.query.core.lambda.visitor.QueryWhereVisitor;
import com.easy.query.core.lambda.visitor.SqlValue;
import com.easy.query.core.lambda.visitor.WhereVisitor;
import io.github.kiryu1223.expressionTree.expressions.LambdaExpression;

import java.util.ArrayList;
import java.util.Arrays;
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
        WhereVisitor where = new WhereVisitor(expression.getParameters(), queryData.getDbType());
        expression.getBody().accept(where);
        deletable.where(w -> w.sqlNativeSegment(where.getData(), s ->
        {
            for (SqlValue sqlValue : where.getSqlValue())
            {
                switch (sqlValue.type)
                {
                    case value:
                        s.value(sqlValue.value);
                        break;
                    case property:
                        s.expression(sqlValue.value.toString());
                        break;
                }
            }
        }));
    }

    public void analysis(ClientExpressionUpdatable<?> updatable, QueryData queryData)
    {
        WhereVisitor where = new WhereVisitor(expression.getParameters(), queryData.getDbType());
        expression.getBody().accept(where);
        updatable.where(w -> w.sqlNativeSegment(where.getData(), s ->
        {
            for (SqlValue sqlValue : where.getSqlValue())
            {
                switch (sqlValue.type)
                {
                    case value:
                        s.value(sqlValue.value);
                        break;
                    case property:
                        s.expression(sqlValue.value.toString());
                        break;
                }
            }
        }));
    }

    public void analysis(ClientQueryable<?> queryable, QueryData queryData)
    {
        SQLExpressionProvider<?> sqlExpressionProvider1 = queryable.getSQLExpressionProvider1();
        WherePredicate<?> wherePredicate1 = sqlExpressionProvider1.getWherePredicate(sqlExpressionProvider1.getWhereFilterContext());
        List<EntitySQLTableOwner<?>> owners = Arrays.asList(wherePredicate1);
        QueryWhereVisitor queryWhereVisitor = new QueryWhereVisitor(queryable, owners);
        queryWhereVisitor.visit(expression);
    }

    public void analysis(ClientQueryable2<?,?> queryable, QueryData queryData)
    {
        SQLExpressionProvider<?> sqlExpressionProvider1 = queryable.getSQLExpressionProvider1();
        SQLExpressionProvider<?> sqlExpressionProvider2 = queryable.getSQLExpressionProvider2();
        WherePredicate<?> wherePredicate1 = sqlExpressionProvider1.getWherePredicate(sqlExpressionProvider1.getWhereFilterContext());
        WherePredicate<?> wherePredicate2 = sqlExpressionProvider2.getWherePredicate(sqlExpressionProvider2.getWhereFilterContext());
        List<EntitySQLTableOwner<?>> owners = Arrays.asList(wherePredicate1, wherePredicate2);
        QueryWhereVisitor queryWhereVisitor = new QueryWhereVisitor(queryable, owners);
        queryWhereVisitor.visit(expression);
    }
}
