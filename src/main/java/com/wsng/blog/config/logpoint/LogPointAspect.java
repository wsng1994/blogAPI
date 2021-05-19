package com.wsng.blog.config.logpoint;

import com.wsng.blog.config.annotation.LogPoint;
import com.wsng.blog.config.annotation.LogSE;
import com.wsng.blog.config.base.ParamFilter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *  @Author Sean
 *  @Date: 2021/4/12 19:54
 *  @Version 0.01
 *
 */
@Aspect
@Order(-2)
@Component
public class LogPointAspect  {

    private static final Logger logger = LoggerFactory
            .getLogger(LogPointAspect.class);


    @Pointcut("execution(* com.wsng.blog.action.*.*.*(..))")
    public void pointCut() {
    }
    //测试日志切面工作顺序
    @Around("@annotation(logPoint)")
    public Object calculateTransTime(ProceedingJoinPoint joinPoint, LogPoint logPoint) throws Throwable {

        Object obj = null;
        Object[] args = joinPoint.getArgs();
        //原子服务开始时间
        long startTime = System.currentTimeMillis();
        Signature signature = joinPoint.getSignature();
//        MethodSignature methodSignature = (MethodSignature) signature;
//        String[] parameterNames = methodSignature.getParameterNames();
        obj = joinPoint.proceed(args);
        //原子服务结束时间
        long endTime = System.currentTimeMillis();
        //原子服务耗时
//        long time = endTime - startTime;
        //原子服务
        String clazz = signature.getDeclaringTypeName();
        //原子服务方法名
        String methodName = signature.getName();

        MDC.put("strMilsTime",""+startTime);
        MDC.put("nodeName","1");
        MDC.put("endMilsTime",""+endTime);
        MDC.put("clazz",clazz);
        MDC.put("method",methodName);
        logger.info("交易中埋点");
        return obj;

    }

    //测试日志切面工作顺序
    @Before("@annotation(logSE)")
    public Object serviceStart(JoinPoint joinPoint, LogSE logSE) throws Throwable {

        //原子服务
        String clazz = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        //原子服务开始时间
        long startTime = System.currentTimeMillis();

        MDC.put("strMilsTime",""+startTime);
        MDC.put("nodeName","0");
        MDC.put("endMilsTime","----");
        MDC.put("clazz",clazz);
        MDC.put("method",methodName);

        logger.info("交易开始埋点");


        return null;

    }

    @After("@annotation(logSE)")
    public Object serviceEnd(JoinPoint joinPoint, LogSE logSE) throws Throwable {

        //原子服务
        Object cls = joinPoint.getSignature();
        String clazz = ((Signature) cls).getDeclaringTypeName();

        String methodName = ((Signature) cls).getName();
        //原子服务开始时间
        long endTime = System.currentTimeMillis();

        MDC.put("strMilsTime","----");
        MDC.put("nodeName","2");
        MDC.put("endMilsTime",""+endTime);
        MDC.put("clazz",clazz);
        MDC.put("method",methodName);
        logger.info("交易结束埋点");
        return null;

    }

}
