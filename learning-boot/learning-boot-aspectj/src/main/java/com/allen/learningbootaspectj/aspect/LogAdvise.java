package com.allen.learningbootaspectj.aspect;

import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author JUN
 * @Description 正常 around -> before -> around -> after -> afterreturning
 * 异常 around -> before -> around -> after -> afterThrowing
 * @createTime 12:09
 */
@Component
@Aspect
public class LogAdvise {

    @Pointcut("execution(public * com.allen.learningbootaspectj.service..*.*(..))")
    public void log() {
    }

    @Pointcut("execution(public * *.*(..))")
    public void time() {
    }

    @Before("log() || time()")
    public void before() {
        System.out.println("Before execute...");
    }

    @After("log()")
    public void after() {
        System.out.println("After execute...");
    }

    @SneakyThrows
    @Around("log()")
    public Object around(ProceedingJoinPoint pjp) {
        System.out.println("Around execute start...");
        Object proceed = pjp.proceed();
        System.out.println("Around execute end...");
        return proceed;
    }

    @AfterReturning(value = "log()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("AfterReturning execute...");
    }

    @AfterThrowing(value = "log()", throwing = "error")
    public void afterThrowing(JoinPoint joinPoint, Throwable error) {
        System.out.println("AfterThrowing execute...");
    }

}
