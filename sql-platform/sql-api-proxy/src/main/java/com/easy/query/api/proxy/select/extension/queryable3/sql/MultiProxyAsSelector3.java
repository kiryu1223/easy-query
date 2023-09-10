package com.easy.query.api.proxy.select.extension.queryable3.sql;

import com.easy.query.api.proxy.sql.ProxyAsSelector;
import com.easy.query.core.proxy.ProxyEntity;

/**
 * create time 2023/9/9 22:25
 * 文件说明
 *
 * @author xuejiaming
 */
public interface MultiProxyAsSelector3<T1Proxy, T2Proxy, T3Proxy, TRProxy extends ProxyEntity<TRProxy, TR>, TR> extends ProxyAsSelector<TRProxy, TR> {
    /**
     * 第一张表
     *
     * @return
     */
    T1Proxy t();

    /**
     * 第二张表
     *
     * @return
     */
    T2Proxy t1();

    /**
     * 第三张表
     *
     * @return
     */
    T3Proxy t2();
}
