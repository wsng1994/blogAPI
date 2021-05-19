package com.wsng.blog.config.annotation;

import com.wsng.blog.config.datasource.DataSourceKey;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Cooper
 * @Date: 2021/4/11 19:10
 * @Version 0.01
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetDataSource {
    DataSourceKey dataSourceKey() default DataSourceKey.DB_BLOG;
}
