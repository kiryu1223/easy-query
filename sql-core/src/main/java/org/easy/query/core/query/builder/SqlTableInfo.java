package org.easy.query.core.query.builder;

import org.easy.query.core.expression.lambda.Property;
import org.easy.query.core.abstraction.metadata.ColumnMetadata;
import org.easy.query.core.enums.MultiTableTypeEnum;
import org.easy.query.core.abstraction.metadata.EntityMetadata;
import org.easy.query.core.expression.segment.condition.AndPredicateSegment;
import org.easy.query.core.expression.lambda.SqlExpression;
import org.easy.query.core.expression.parser.abstraction.SqlColumnSetter;
import org.easy.query.core.expression.parser.abstraction.SqlPredicate;
import org.easy.query.core.expression.segment.condition.PredicateSegment;
import org.easy.query.core.util.LambdaUtil;

/**
 * @FileName: SelectTableInfo.java
 * @Description: 文件说明
 * @Date: 2023/2/7 11:36
 * @Created by xuejiaming
 */
public class SqlTableInfo {
    private final String alias;
    private final int index;
    private PredicateSegment on;

    private final MultiTableTypeEnum multiTableType;

    public EntityMetadata getEntityMetadata() {
        return entityMetadata;
    }

    private final EntityMetadata entityMetadata;

    public SqlTableInfo(EntityMetadata entityMetadata, String alias, int index, MultiTableTypeEnum multiTableType) {
        this.entityMetadata = entityMetadata;
        this.alias = alias;
        this.index = index;
        this.multiTableType = multiTableType;
    }


    public String getAlias() {
        return alias;
    }

    public int getIndex() {
        return index;
    }

    public PredicateSegment getOn() {
        if(on==null){
            on=new AndPredicateSegment(true);
        }
        return on;
    }
    public boolean hasOn(){
        return on!=null&&on.isNotEmpty();
    }

    public String getColumnName(String propertyName){
        return this.entityMetadata.getColumnName(propertyName);
    }
    public <T1> ColumnMetadata getColumn(Property<T1, ?> column){
        String propertyName = LambdaUtil.getPropertyName(column);
        return this.getColumnMetadata(propertyName);

    }
    public <T1> ColumnMetadata getColumnMetadata(String propertyName){
        return this.entityMetadata.getColumn(propertyName);
    }
    public <T1> String getColumnName(Property<T1, ?> column){
        String propertyName = LambdaUtil.getPropertyName(column);
        return this.getColumnName(propertyName);
    }
    public <T1> String getPropertyName(Property<T1, ?> column){
        return LambdaUtil.getPropertyName(column);
    }

    public SqlExpression<SqlPredicate<?>> getQueryFilterExpression(){
        if(entityMetadata.enableLogicDelete()){
            return entityMetadata.getLogicDeleteMetadata().getLogicDeleteQueryFilterExpression();
        }
        return null;
    }
    public SqlExpression<SqlColumnSetter<?>> getDeletedSqlExpression(){
        if(entityMetadata.enableLogicDelete()){
            return entityMetadata.getLogicDeleteMetadata().getLogicDeletedSqlExpression();
        }
        return null;
    }

    /**
     * 获取查询表的链接方式
     * @return
     */
    public String getSelectTableSource(){
        if(MultiTableTypeEnum.LEFT_JOIN.equals(multiTableType)){
            return  " LEFT JOIN ";
        }
        else if(MultiTableTypeEnum.INNER_JOIN.equals(multiTableType)){
            return  " INNER JOIN ";
        }
        else if(MultiTableTypeEnum.RIGHT_JOIN.equals(multiTableType)){
            return  " RIGHT JOIN ";
        }
        return " FROM ";
    }

}
