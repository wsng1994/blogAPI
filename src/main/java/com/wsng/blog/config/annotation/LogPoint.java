package com.wsng.blog.config.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Cooper
 * @Date: 2021/4/12 20:05
 * @Version 0.01
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogPoint {
    String printLog() default "打印日志";
}
