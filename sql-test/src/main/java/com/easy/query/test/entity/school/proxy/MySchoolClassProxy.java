package com.easy.query.test.entity.school.proxy;

import com.easy.query.core.expression.parser.core.available.TableAvailable;
import com.easy.query.core.proxy.AbstractProxyEntity;
import com.easy.query.core.proxy.SQLColumn;
import com.easy.query.core.proxy.SQLSelectAsExpression;
import com.easy.query.core.proxy.fetcher.AbstractFetcher;
import com.easy.query.core.proxy.core.EntitySQLContext;
import com.easy.query.test.entity.school.MySchoolClass;
import com.easy.query.core.proxy.columns.types.SQLStringTypeColumn;
import com.easy.query.core.proxy.columns.SQLNavigateColumn;
import com.easy.query.core.proxy.columns.SQLQueryable;
import com.easy.query.core.proxy.columns.types.SQLAnyTypeColumn;

/**
 * this file automatically generated by easy-query, don't modify it
 * 当前文件是easy-query自动生成的请不要随意修改
 * 如果出现属性冲突请使用@ProxyProperty进行重命名
 *
 * @author easy-query
 */
public class MySchoolClassProxy extends AbstractProxyEntity<MySchoolClassProxy, MySchoolClass> {

    private static final Class<MySchoolClass> entityClass = MySchoolClass.class;

    public static MySchoolClassProxy createTable() {
        return new MySchoolClassProxy();
    }

    public MySchoolClassProxy() {
    }

    /**
     * {@link MySchoolClass#getId}
     */
    public SQLStringTypeColumn<MySchoolClassProxy> id() {
        return getStringTypeColumn("id");
    }

    /**
     * {@link MySchoolClass#getName}
     */
    public SQLStringTypeColumn<MySchoolClassProxy> name() {
        return getStringTypeColumn("name");
    }

    /**
     * 一对多 一个班级多个学生
     * {@link MySchoolClass#getSchoolStudents}
     */
    public SQLQueryable<com.easy.query.test.entity.school.proxy.MySchoolStudentProxy, com.easy.query.test.entity.school.MySchoolStudent> schoolStudents() {
        return getNavigates("schoolStudents", new com.easy.query.test.entity.school.proxy.MySchoolStudentProxy());
    }


    @Override
    public Class<MySchoolClass> getEntityClass() {
        return entityClass;
    }


    /**
     * 数据库列的简单获取
     *
     * @return
     */
    public MySchoolClassProxyFetcher FETCHER = new MySchoolClassProxyFetcher(this, null, SQLSelectAsExpression.empty);


    public static class MySchoolClassProxyFetcher extends AbstractFetcher<MySchoolClassProxy, MySchoolClass, MySchoolClassProxyFetcher> {

        public MySchoolClassProxyFetcher(MySchoolClassProxy proxy, MySchoolClassProxyFetcher prev, SQLSelectAsExpression sqlSelectAsExpression) {
            super(proxy, prev, sqlSelectAsExpression);
        }


        /**
         * {@link MySchoolClass#getId}
         */
        public MySchoolClassProxyFetcher id() {
            return add(getProxy().id());
        }

        /**
         * {@link MySchoolClass#getName}
         */
        public MySchoolClassProxyFetcher name() {
            return add(getProxy().name());
        }


        @Override
        protected MySchoolClassProxyFetcher createFetcher(MySchoolClassProxy cp, AbstractFetcher<MySchoolClassProxy, MySchoolClass, MySchoolClassProxyFetcher> prev, SQLSelectAsExpression sqlSelectExpression) {
            return new MySchoolClassProxyFetcher(cp, this, sqlSelectExpression);
        }
    }

}
