package com.hjl.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author ：hjl
 * @date ：2019/11/1 13:23
 * @description： 简单的测试切面
 * @modified By：
 */
@Aspect
@Component
public class SimpleAspect {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleAspect.class);


    @Pointcut("execution(* com.hjl.service.*AopTestService*.*(..))")
    public void pointCut() {
        LOG.info("pointCut.........");
    }

    @After("pointCut()")
    public void after(JoinPoint joinPoint) {
        LOG.info("after aspect executed ,joinPoint is : {}",joinPoint);
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        //如果需要这里可以取出参数进行处理
        //Object[] args = joinPoint.getArgs();
        LOG.info("before aspect executing");
    }

    @AfterReturning(pointcut = "pointCut()", returning = "returnVal")
    public void afterReturning(JoinPoint joinPoint, Object returnVal) {
        LOG.info("afterReturning executed, return result is {}",returnVal);
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        LOG.info("around start.........");
        try {
            pjp.proceed();
        } catch (Throwable ex) {
            LOG.error("error in around ",ex);
            throw ex;
        }
        LOG.info("around end.........");
    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "error")
    public void afterThrowing(JoinPoint jp, Throwable error) {
        LOG.error("error: ",error);
    }
}
