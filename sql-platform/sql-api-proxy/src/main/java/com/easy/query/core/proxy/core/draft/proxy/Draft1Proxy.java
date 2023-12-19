package com.easy.query.core.proxy.core.draft.proxy;

import com.easy.query.core.proxy.AbstractProxyEntity;
import com.easy.query.core.proxy.SQLColumn;
import com.easy.query.core.proxy.SQLSelectAsExpression;
import com.easy.query.core.proxy.core.draft.Draft1;
import com.easy.query.core.proxy.fetcher.AbstractFetcher;
import com.easy.query.core.util.EasyObjectUtil;

/**
 * this file automatically generated by easy-query, don't modify it
 * 当前文件是easy-query自动生成的请不要随意修改
 *
 * @author xuejiaming
 */
public class Draft1Proxy<T1> extends AbstractProxyEntity<Draft1Proxy<T1>, Draft1<T1>> {

    private static final Class<Draft1> entityClass = Draft1.class;

    public static <T> Draft1Proxy<T> createTable() {
        return new Draft1Proxy<>();
    }

    public Draft1Proxy() {
    }

    /**
     * {@link Draft1#getValue1}
     */
    public SQLColumn<Draft1Proxy<T1>, T1> value1() {
        return get("value1");
    }


    @Override
    public Class<Draft1<T1>> getEntityClass() {
        return EasyObjectUtil.typeCastNullable(entityClass);
    }


    /**
     * 数据库列的简单获取
     *
     * @return
     */
    public Draft1ProxyFetcher<T1> FETCHER = new Draft1ProxyFetcher<>(this, null, SQLSelectAsExpression.empty);


    public static class Draft1ProxyFetcher<TF1> extends AbstractFetcher<Draft1Proxy<TF1>, Draft1<TF1>, Draft1ProxyFetcher<TF1>> {

        public Draft1ProxyFetcher(Draft1Proxy<TF1> proxy, Draft1ProxyFetcher<TF1> prev, SQLSelectAsExpression sqlSelectAsExpression) {
            super(proxy, prev, sqlSelectAsExpression);
        }


        /**
         * {@link Draft1#getValue1}
         */
        public Draft1ProxyFetcher<TF1> value1() {
            return add(getProxy().value1());
        }


        @Override
        protected Draft1ProxyFetcher<TF1> createFetcher(
                Draft1Proxy<TF1> cp,
                AbstractFetcher<Draft1Proxy<TF1>, Draft1<TF1>, Draft1ProxyFetcher<TF1>> prev,
                SQLSelectAsExpression sqlSelectExpression
        ) {
            return new Draft1ProxyFetcher<>(cp, this, sqlSelectExpression);
        }
    }

}
