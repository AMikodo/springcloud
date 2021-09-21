package com.start.springcloud.util;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Map;

@Aspect
@Component
@Slf4j
public class MyAnnAop {

    @Pointcut("@annotation(com.start.springcloud.util.MyAnn)")
    public void run() {

    }

//    @Before("run()")
//    public void before(JoinPoint joinPoint) {
//        log.info("before run");
//    }
//
//    @AfterReturning(value = "run()", returning = "res")
//    public Object after(JoinPoint joinPoint, Object res) {
//        log.info("after run res is " + res);
//        Map<String, String> map = (Map<String, String>) res;
//        map.put("s", "aop put key ");
//        return map;
//    }

    @Around(value = "run()")
    public void around(ProceedingJoinPoint joinpoint) {
        try {
            joinpoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }


}
