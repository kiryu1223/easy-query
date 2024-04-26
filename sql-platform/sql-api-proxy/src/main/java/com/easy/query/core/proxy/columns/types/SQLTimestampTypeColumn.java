package com.easy.query.core.proxy.columns.types;

import com.easy.query.core.proxy.ProxyEntity;
import com.easy.query.core.proxy.columns.SQLDateTimeColumn;

import java.sql.Timestamp;

/**
 * create time 2024/4/26 13:21
 * 文件说明
 *
 * @author xuejiaming
 */
public interface SQLTimestampTypeColumn<TProxy> extends SQLDateTimeColumn<TProxy, Timestamp>,
        ProxyEntity<SQLTimestampTypeColumn<TProxy>, Timestamp> {
}
