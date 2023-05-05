package com.easy.query.core.basic.plugin.interceptor;

import com.easy.query.core.expression.parser.core.SqlWherePredicate;
import com.easy.query.core.expression.sql.builder.LambdaEntityExpressionBuilder;

/**
 * @FileName: GlobalQueryFilterInterceptor.java
 * @Description: 文件说明
 * @Date: 2023/3/7 22:25
 * @author xuejiaming
 */
public interface EasyPredicateFilterInterceptor extends EasyInterceptor {


    /**
     * 配置
     * @param entityClass
     * @param lambdaEntityExpression
     * @param sqlPredicate
     */
    void configure(Class<?> entityClass, LambdaEntityExpressionBuilder lambdaEntityExpression, SqlWherePredicate<Object> sqlPredicate);
}
