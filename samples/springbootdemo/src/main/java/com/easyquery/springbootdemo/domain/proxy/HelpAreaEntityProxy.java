package com.easyquery.springbootdemo.domain.proxy;

import com.easy.query.core.expression.parser.core.available.TableAvailable;
import com.easy.query.core.proxy.AbstractProxyEntity;
import com.easy.query.core.proxy.SQLColumn;
import com.easy.query.core.proxy.SQLSelectAsExpression;
import com.easy.query.core.proxy.fetcher.AbstractFetcher;
import com.easy.query.core.proxy.core.EntitySQLContext;
import com.easyquery.springbootdemo.domain.HelpAreaEntity;
import com.easy.query.core.proxy.columns.SQLStringColumn;
import com.easy.query.core.proxy.columns.SQLDateTimeColumn;
import com.easy.query.core.proxy.columns.SQLBooleanColumn;

/**
 * this file automatically generated by easy-query, don't modify it
 * 当前文件是easy-query自动生成的请不要随意修改
 * 如果出现属性冲突请使用@ProxyProperty进行重命名
 *
 * @author xuejiaming
 */
public class HelpAreaEntityProxy extends AbstractProxyEntity < HelpAreaEntityProxy, HelpAreaEntity > {

    private static final Class < HelpAreaEntity > entityClass = HelpAreaEntity .class;

    public static HelpAreaEntityProxy createTable () {
        return new HelpAreaEntityProxy ();
    }

    public HelpAreaEntityProxy () {
    }

    /**
     * {@link HelpAreaEntity#getName}
     */
    public SQLStringColumn < HelpAreaEntityProxy, String> name(){
    return getStringColumn("name", String.class);
}

    /**
     * {@link HelpAreaEntity#getProvinceId}
     */
    public SQLStringColumn < HelpAreaEntityProxy, String> provinceId(){
    return getStringColumn("provinceId", String.class);
}

    /**
     * {@link HelpAreaEntity#getProvinceName}
     */
    public SQLStringColumn < HelpAreaEntityProxy, String> provinceName(){
    return getStringColumn("provinceName", String.class);
}

    /**
     * {@link HelpAreaEntity#getCityId}
     */
    public SQLStringColumn < HelpAreaEntityProxy, String> cityId(){
    return getStringColumn("cityId", String.class);
}

    /**
     * {@link HelpAreaEntity#getCityName}
     */
    public SQLStringColumn < HelpAreaEntityProxy, String> cityName(){
    return getStringColumn("cityName", String.class);
}

    /**
     * {@link HelpAreaEntity#getId}
     */
    public SQLStringColumn < HelpAreaEntityProxy, String> id(){
    return getStringColumn("id", String.class);
}

    /**
     * 创建时间;创建时间
     * {@link HelpAreaEntity#getCreateTime}
     */
    public SQLDateTimeColumn < HelpAreaEntityProxy, java.time.LocalDateTime> createTime(){
    return getDateTimeColumn("createTime", java.time.LocalDateTime.class);
}

    /**
     * 修改时间;修改时间
     * {@link HelpAreaEntity#getUpdateTime}
     */
    public SQLDateTimeColumn < HelpAreaEntityProxy, java.time.LocalDateTime> updateTime(){
    return getDateTimeColumn("updateTime", java.time.LocalDateTime.class);
}

    /**
     * 创建人;创建人
     * {@link HelpAreaEntity#getCreateBy}
     */
    public SQLStringColumn < HelpAreaEntityProxy, String> createBy(){
    return getStringColumn("createBy", String.class);
}

    /**
     * 修改人;修改人
     * {@link HelpAreaEntity#getUpdateBy}
     */
    public SQLStringColumn < HelpAreaEntityProxy, String> updateBy(){
    return getStringColumn("updateBy", String.class);
}

    /**
     * 是否删除;是否删除
     * {@link HelpAreaEntity#getDeleted}
     */
    public SQLBooleanColumn < HelpAreaEntityProxy, Boolean> deleted(){
    return getBooleanColumn("deleted", Boolean.class);
}


    @Override
    public Class < HelpAreaEntity > getEntityClass () {
        return entityClass;
    }


    /**
     * 数据库列的简单获取
     * @return
     */
    public HelpAreaEntityProxyFetcher FETCHER =
        new HelpAreaEntityProxyFetcher (this, null, SQLSelectAsExpression.empty);


    public static class HelpAreaEntityProxyFetcher extends AbstractFetcher<HelpAreaEntityProxy, HelpAreaEntity, HelpAreaEntityProxyFetcher> {

        public HelpAreaEntityProxyFetcher (HelpAreaEntityProxy proxy, HelpAreaEntityProxyFetcher prev, SQLSelectAsExpression sqlSelectAsExpression) {
        super(proxy, prev, sqlSelectAsExpression);
    }


        /**
         * {@link HelpAreaEntity#getName}
         */
        public HelpAreaEntityProxyFetcher name() {
            return add(getProxy().name());
        }

        /**
         * {@link HelpAreaEntity#getProvinceId}
         */
        public HelpAreaEntityProxyFetcher provinceId() {
            return add(getProxy().provinceId());
        }

        /**
         * {@link HelpAreaEntity#getProvinceName}
         */
        public HelpAreaEntityProxyFetcher provinceName() {
            return add(getProxy().provinceName());
        }

        /**
         * {@link HelpAreaEntity#getCityId}
         */
        public HelpAreaEntityProxyFetcher cityId() {
            return add(getProxy().cityId());
        }

        /**
         * {@link HelpAreaEntity#getCityName}
         */
        public HelpAreaEntityProxyFetcher cityName() {
            return add(getProxy().cityName());
        }

        /**
         * {@link HelpAreaEntity#getId}
         */
        public HelpAreaEntityProxyFetcher id() {
            return add(getProxy().id());
        }

        /**
         * 创建时间;创建时间
         * {@link HelpAreaEntity#getCreateTime}
         */
        public HelpAreaEntityProxyFetcher createTime() {
            return add(getProxy().createTime());
        }

        /**
         * 修改时间;修改时间
         * {@link HelpAreaEntity#getUpdateTime}
         */
        public HelpAreaEntityProxyFetcher updateTime() {
            return add(getProxy().updateTime());
        }

        /**
         * 创建人;创建人
         * {@link HelpAreaEntity#getCreateBy}
         */
        public HelpAreaEntityProxyFetcher createBy() {
            return add(getProxy().createBy());
        }

        /**
         * 修改人;修改人
         * {@link HelpAreaEntity#getUpdateBy}
         */
        public HelpAreaEntityProxyFetcher updateBy() {
            return add(getProxy().updateBy());
        }

        /**
         * 是否删除;是否删除
         * {@link HelpAreaEntity#getDeleted}
         */
        public HelpAreaEntityProxyFetcher deleted() {
            return add(getProxy().deleted());
        }


        @Override
        protected HelpAreaEntityProxyFetcher createFetcher(
            HelpAreaEntityProxy cp,
            AbstractFetcher<HelpAreaEntityProxy, HelpAreaEntity, HelpAreaEntityProxyFetcher> prev,
            SQLSelectAsExpression sqlSelectExpression
        ) {
            return new HelpAreaEntityProxyFetcher (cp, this, sqlSelectExpression);
        }
    }

}