package com.easy.query.core.lambda.condition.where;

import com.easy.query.api.lambda.crud.read.QueryData;
import com.easy.query.core.basic.api.delete.ClientExpressionDeletable;
import com.easy.query.core.basic.api.select.*;
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

    private List<EntitySQLTableOwner<?>> getOwners(ClientQueryable<?> queryable)
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
