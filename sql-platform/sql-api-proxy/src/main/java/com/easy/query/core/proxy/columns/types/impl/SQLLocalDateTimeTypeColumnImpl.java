package com.easy.query.core.proxy.columns.types.impl;

import com.easy.query.core.expression.parser.core.available.TableAvailable;
import com.easy.query.core.proxy.columns.types.SQLLocalDateTimeTypeColumn;
import com.easy.query.core.proxy.core.EntitySQLContext;
import com.easy.query.core.proxy.impl.SQLColumnImpl;

import java.time.LocalDateTime;

/**
 * create time 2023/12/24 00:12
 * 文件说明
 *
 * @author xuejiaming
 */
public class SQLLocalDateTimeTypeColumnImpl<TProxy> extends SQLColumnImpl<TProxy, LocalDateTime> implements SQLLocalDateTimeTypeColumn<TProxy> {
    public SQLLocalDateTimeTypeColumnImpl(EntitySQLContext entitySQLContext, TableAvailable table, String property) {
        super(entitySQLContext, table, property, LocalDateTime.class);
    }

    @Override
    public TableAvailable getTableOrNull() {
        return table;
    }

    @Override
    public Class<LocalDateTime> getEntityClass() {
        return LocalDateTime.class;
    }

    @Override
    public SQLLocalDateTimeTypeColumn<TProxy> create(TableAvailable table, EntitySQLContext entitySQLContext) {
        return new SQLLocalDateTimeTypeColumnImpl<>(entitySQLContext,table,property);
    }
}
