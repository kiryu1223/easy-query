package com.easy.query.core.proxy.core;

import com.easy.query.core.expression.builder.AggregateFilter;
import com.easy.query.core.expression.builder.Filter;
import com.easy.query.core.expression.builder.OrderSelector;
import com.easy.query.core.expression.builder.Setter;
import com.easy.query.core.expression.lambda.SQLActionExpression;
import com.easy.query.core.proxy.SQLAggregatePredicateExpression;
import com.easy.query.core.proxy.SQLColumnSetExpression;
import com.easy.query.core.proxy.SQLOrderByExpression;
import com.easy.query.core.proxy.SQLPredicateExpression;
import com.easy.query.core.proxy.core.accpet.AggregatePredicateEntityExpressionAcceptImpl;
import com.easy.query.core.proxy.core.accpet.EntityExpressionAccept;
import com.easy.query.core.proxy.core.accpet.OrderByEntityExpressionAcceptImpl;
import com.easy.query.core.proxy.core.accpet.PredicateEntityExpressionAcceptImpl;
import com.easy.query.core.proxy.core.accpet.SetterEntityExpressionAcceptImpl;

/**
 * create time 2023/12/8 14:56
 * 文件说明
 *
 * @author xuejiaming
 */
public interface EntitySQLContext {
    default void _where(Filter filter, SQLActionExpression sqlActionExpression){
        accept(new PredicateEntityExpressionAcceptImpl(filter),sqlActionExpression);
    }
    default void _whereOr(SQLActionExpression sqlActionExpression){
        throw new UnsupportedOperationException();
    }
    default void _having(AggregateFilter aggregateFilter, SQLActionExpression sqlActionExpression){
        accept(new AggregatePredicateEntityExpressionAcceptImpl(aggregateFilter),sqlActionExpression);
    }
    default void _orderBy(OrderSelector orderSelector, SQLActionExpression sqlActionExpression){
        accept(new OrderByEntityExpressionAcceptImpl(orderSelector),sqlActionExpression);
    }
    default void _set(Setter setter, SQLActionExpression sqlActionExpression){
        accept(new SetterEntityExpressionAcceptImpl(setter),sqlActionExpression);
    }
    void accept(EntityExpressionAccept accept, SQLActionExpression sqlActionExpression);
    void accept(SQLPredicateExpression sqlPredicateExpression);
    void accept(SQLAggregatePredicateExpression sqlAggregatePredicateExpression);
    void accept(SQLColumnSetExpression sqlColumnSetExpression);
    void accept(SQLOrderByExpression sqlOrderByExpression);
}
