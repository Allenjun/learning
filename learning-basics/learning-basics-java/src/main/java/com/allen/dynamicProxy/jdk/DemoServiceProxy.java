package com.allen.dynamicProxy.jdk;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DemoServiceProxy implements InvocationHandler {

    private Object target;

    public Object getInstance(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            System.out.println("代理开始。。。");
            Object o = method.invoke(target, args);
            System.out.println("代理响应。。。" + o);
            return o;
        } finally {
            System.out.println("代理结束。。。");
        }
    }


    /**
     * JDK代理缺点：
     * 1. 面向接口的代理，生成的$Proxy0代理类默认继承Proxy并实现被代理的接口
     */
    @Test
    public void test1() {
        DemoService demoService = new DemoServiceImpl();
        DemoService service = (DemoService) new DemoServiceProxy().getInstance(demoService);
        service.info();
    }

    @Test
    public void test2() throws IOException {
        byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
                "com.allen.dynamicProxy.jdk.$Proxy0", new Class<?>[]{DemoService.class}, 3);
        FileUtils.writeByteArrayToFile(new File("com.allen.dynamicProxy.jdk.$Proxy0.class"), proxyClassFile);
    }
}
