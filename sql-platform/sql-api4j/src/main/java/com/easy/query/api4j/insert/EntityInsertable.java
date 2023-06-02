package com.easy.query.api4j.insert;

import com.easy.query.core.basic.api.insert.Insertable;

import java.util.Collection;

/**
 * create time 2023/6/2 16:14
 * 文件说明
 *
 * @author xuejiaming
 */
public interface EntityInsertable<T> extends Insertable<T, EntityInsertable<T>> {
    @Override
    EntityInsertable<T> insert(T entity);

    @Override
    EntityInsertable<T> insert(Collection<T> entities);
}
