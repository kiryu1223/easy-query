package com.easy.query.core.annotation;

import com.easy.query.core.basic.extension.version.VersionStrategy;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;

/**
 * 乐观锁,一个对象只能有一个乐观锁
 * 更新时会将version作为条件和下次设置的值
 * @author xuejiaming
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface Version {
    /**
     * 版本号策略
     * @return 所使用的版本号策略
     */
    Class<? extends VersionStrategy> strategy();
}
