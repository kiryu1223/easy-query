package com.easy.query.core.proxy.core.draft.proxy;

import com.easy.query.core.proxy.AbstractProxyEntity;
import com.easy.query.core.proxy.SQLColumn;
import com.easy.query.core.proxy.SQLSelectAsExpression;
import com.easy.query.core.proxy.core.draft.Draft9;
import com.easy.query.core.proxy.fetcher.AbstractFetcher;
import com.easy.query.core.util.EasyObjectUtil;

/**
 * this file automatically generated by easy-query, don't modify it
 * 当前文件是easy-query自动生成的请不要随意修改
 *
 * @author xuejiaming
 */
public class Draft9Proxy<T1,T2,T3,T4,T5,T6,T7,T8,T9> extends AbstractProxyEntity<Draft9Proxy<T1,T2,T3,T4,T5,T6,T7,T8,T9>, Draft9<T1,T2,T3,T4,T5,T6,T7,T8,T9>> {

    private static final Class<Draft9> entityClass = Draft9.class;

    public static <TR1,TR2,TR3,TR4,TR5,TR6,TR7,TR8,TR9> Draft9Proxy<TR1,TR2,TR3,TR4,TR5,TR6,TR7,TR8,TR9> createTable() {
        return new Draft9Proxy<>();
    }

    public Draft9Proxy() {
    }

    /**
     * {@link Draft9#getValue1}
     */
    public SQLColumn<Draft9Proxy<T1,T2,T3,T4,T5,T6,T7,T8,T9>, T1> value1() {
        return get("value1");
    }

    /**
     * {@link Draft9#getValue2()}
     */
    public SQLColumn<Draft9Proxy<T1,T2,T3,T4,T5,T6,T7,T8,T9>, T2> value2() {
        return get("value2");
    }
    /**
     * {@link Draft9#getValue3()}
     */
    public SQLColumn<Draft9Proxy<T1,T2,T3,T4,T5,T6,T7,T8,T9>, T3> value3() {
        return get("value3");
    }
    /**
     * {@link Draft9#getValue4()}
     */
    public SQLColumn<Draft9Proxy<T1,T2,T3,T4,T5,T6,T7,T8,T9>, T4> value4() {
        return get("value4");
    }
    /**
     * {@link Draft9#getValue5()}
     */
    public SQLColumn<Draft9Proxy<T1,T2,T3,T4,T5,T6,T7,T8,T9>, T5> value5() {
        return get("value5");
    }
    /**
     * {@link Draft9#getValue6()}
     */
    public SQLColumn<Draft9Proxy<T1,T2,T3,T4,T5,T6,T7,T8,T9>, T6> value6() {
        return get("value6");
    }
    /**
     * {@link Draft9#getValue7()}
     */
    public SQLColumn<Draft9Proxy<T1,T2,T3,T4,T5,T6,T7,T8,T9>, T7> value7() {
        return get("value7");
    }
    /**
     * {@link Draft9#getValue8()}
     */
    public SQLColumn<Draft9Proxy<T1,T2,T3,T4,T5,T6,T7,T8,T9>, T8> value8() {
        return get("value8");
    }
    /**
     * {@link Draft9#getValue9()}
     */
    public SQLColumn<Draft9Proxy<T1,T2,T3,T4,T5,T6,T7,T8,T9>, T9> value9() {
        return get("value9");
    }


    @Override
    public Class<Draft9<T1,T2,T3,T4,T5,T6,T7,T8,T9>> getEntityClass() {
        return EasyObjectUtil.typeCastNullable(entityClass);
    }


    /**
     * 数据库列的简单获取
     *
     * @return
     */
    public Draft9ProxyFetcher<T1,T2,T3,T4,T5,T6,T7,T8,T9> FETCHER = new Draft9ProxyFetcher<>(this, null, SQLSelectAsExpression.empty);


    public static class Draft9ProxyFetcher<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9> extends AbstractFetcher<Draft9Proxy<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9>, Draft9<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9>, Draft9ProxyFetcher<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9>> {

        public Draft9ProxyFetcher(Draft9Proxy<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9> proxy, Draft9ProxyFetcher<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9> prev, SQLSelectAsExpression sqlSelectAsExpression) {
            super(proxy, prev, sqlSelectAsExpression);
        }


        /**
         * {@link Draft9#getValue1}
         */
        public Draft9ProxyFetcher<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9> value1() {
            return add(getProxy().value1());
        }


        /**
         * {@link Draft9#getValue2}
         */
        public Draft9ProxyFetcher<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9> value2() {
            return add(getProxy().value2());
        }
        /**
         * {@link Draft9#getValue3}
         */
        public Draft9ProxyFetcher<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9> value3() {
            return add(getProxy().value3());
        }
        /**
         * {@link Draft9#getValue4}
         */
        public Draft9ProxyFetcher<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9> value4() {
            return add(getProxy().value4());
        }
        /**
         * {@link Draft9#getValue5}
         */
        public Draft9ProxyFetcher<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9> value5() {
            return add(getProxy().value5());
        }
        /**
         * {@link Draft9#getValue6}
         */
        public Draft9ProxyFetcher<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9> value6() {
            return add(getProxy().value6());
        }
        /**
         * {@link Draft9#getValue7}
         */
        public Draft9ProxyFetcher<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9> value7() {
            return add(getProxy().value7());
        }
        /**
         * {@link Draft9#getValue8}
         */
        public Draft9ProxyFetcher<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9> value8() {
            return add(getProxy().value8());
        }
        /**
         * {@link Draft9#getValue9}
         */
        public Draft9ProxyFetcher<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9> value9() {
            return add(getProxy().value9());
        }


        @Override
        protected Draft9ProxyFetcher<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9> createFetcher(
                Draft9Proxy<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9> cp,
                AbstractFetcher<Draft9Proxy<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9>, Draft9<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9>, Draft9ProxyFetcher<TF1,TF2,TF3,TF4,TF5,TF6,TF7,TF8,TF9>> prev,
                SQLSelectAsExpression sqlSelectExpression
        ) {
            return new Draft9ProxyFetcher<>(cp, this, sqlSelectExpression);
        }
    }

}
