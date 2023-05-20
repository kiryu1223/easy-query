package com.easy.query.core.expression.segment;


/**
 * create time 2023/4/28 21:33
 * 文件说明
 *
 * @author xuejiaming
 */
public interface AggregationColumnSegment extends MaybeAggregateColumnSegment {
    default boolean isAggregateColumn(){
        return true;
    }
    @Override
    AggregationColumnSegment cloneSQLEntitySegment();
}
