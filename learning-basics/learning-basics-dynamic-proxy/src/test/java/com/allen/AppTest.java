package com.allen;

import com.allen.cglib.WokerFilter;
import com.allen.cglib.WokerInterceptor;
import com.allen.jdk.WorkerHandler;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

/**
 * @description:
 * 1. JDK动态代理是依赖于接口的，要代理的类必须实现接口。 利用反射生成clazz
 * 2. cglib动态代理是依赖类实现代理，通过生成目标对象的子类来增强实现，不能代理final的类和方法。
 * 底层利用ASM技术来转换字节码并生成新的类，所以速度上比JDK代理要快。
 */
public class AppTest {
    
    @Test
    public void jdk() {
        Worker worker = new WorkerImpl();
        Worker workerProxy = (Worker) new WorkerHandler().newInstance(worker);
        workerProxy.work("jdk");
    }
    
    @Test
    public void cglib() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(WorkerImpl.class);
        enhancer.setCallback(new WokerInterceptor());   // 可以传入多个callback
        enhancer.setCallbackFilter(new WokerFilter());  // 返回数字代表要执行的callback下标
        WorkerImpl workerProxy = (WorkerImpl) enhancer.create();
        workerProxy.work("cglib");
        System.out.println(workerProxy.getClass().getName());
    }
}
