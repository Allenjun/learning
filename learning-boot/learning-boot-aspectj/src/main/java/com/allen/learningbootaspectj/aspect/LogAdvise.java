package com.allen.learningbootaspectj.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author JUN
 * @Description TODO
 * @createTime 12:09
 */
@Component
@Aspect
public class LogAdvise {
    
    @Pointcut("execution(* com.allen.learningbootaspectj.service..*(..))")
    public void pointCut() {
    }
    
    @Before("pointCut()")
    public void log() {
        System.out.println("Before execute...");
    }
    
}
