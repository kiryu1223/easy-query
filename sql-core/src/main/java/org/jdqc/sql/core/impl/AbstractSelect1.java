package org.jdqc.sql.core.impl;

import org.jdqc.sql.core.abstraction.lambda.SqlExpression2;
import org.jdqc.sql.core.abstraction.sql.Select1;
import org.jdqc.sql.core.abstraction.sql.Select2;
import org.jdqc.sql.core.abstraction.sql.base.SqlPredicate;
import org.jdqc.sql.core.abstraction.sql.enums.PredicateModeEnum;
import org.jdqc.sql.core.enums.SelectTableInfoTypeEnum;
import org.jdqc.sql.core.impl.lambda.DefaultSqlPredicate;
import org.jdqc.sql.core.query.builder.SelectTableInfo;
import org.jdqc.sql.core.schema.TableInfo;

/**
 *
 * @FileName: AbstractSelect1.java
 * @Description: 文件说明
 * @Date: 2023/2/6 23:43
 * @Created by xuejiaming
 */
public abstract class AbstractSelect1<T1,TR> extends AbstractSelect0<T1,TR,Select1<T1,TR>> implements Select1<T1,TR> {

    private final Select1SqlPredicateProvider<T1> sqlPredicateProvider;
    public AbstractSelect1(Class<T1> t1Class,SelectContext selectContext) {
        super(selectContext);
        TableInfo tableInfo = selectContext.getJdqcConfiguration().getTableByEntity(t1Class);
        selectContext.addSelectTable(new SelectTableInfo(tableInfo,selectContext.getAlias(),selectContext.getNextTableIndex(), SelectTableInfoTypeEnum.FROM));
        sqlPredicateProvider= new Select1SqlPredicateProvider<>(selectContext);
    }
    @Override
    public <T2> Select2<T1, T2, TR> leftJoin(Class<T2> joinClass, SqlExpression2<SqlPredicate<T1>, SqlPredicate<T2>> on) {
        SelectContext selectContext = getSelectContext();
        AbstractSelect2<T1, T2, TR> select2 = createSelect2(joinClass, selectContext, SelectTableInfoTypeEnum.LEFT_JOIN);
        SqlPredicate<T1> on1 = select2.getSelect2SqlPredicateProvider().getSqlPredicate1(PredicateModeEnum.ON_PREDICATE);
        SqlPredicate<T2> on2 = select2.getSelect2SqlPredicateProvider().getSqlPredicate2(PredicateModeEnum.ON_PREDICATE);
        on.apply(on1,on2);
        return select2;
    }


    @Override
    protected Select1SqlPredicateProvider<T1> getSelect1SqlPredicateProvider(){
        return this.sqlPredicateProvider;
    }
    @Override
    public <T2> Select2<T1, T2, TR> innerJoin(Class<T2> joinClass, SqlExpression2<SqlPredicate<T1>, SqlPredicate<T2>> on) {
        SelectContext selectContext = getSelectContext();
        Select2<T1, T2, TR> select2 = createSelect2(joinClass, selectContext, SelectTableInfoTypeEnum.INNER_JOIN);
        SqlPredicate on1=new DefaultSqlPredicate<>(0,selectContext,PredicateModeEnum.ON_PREDICATE);
        SqlPredicate on2=new DefaultSqlPredicate<>(1,selectContext,PredicateModeEnum.ON_PREDICATE);
        on.apply(on1,on2);
        return select2;
    }
    @Override
    protected Select1<T1, TR> getChain() {
        return this;
    }
    protected abstract <T2> AbstractSelect2<T1, T2, TR> createSelect2(Class<T2> joinClass,SelectContext selectContext,SelectTableInfoTypeEnum selectTableInfoType);
}
