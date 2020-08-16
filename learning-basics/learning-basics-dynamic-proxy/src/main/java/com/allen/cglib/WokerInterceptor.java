package com.allen.cglib;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author admin
 * @version 1.0.0
 * @Description TODO
 * @createTime 2019/07/16 20:50:00
 */
public class WokerInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
        throws Throwable {
        System.out.println("开始cglib代理");
        Object invokeSuper = methodProxy.invokeSuper(o, objects);
        System.out.println("结束cglib代理");
        return invokeSuper;
    }
}
