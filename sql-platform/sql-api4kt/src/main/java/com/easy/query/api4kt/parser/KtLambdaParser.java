package com.easy.query.api4kt.parser;

import kotlin.reflect.KProperty1;

/**
 * create time 2023/11/7 10:38
 * 文件说明
 *
 * @author xuejiaming
 */
public interface KtLambdaParser {
    <T> String getPropertyName(KProperty1<? super T, ?> property);
}
