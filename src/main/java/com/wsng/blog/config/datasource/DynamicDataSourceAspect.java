package com.wsng.blog.config.datasource;

import com.wsng.blog.config.annotation.TargetDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


/**
 *  @Author Sean
 *  @Date: 2021/4/11 19:11
 *  @Version 0.01
 *
 */

@Aspect
@Order(-1)
@Component
public class DynamicDataSourceAspect {
    @Pointcut("execution(* com.wsng.blog.action.*.*.executor*(..))")
    public void pointCut() {
    }

    /**
     * 执行方法前更换数据源
     *
     * @param joinPoint        切点
     * @param targetDataSource 动态数据源
     */
    @Before("@annotation(targetDataSource)")
    public void doBefore(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        DataSourceKey dataSourceKey = targetDataSource.dataSourceKey();

        if (dataSourceKey == DataSourceKey.DB_CK) {
//            System.out.println("设置数据源"+DataSourceKey.DB_CK);
            DataSourceContextHolder.set(DataSourceKey.DB_CK);
        } else {
//            System.out.println("设置数据源"+DataSourceKey.DB_BLOG);
            DataSourceContextHolder.set(DataSourceKey.DB_BLOG);
        }
    }

    /**
     * 执行方法后清除数据源设置
     *
     * @param joinPoint        切点
     * @param targetDataSource 动态数据源
     */
    @After("@annotation(targetDataSource)")
    public void doAfter(JoinPoint joinPoint, TargetDataSource targetDataSource) {
//        System.out.println("当前数据源  %s  执行清理方法");
//        LOG.info(String.format("当前数据源  %s  执行清理方法", targetDataSource.dataSourceKey()));
        DataSourceContextHolder.clear();
    }

    @Before(value = "pointCut()")
    public void doBeforeWithSlave(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //获取当前切点方法对象
        Method method = methodSignature.getMethod();
        if (method.getDeclaringClass().isInterface()) {//判断是否为接口方法
            try {
                //获取实际类型的方法对象
                method = joinPoint.getTarget().getClass()
                        .getDeclaredMethod(joinPoint.getSignature().getName(), method.getParameterTypes());
            } catch (NoSuchMethodException e) {
                System.err.println("方法不存在！"+e);
            }
        }
        if (null == method.getAnnotation(TargetDataSource.class)) {
            DataSourceContextHolder.setSlave();
        }
    }


}
