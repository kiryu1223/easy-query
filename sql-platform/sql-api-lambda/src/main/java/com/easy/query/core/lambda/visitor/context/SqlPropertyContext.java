package com.easy.query.core.lambda.visitor.context;

import com.easy.query.core.expression.builder.Filter;
import com.easy.query.core.expression.parser.core.EntitySQLTableOwner;
import com.easy.query.core.expression.parser.core.SQLTableOwner;
import com.easy.query.core.expression.parser.core.base.WherePredicate;

public class SqlPropertyContext extends SqlContext
{
    private final String property;
    private final EntitySQLTableOwner<?> tableOwner;

    public SqlPropertyContext(String property, EntitySQLTableOwner<?> tableOwner)
    {
        this.property = property;
        this.tableOwner = tableOwner;
    }

    public String getProperty()
    {
        return property;
    }

    public EntitySQLTableOwner<?> getTableOwner()
    {
        return tableOwner;
    }

    @Override
    public void revWhere(WherePredicate<?> wherePredicate)
    {
        Filter filter = wherePredicate.getFilter();
        filter.eq(tableOwner.getTable(),property,true);
    }
}
