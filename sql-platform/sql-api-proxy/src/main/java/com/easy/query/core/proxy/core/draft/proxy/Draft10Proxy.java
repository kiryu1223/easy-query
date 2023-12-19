package com.easy.query.core.proxy.core.draft.proxy;

import com.easy.query.core.proxy.AbstractProxyEntity;
import com.easy.query.core.proxy.SQLColumn;
import com.easy.query.core.proxy.SQLSelectAsExpression;
import com.easy.query.core.proxy.core.draft.Draft10;
import com.easy.query.core.proxy.core.draft.Draft9;
import com.easy.query.core.proxy.fetcher.AbstractFetcher;
import com.easy.query.core.util.EasyObjectUtil;

/**
 * this file automatically generated by easy-query, don't modify it
 * 当前文件是easy-query自动生成的请不要随意修改
 *
 * @author xuejiaming
 */
public class Draft10Proxy<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10> extends AbstractProxyEntity<Draft10Proxy<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10>, Draft10<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10>> {

    private static final Class<Draft10> entityClass = Draft10.class;

    public static <TR1,TR2,TR3,TR4,TR5,TR6,TR7,TR8,TR9,TR10> Draft10Proxy<TR1,TR2,TR3,TR4,TR5,TR6,TR7,TR8,TR9,TR10> createTable() {
        return new Draft10Proxy<>();
    }

    public Draft10Proxy() {
    }

    /**
     * {@link Draft10#getValue1}
     */
    public SQLColumn<Draft10Proxy<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10>, T1> value1() {
        return get("value1");
    }

    /**
     * {@link Draft10#getValue2()}
     */
    public SQLColumn<Draft10Proxy<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10>, T2> value2() {
        return get("value2");
    }
    /**
     * {@link Draft10#getValue3()}
     */
    public SQLColumn<Draft10Proxy<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10>, T3> value3() {
        return get("value3");
    }
    /**
     * {@link Draft10#getValue4()}
     */
    public SQLColumn<Draft10Proxy<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10>, T4> value4() {
        return get("value4");
    }
    /**
     * {@link Draft10#getValue5()}
     */
    public SQLColumn<Draft10Proxy<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10>, T5> value5() {
        return get("value5");
    }
    /**
     * {@link Draft10#getValue6()}
     */
    public SQLColumn<Draft10Proxy<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10>, T6> value6() {
        return get("value6");
    }
    /**
     * {@link Draft10#getValue7()}
     */
    public SQLColumn<Draft10Proxy<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10>, T7> value7() {
        return get("value7");
    }
    /**
     * {@link Draft10#getValue8()}
     */
    public SQLColumn<Draft10Proxy<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10>, T8> value8() {
        return get("value8");
    }
    /**
     * {@link Draft10#getValue9()}
     */
    public SQLColumn<Draft10Proxy<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10>, T9> value9() {
        return get("value9");
    }
    /**
     * {@link Draft10#getValue10()}
     */
    public SQLColumn<Draft10Proxy<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10>, T10> value10() {
        return get("value10");
    }


    @Override
    public Class<Draft10<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10>> getEntityClass() {
        return EasyObjectUtil.typeCastNullable(entityClass);
    }


    /**
     * 数据库列的简单获取
     *
     * @return
     */
    public Draft10ProxyFetcher<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10> FETCHER = new Draft10ProxyFetcher<>(this, null, SQLSelectAsExpression.empty);


    public static class Draft10ProxyFetcher<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9,TF10> extends AbstractFetcher<Draft10Proxy<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9,TF10>, Draft10<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9,TF10>, Draft10ProxyFetcher<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9,TF10>> {

        public Draft10ProxyFetcher(Draft10Proxy<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9,TF10> proxy, Draft10ProxyFetcher<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9,TF10> prev, SQLSelectAsExpression sqlSelectAsExpression) {
            super(proxy, prev, sqlSelectAsExpression);
        }


        /**
         * {@link Draft10#getValue1}
         */
        public Draft10ProxyFetcher<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9,TF10> value1() {
            return add(getProxy().value1());
        }


        /**
         * {@link Draft10#getValue2}
         */
        public Draft10ProxyFetcher<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9,TF10> value2() {
            return add(getProxy().value2());
        }
        /**
         * {@link Draft10#getValue3}
         */
        public Draft10ProxyFetcher<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9,TF10> value3() {
            return add(getProxy().value3());
        }
        /**
         * {@link Draft10#getValue4}
         */
        public Draft10ProxyFetcher<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9,TF10> value4() {
            return add(getProxy().value4());
        }
        /**
         * {@link Draft10#getValue5}
         */
        public Draft10ProxyFetcher<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9,TF10> value5() {
            return add(getProxy().value5());
        }
        /**
         * {@link Draft10#getValue6}
         */
        public Draft10ProxyFetcher<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9,TF10> value6() {
            return add(getProxy().value6());
        }
        /**
         * {@link Draft10#getValue7}
         */
        public Draft10ProxyFetcher<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9,TF10> value7() {
            return add(getProxy().value7());
        }
        /**
         * {@link Draft10#getValue8}
         */
        public Draft10ProxyFetcher<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9,TF10> value8() {
            return add(getProxy().value8());
        }

        /**
         * {@link Draft9#getValue9}
         */
        public Draft10ProxyFetcher<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9,TF10> value9() {
            return add(getProxy().value9());
        }
        /**
         * {@link Draft9#getValue10}
         */
        public Draft10ProxyFetcher<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9,TF10> value10() {
            return add(getProxy().value10());
        }

        @Override
        protected Draft10ProxyFetcher<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9,TF10> createFetcher(
                Draft10Proxy<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9,TF10> cp,
                AbstractFetcher<Draft10Proxy<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9,TF10>, Draft10<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9,TF10>, Draft10ProxyFetcher<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9,TF10>> prev,
                SQLSelectAsExpression sqlSelectExpression
        ) {
            return new Draft10ProxyFetcher<>(cp, this, sqlSelectExpression);
        }
    }

}
