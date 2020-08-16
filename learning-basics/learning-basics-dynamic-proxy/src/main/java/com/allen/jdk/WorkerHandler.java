package com.allen.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author admin
 * @version 1.0.0
 * @Description TODO
 * @createTime 2019/07/16 18:35:00
 */
public class WorkerHandler implements InvocationHandler {
    
    private Object target;
    
    public Object newInstance(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始代理");
        Object invoke = method.invoke(target, args);
        System.out.println("结束代理");
        return invoke;
    }
}
