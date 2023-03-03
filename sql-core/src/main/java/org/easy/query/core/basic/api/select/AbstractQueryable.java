package org.easy.query.core.basic.api.select;

import org.easy.query.core.abstraction.*;
import org.easy.query.core.abstraction.metadata.EntityMetadata;
import org.easy.query.core.enums.MultiTableTypeEnum;
import org.easy.query.core.expression.lambda.Property;
import org.easy.query.core.expression.lambda.SqlExpression;
import org.easy.query.core.abstraction.metadata.ColumnMetadata;
import org.easy.query.core.abstraction.sql.DefaultPageResult;
import org.easy.query.core.abstraction.sql.PageResult;
import org.easy.query.core.abstraction.sql.enums.EasyAggregate;
import org.easy.query.core.expression.lambda.SqlExpression2;
import org.easy.query.core.expression.parser.abstraction.SqlAggregatePredicate;
import org.easy.query.core.expression.parser.abstraction.SqlColumnAsSelector;
import org.easy.query.core.expression.parser.abstraction.SqlColumnSelector;
import org.easy.query.core.expression.parser.abstraction.SqlPredicate;
import org.easy.query.core.expression.parser.abstraction.internal.ColumnSelector;
import org.easy.query.core.expression.segment.SelectConstSegment;
import org.easy.query.core.expression.segment.SelectCountSegment;
import org.easy.query.core.expression.segment.builder.ProjectSqlBuilderSegment;
import org.easy.query.core.expression.context.SelectContext;
import org.easy.query.core.impl.Select1SqlProvider;
import org.easy.query.core.query.builder.SqlTableInfo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @FileName: AbstractSelect0.java
 * @Description: 文件说明
 * @Date: 2023/2/6 23:44
 * @Created by xuejiaming
 */
public abstract class AbstractQueryable<T1> implements Queryable<T1> {
    protected final Class<T1> t1Class;
    protected final SqlTableInfo sqlTable;
    protected final SelectContext selectContext;
    protected final Select1SqlProvider<T1> sqlPredicateProvider;

    @Override
    public Class<T1> queryClass() {
        return t1Class;
    }

    public AbstractQueryable(Class<T1> t1Class, SelectContext selectContext) {
        this.t1Class = t1Class;
        this.selectContext = selectContext;
        EntityMetadata entityMetadata = selectContext.getRuntimeContext().getEntityMetadataManager().getEntityMetadata(t1Class);
        entityMetadata.checkTable();
        sqlTable = new SqlTableInfo(entityMetadata, selectContext.getAlias(), selectContext.getNextTableIndex(), MultiTableTypeEnum.FROM);
        selectContext.addSqlTable(sqlTable);
        sqlPredicateProvider = new Select1SqlProvider<>(selectContext);
    }


    @Override
    public long count() {
        selectContext.getProjects().append(new SelectCountSegment());
        List<Long> result = toInternalList(Long.class);
        if (result.isEmpty()) {
            return 0L;
        }
        Long r = result.get(0);
        if (r == null) {
            return 0L;
        }
        return r;
    }

    @Override
    public long countDistinct(SqlExpression<SqlColumnSelector<T1>> selectExpression) {
        ProjectSqlBuilderSegment sqlSegmentBuilder = new ProjectSqlBuilderSegment();
        SqlColumnSelector<T1> sqlColumnSelector = getSqlBuilderProvider1().getSqlColumnSelector1(sqlSegmentBuilder);
        selectExpression.apply(sqlColumnSelector);
        selectContext.getProjects().append(new SelectConstSegment(EasyAggregate.COUNT_DISTINCT.getFuncColumn(sqlSegmentBuilder.toSql())));
        List<Long> result = toInternalList(Long.class);

        if (result.isEmpty()) {
            return 0L;
        }
        Long r = result.get(0);
        if (r == null) {
            return 0L;
        }
        return r;
    }

    @Override
    public boolean any() {
        limit(1);
        selectContext.getProjects().append(new SelectCountSegment());
        List<Integer> result = toInternalList(Integer.class);
        return !result.isEmpty();
    }

    @Override
    public <TMember extends Number> BigDecimal sumBigDecimalOrDefault(Property<T1, TMember> column, BigDecimal def) {

        SqlTableInfo table = selectContext.getTable(0);
        ColumnMetadata columnMetadata = table.getColumn(column);
        String columnName = columnMetadata.getName();
        String quoteName = selectContext.getQuoteName(columnName);
        Class<?> memberClass = columnMetadata.getProperty().getPropertyType();
        selectContext.getProjects().append(new SelectConstSegment(EasyAggregate.SUM.getFuncColumn(table.getAlias() + "." + quoteName)));
        List<TMember> result = toInternalList((Class<TMember>) memberClass);
        if (result.isEmpty()) {
            return def;
        }
        TMember resultMember = result.get(0);
        if (resultMember == null) {
            return def;
        }
        return new BigDecimal(resultMember.toString());
    }

    @Override
    public <TMember extends Number> TMember sumOrDefault(Property<T1, TMember> column, TMember def) {

        SqlTableInfo table = selectContext.getTable(0);
        String propertyName = table.getPropertyName(column);
        String sqlColumnSegment = selectContext.getSqlColumnSegment(table, propertyName);
        ColumnMetadata columnMetadata = table.getColumnMetadata(propertyName);
        Class<?> memberClass = columnMetadata.getProperty().getPropertyType();
        selectContext.getProjects().append(new SelectConstSegment(EasyAggregate.SUM.getFuncColumn(sqlColumnSegment)));
        List<TMember> result = toInternalList((Class<TMember>) memberClass);
        if (result.isEmpty()) {
            return def;
        }
        TMember resultMember = result.get(0);
        if (resultMember == null) {
            return def;
        }
        return resultMember;
    }
    //    @Override
//    public BigDecimal sum(SqlExpression<SqlSingleColumnSelector<T1>> selectExpression) {
//
//        SelectSqlSegmentBuilder sqlSegmentBuilder = new SelectSqlSegmentBuilder();
//        SqlSingleColumnSelector<T1> sqlColumnSelector = getSqlBuilderProvider1().getSqlSingleColumnSelector1(sqlSegmentBuilder);
//        selectExpression.apply(sqlColumnSelector);
//        if(sqlSegmentBuilder.isEmpty()){
//            throw new JDQCException("sum must set column select expression");
//        }
//        List<BigDecimal> result = toInternalList(BigDecimal.class, EasyAggregate.SUM.getFuncColumn(sqlSegmentBuilder.toSql()));
//        if(result.isEmpty()){
//            return BigDecimal.ZERO;
//        }
//        return result.get(0);
//    }

    @Override
    public <TMember> TMember maxOrDefault(Property<T1, TMember> column, TMember def) {

        SqlTableInfo table = selectContext.getTable(0);
        String propertyName = table.getPropertyName(column);
        String sqlColumnSegment = selectContext.getSqlColumnSegment(table, propertyName);
        ColumnMetadata columnMetadata = table.getColumnMetadata(propertyName);
        Class<?> memberClass = columnMetadata.getProperty().getPropertyType();
        selectContext.getProjects().append(new SelectConstSegment(EasyAggregate.MAX.getFuncColumn(sqlColumnSegment)));
        List<TMember> result = toInternalList((Class<TMember>) memberClass);
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    @Override
    public <TMember> TMember minOrDefault(Property<T1, TMember> column, TMember def) {
        SqlTableInfo table = selectContext.getTable(0);
        String propertyName = table.getPropertyName(column);
        String sqlColumnSegment = selectContext.getSqlColumnSegment(table, propertyName);
        ColumnMetadata columnMetadata = table.getColumnMetadata(propertyName);
        Class<?> memberClass = columnMetadata.getProperty().getPropertyType();
        selectContext.getProjects().append(new SelectConstSegment(EasyAggregate.MIN.getFuncColumn(sqlColumnSegment)));
        List<TMember> result = toInternalList((Class<TMember>) memberClass);
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    @Override
    public <TMember> TMember avgOrDefault(Property<T1, TMember> column, TMember def) {
        SqlTableInfo table = selectContext.getTable(0);
        String propertyName = table.getPropertyName(column);
        String sqlColumnSegment = selectContext.getSqlColumnSegment(table, propertyName);
        ColumnMetadata columnMetadata = table.getColumnMetadata(propertyName);
        Class<?> memberClass = columnMetadata.getProperty().getPropertyType();
        selectContext.getProjects().append(new SelectConstSegment(EasyAggregate.AVG.getFuncColumn(sqlColumnSegment)));
        List<TMember> result = toInternalList((Class<TMember>) memberClass);
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    @Override
    public T1 firstOrNull() {
        this.limit(1);
        List<T1> list = toList();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    //
//    @Override
//    public T1 firstOrNull(SqlExpression<SqlColumnSelector<T1>> selectExpression) {
//        this.limit(1);
//        List<T1> list = toList(selectExpression);
//        if (list.isEmpty()) {
//            return null;
//        }
//        return list.get(0);
//    }
//
//    @Override
//    public <TR> TR firstOrNull(Class<TR> resultClass) {
//        SqlExpression<SqlColumnAsSelector<T1, TR>> selectExpression = getDefaultColumnAsAll();
//        return firstOrNull(resultClass, selectExpression);
//    }
//
//    @Override
//    public <TR> TR firstOrNull(Class<TR> resultClass, SqlExpression<SqlColumnAsSelector<T1, TR>> selectExpression) {
//        this.limit(1);
//        List<TR> list = toList(resultClass, selectExpression);
//        if (list.isEmpty()) {
//            return null;
//        }
//        return list.get(0);
//    }
    protected SqlExpression<SqlColumnSelector<T1>> getDefaultColumnAll() {
        if (selectContext.getGroup().isEmpty()) {
            return ColumnSelector::columnAll;
        } else {
            return null;
        }
    }

    protected <TR> SqlExpression<SqlColumnAsSelector<T1, TR>> getDefaultColumnAsAll() {
        if (selectContext.getGroup().isEmpty()) {
            return ColumnSelector::columnAll;
        } else {
            return null;
        }
    }

    @Override
    public List<T1> toList() {
        SqlExpression<SqlColumnSelector<T1>> selectorExpression = getDefaultColumnAll();
        if (selectorExpression != null) {
            SqlColumnSelector<T1> sqlColumnSelector = getSqlBuilderProvider1().getSqlColumnSelector1(selectContext.getProjects());
            selectorExpression.apply(sqlColumnSelector);
        }
        return toInternalList(queryClass());
    }
//
//    @Override
//    public <TR> List<TR> toList(Class<TR> resultClass) {
//        SqlExpression<SqlColumnAsSelector<T1, TR>> selectExpression=getDefaultColumnAsAll();
//        return toList(resultClass,selectExpression);
//    }
//
//    @Override
//    public <TR> List<TR> toList(Class<TR> resultClass, SqlExpression<SqlColumnAsSelector<T1, TR>> selectExpression) {
//
////        EntityMetadata entityMetadata = selectContext.getRuntimeContext().getEntityMetadataManager().getEntityMetadata(t1Class);
////        if(entityMetadata.logicDelete()!=null){
////            DefaultSqlPredicate<T1> objectDefaultSqlPredicate = new DefaultSqlPredicate<>(0, selectContext, selectContext.getWhere());
////            ((SqlExpression<SqlPredicate<T1>>)entityMetadata.logicDelete()).apply(objectDefaultSqlPredicate);
////        }
//        ProjectSqlBuilderSegment sqlSegmentBuilder = new ProjectSqlBuilderSegment();
//        SqlColumnAsSelector<T1,TR> sqlColumnSelector = getSqlBuilderProvider1().getSqlColumnAsSelector1(sqlSegmentBuilder);
//        selectExpression.apply(sqlColumnSelector);
//        return toInternalList(resultClass,sqlSegmentBuilder.toSql());
//    }
//
//    @Override
//    public List<T1> toList(SqlExpression<SqlColumnSelector<T1>> selectExpression) {
//        ProjectSqlBuilderSegment sqlSegmentBuilder = new ProjectSqlBuilderSegment();
//        SqlColumnSelector<T1> sqlColumnSelector = getSqlBuilderProvider1().getSqlColumnSelector1(sqlSegmentBuilder);
//        selectExpression.apply(sqlColumnSelector);
//        return toInternalList(t1Class);
//    }

    /**
     * 子类实现方法
     *
     * @return
     */
    protected <TR> List<TR> toInternalList(Class<TR> resultClass) {
        //添加query filter logic delete
        String sql = toSql();
        EasyExecutor easyExecutor = selectContext.getRuntimeContext().getEasyExecutor();
        return easyExecutor.query(ExecutorContext.create(selectContext.getRuntimeContext()), resultClass, sql, selectContext.getParameters());
    }

    @Override
    public Queryable<T1> select(SqlExpression<SqlColumnSelector<T1>> selectExpression) {
        SqlColumnSelector<T1> sqlColumnSelector = getSqlBuilderProvider1().getSqlColumnSelector1(selectContext.getProjects());
        selectExpression.apply(sqlColumnSelector);
        return this;
    }

    @Override
    public <TR> Queryable<TR> select(Class<TR> resultClass, SqlExpression<SqlColumnAsSelector<T1, TR>> selectExpression) {
        SqlColumnAsSelector<T1, TR> sqlColumnSelector = getSqlBuilderProvider1().getSqlColumnAsSelector1(selectContext.getProjects());
        selectExpression.apply(sqlColumnSelector);
        return selectContext.getRuntimeContext().getQueryableFactory().createQueryable(resultClass, selectContext);
    }

//    @Override
//    public Queryable<T1> select(String columns) {
//        return this;
//    }

    @Override
    public <TR> Queryable<TR> select(Class<TR> resultClass) {
        SqlColumnAsSelector<T1, TR> sqlColumnSelector = getSqlBuilderProvider1().getSqlColumnAsSelector1(selectContext.getProjects());
        SqlExpression<SqlColumnAsSelector<T1, TR>> selectExpression = getDefaultColumnAsAll();
        selectExpression.apply(sqlColumnSelector);
        return selectContext.getRuntimeContext().getQueryableFactory().createQueryable(resultClass, selectContext);
    }

//    @Override
//    public <TR> Queryable<TR> select(Class<TR> resultClass, String columns) {
//        return null;
//    }
    //    @Override
//    public String toSql() {
//        SqlExpression<SqlColumnSelector<T1>> selectExpression = ColumnSelector::columnAll;
//        return toSql(selectExpression);
//    }
//
//    @Override
//    public String toSql(SqlExpression<SqlColumnSelector<T1>> selectExpression) {
//        ProjectSqlBuilderSegment sqlSegmentBuilder = new ProjectSqlBuilderSegment();
//        SqlColumnSelector<T1> sqlColumnSelector = getSqlBuilderProvider1().getSqlColumnSelector1(sqlSegmentBuilder);
//        selectExpression.apply(sqlColumnSelector);
//        return toSql(sqlSegmentBuilder.toSql());
//    }
//
//    @Override
//    public <TR> String toSql(Class<TR> resultClass, SqlExpression<SqlColumnAsSelector<T1, TR>> selectExpression) {
//        ProjectSqlBuilderSegment sqlSegmentBuilder = new ProjectSqlBuilderSegment();
//        SqlColumnAsSelector<T1,TR> sqlColumnSelector = getSqlBuilderProvider1().getSqlColumnAsSelector1(sqlSegmentBuilder);
//        selectExpression.apply(sqlColumnSelector);
//        return toSql(sqlSegmentBuilder.toSql());
//    }
//
//    @Override
//    public abstract String toSql(String columns);

    @Override
    public Queryable<T1> where(boolean condition, SqlExpression<SqlPredicate<T1>> whereExpression) {
        if (condition) {
            SqlPredicate<T1> sqlPredicate = getSqlBuilderProvider1().getSqlWherePredicate1();
            whereExpression.apply(sqlPredicate);
        }
        return this;
    }

    @Override
    public Queryable<T1> groupBy(boolean condition, SqlExpression<SqlColumnSelector<T1>> selectExpression) {
        if (condition) {
            SqlColumnSelector<T1> sqlPredicate = getSqlBuilderProvider1().getSqlGroupColumnSelector1();
            selectExpression.apply(sqlPredicate);
        }
        return this;
    }

    @Override
    public Queryable<T1> having(boolean condition, SqlExpression<SqlAggregatePredicate<T1>> predicateExpression) {

        if (condition) {
            SqlAggregatePredicate<T1> sqlAggregatePredicate = getSqlBuilderProvider1().getSqlAggregatePredicate1();
            predicateExpression.apply(sqlAggregatePredicate);
        }
        return this;
    }

    @Override
    public Queryable<T1> orderByAsc(boolean condition, SqlExpression<SqlColumnSelector<T1>> selectExpression) {
        if (condition) {
            SqlColumnSelector<T1> sqlPredicate = getSqlBuilderProvider1().getSqlOrderColumnSelector1(true);
            selectExpression.apply(sqlPredicate);
        }
        return this;
    }

    @Override
    public Queryable<T1> orderByDesc(boolean condition, SqlExpression<SqlColumnSelector<T1>> selectExpression) {
        if (condition) {
            SqlColumnSelector<T1> sqlPredicate = getSqlBuilderProvider1().getSqlOrderColumnSelector1(false);
            selectExpression.apply(sqlPredicate);
        }
        return this;
    }

    @Override
    public Queryable<T1> limit(boolean condition, long offset, long rows) {
        if (condition) {
            selectContext.setOffset(offset);
            selectContext.setRows(rows);
        }
        return this;
    }

    @Override
    public PageResult<T1> toPageResult(long pageIndex, long pageSize) {
        return toPageResult(pageIndex, pageSize, t1Class);
    }

    @Override
    public <TR> PageResult<TR> toPageResult(long pageIndex, long pageSize, Class<TR> clazz) {
//        SqlColumnSelector<T1> sqlColumnSelector = getSqlBuilderProvider1().getSqlColumnSelector1(selectContext.getProjects());
        SqlExpression<SqlColumnSelector<T1>> selectExpression = ColumnSelector::columnAll;
//        selectExpression.apply(sqlColumnSelector);
        return doPageResult(pageIndex, pageSize, clazz,selectExpression);
    }

    @Override
    public PageResult<T1> toPageResult(long pageIndex, long pageSize, SqlExpression<SqlColumnSelector<T1>> selectExpression) {
        return doPageResult(pageIndex, pageSize, t1Class,selectExpression);
    }

    protected <TR> PageResult<TR> doPageResult(long pageIndex, long pageSize, Class<TR> clazz, SqlExpression<SqlColumnSelector<T1>> selectExpression) {
        //设置每次获取多少条
        long take = pageSize <= 0 ? 1 : pageSize;
        //设置当前页码最小1
        long index = pageIndex <= 0 ? 1 : pageIndex;
        //需要跳过多少条
        long offset = (index - 1) * take;
        long total = this.count();
        if (total <= offset) {
            return new DefaultPageResult<TR>(total, new ArrayList<>(0));
        }//获取剩余条数
        long remainingCount = total - offset;
        //当剩余条数小于take数就取remainingCount
        long realTake = Math.min(remainingCount, take);
        this.limit(offset, realTake);
selectContext.getProjects().getSqlSegments().clear();
        SqlColumnSelector<T1> sqlColumnSelector = getSqlBuilderProvider1().getSqlColumnSelector1(selectContext.getProjects());
        selectExpression.apply(sqlColumnSelector);
        List<TR> data = this.toInternalList(clazz);
        return new DefaultPageResult<TR>(total, data);
    }

    @Override
    public <TR> PageResult<TR> toPageResult(long pageIndex, long pageSize, Class<TR> clazz, SqlExpression<SqlColumnAsSelector<T1, TR>> selectExpression) {

        ProjectSqlBuilderSegment sqlSegmentBuilder = new ProjectSqlBuilderSegment();
        SqlColumnAsSelector<T1, TR> sqlColumnSelector = getSqlBuilderProvider1().getSqlColumnAsSelector1(sqlSegmentBuilder);
        selectExpression.apply(sqlColumnSelector);
        return toPageResult(pageIndex, pageSize, clazz);
    }

    @Override
    public SelectContext getSelectContext() {
        return selectContext;
    }


    @Override
    public <T2> Queryable2<T1, T2> leftJoin(Class<T2> joinClass, SqlExpression2<SqlPredicate<T1>, SqlPredicate<T2>> on) {
        Queryable2<T1, T2> queryable2 = selectContext.getRuntimeContext().getQueryableFactory().createQueryable2(t1Class, joinClass, MultiTableTypeEnum.LEFT_JOIN, selectContext);
        SqlPredicate<T1> on1 = queryable2.getSqlBuilderProvider2().getSqlOnPredicate1();
        SqlPredicate<T2> on2 = queryable2.getSqlBuilderProvider2().getSqlOnPredicate2();
        on.apply(on1, on2);
        return queryable2;
    }


    @Override
    public <T2> Queryable2<T1, T2> innerJoin(Class<T2> joinClass, SqlExpression2<SqlPredicate<T1>, SqlPredicate<T2>> on) {
        Queryable2<T1, T2> queryable2 = selectContext.getRuntimeContext().getQueryableFactory().createQueryable2(t1Class, joinClass, MultiTableTypeEnum.INNER_JOIN, selectContext);
        SqlPredicate<T1> sqlOnPredicate1 = queryable2.getSqlBuilderProvider2().getSqlOnPredicate1();
        SqlPredicate<T2> sqlOnPredicate2 = queryable2.getSqlBuilderProvider2().getSqlOnPredicate2();
        on.apply(sqlOnPredicate1, sqlOnPredicate2);
        return queryable2;
    }

    @Override
    public EasyQuerySqlBuilderProvider<T1> getSqlBuilderProvider1() {
        return sqlPredicateProvider;
    }
}
