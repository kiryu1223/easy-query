package org.easy.query.core.expression.lambda;

/**
 *
 * @FileName: OnFunction.java
 * @Description: 文件说明
 * @Date: 2023/2/4 22:31
 * @Created by xuejiaming
 */
@FunctionalInterface
public interface SqlExpression2<T1,T2> {

    void apply(T1 p1,T2 p2);
}