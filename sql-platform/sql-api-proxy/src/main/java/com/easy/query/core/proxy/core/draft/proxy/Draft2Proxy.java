package com.easy.query.core.proxy.core.draft.proxy;

import com.easy.query.core.proxy.AbstractProxyEntity;
import com.easy.query.core.proxy.SQLColumn;
import com.easy.query.core.proxy.SQLSelectAsExpression;
import com.easy.query.core.proxy.core.draft.Draft2;
import com.easy.query.core.proxy.fetcher.AbstractFetcher;
import com.easy.query.core.util.EasyObjectUtil;

/**
 * this file automatically generated by easy-query, don't modify it
 * 当前文件是easy-query自动生成的请不要随意修改
 *
 * @author xuejiaming
 */
public class Draft2Proxy<T1,T2> extends AbstractProxyEntity<Draft2Proxy<T1,T2>, Draft2<T1,T2>> {

    private static final Class<Draft2> entityClass = Draft2.class;

    public static <TR1,TR2> Draft2Proxy<TR1,TR2> createTable() {
        return new Draft2Proxy<>();
    }

    public Draft2Proxy() {
    }

    /**
     * {@link Draft2#getValue1}
     */
    public SQLColumn<Draft2Proxy<T1,T2>, T1> value1() {
        return get("value1");
    }

    /**
     * {@link Draft2#getValue2()}
     */
    public SQLColumn<Draft2Proxy<T1,T2>, T2> value2() {
        return get("value2");
    }


    @Override
    public Class<Draft2<T1,T2>> getEntityClass() {
        return EasyObjectUtil.typeCastNullable(entityClass);
    }


    /**
     * 数据库列的简单获取
     *
     * @return
     */
    public Draft2ProxyFetcher<T1,T2> FETCHER = new Draft2ProxyFetcher<>(this, null, SQLSelectAsExpression.empty);


    public static class Draft2ProxyFetcher<TF1,TF2> extends AbstractFetcher<Draft2Proxy<TF1,TF2>, Draft2<TF1,TF2>, Draft2ProxyFetcher<TF1,TF2>> {

        public Draft2ProxyFetcher(Draft2Proxy<TF1,TF2> proxy, Draft2ProxyFetcher<TF1,TF2> prev, SQLSelectAsExpression sqlSelectAsExpression) {
            super(proxy, prev, sqlSelectAsExpression);
        }


        /**
         * {@link Draft2#getValue1}
         */
        public Draft2ProxyFetcher<TF1,TF2> value1() {
            return add(getProxy().value1());
        }


        /**
         * {@link Draft2#getValue2}
         */
        public Draft2ProxyFetcher<TF1,TF2> value2() {
            return add(getProxy().value2());
        }


        @Override
        protected Draft2ProxyFetcher<TF1,TF2> createFetcher(
                Draft2Proxy<TF1,TF2> cp,
                AbstractFetcher<Draft2Proxy<TF1,TF2>, Draft2<TF1,TF2>, Draft2ProxyFetcher<TF1,TF2>> prev,
                SQLSelectAsExpression sqlSelectExpression
        ) {
            return new Draft2ProxyFetcher<>(cp, this, sqlSelectExpression);
        }
    }

}
