package com.easy.query.core.proxy.columns.types;

import com.easy.query.core.proxy.ProxyEntity;
import com.easy.query.core.proxy.columns.SQLNumberColumn;

/**
 * create time 2024/4/26 13:21
 * 文件说明
 *
 * @author xuejiaming
 */
public interface SQLShortTypeColumn<TProxy> extends SQLNumberColumn<TProxy, Short>,
        ProxyEntity<SQLShortTypeColumn<TProxy>, Short> {
}
