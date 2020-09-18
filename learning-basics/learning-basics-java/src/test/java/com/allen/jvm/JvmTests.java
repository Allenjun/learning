package com.allen.jvm;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;
import sun.misc.Unsafe;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class JvmTests {

    /**
     * 只有被同一个类加载器加载的前提下，类对象才会相等
     */
    public static void main(String[] args) throws Exception {
        ClassLoader loader =
                new ClassLoader() {
                    @Override
                    public Class<?> loadClass(String name) throws ClassNotFoundException {
                        try {
                            String filename = name.substring(name.lastIndexOf(".") + 1) + ".class";
                            InputStream is = getClass().getResourceAsStream(filename);
                            if (is == null) {
                                return super.loadClass(name);
                            }
                            byte[] b = new byte[is.available()];
                            is.read(b);
                            return defineClass(name, b, 0, b.length);
                        } catch (IOException e) {
                            throw new ClassNotFoundException(name);
                        }
                    }
                };
        Object obj = loader.loadClass("com.allen.jvm.JvmTests").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj.getClass().getClassLoader());
        System.out.println(JvmTests.class.getClassLoader());
        System.out.println(obj instanceof JvmTests);
    }

    /**
     * -XX:PermSize=10M -XX:MaxPermSize=10M 此种测试只在JDK1.6测试有效。 JDK1.7的String.intern()已修改实现，并不会导致AreaMethod的OutOfMemoryError
     */
    @Test
    public void testAreaMethod() {
        ArrayList<String> strings = new ArrayList<>();
        int i = 0;
        while (true) {
            strings.add(String.valueOf(i++).intern());
        }
    }

    /**
     * -XX:PermSize=10M -XX:MaxPermSize=10M JDK1.8的VM_OPTIONS中已移除-XX:PermSize -XX:MaxPermSize
     */
    @Test
    public void testAreaMethod2() {

        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(
                    new MethodInterceptor() {

                        @Override
                        public Object intercept(
                                Object o, Method method, Object[] objects, MethodProxy methodProxy)
                                throws Throwable {
                            return methodProxy.invokeSuper(o, objects);
                        }
                    });
            enhancer.create();
        }
    }

    /**
     * 1. -XX:MaxDirectMemorySize=10M，限制申请的最大直接内存 2. -Xmx20M，当不指定 -XX:MaxDirectMemorySize 的时候，则以
     * -Xmx （最大堆内存）作为可以申请的最大直接内存
     */
    @Test
    public void testDirectMemoryOOM() throws IllegalAccessException {
        Field declaredField = Unsafe.class.getDeclaredFields()[0];
        declaredField.setAccessible(true);
        Unsafe unsafe = (Unsafe) declaredField.get(null);
        while (true) {
            unsafe.allocateMemory(1024 * 1024);
        }
    }

    /**
     * -Xms10m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=gc.hprof
     */
    @Test
    public void testHeapOOM() {
        ArrayList<OOMObject> oomObjects = new ArrayList<>();
        while (true) {
            oomObjects.add(new OOMObject());
        }
    }

    /**
     * -Xss128k，栈容量大小
     */
    @Test
    public void testStackOverflow() {
        testStackOverflow();
    }

    @Test
    public void testClassload() {
        // 情况一：触发SuperClass的类加载，没有触发SubClass的类加载
        System.out.println(SubClass.val);

        // 情况二：没有触发SubClass类加载
        SubClass[] subClasses = new SubClass[10];

        // 情况三：没有触发SubClass类加载
        System.out.println(SubClass.HELLOWORLD);
    }

    @Test
    public void testClinit() throws InterruptedException {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread() + "start");
                        ClinitClass clinitClass = new ClinitClass();
                        System.out.println(Thread.currentThread() + "end");
                    }
                })
                .start();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread() + "start");
                        ClinitClass clinitClass = new ClinitClass();
                        System.out.println(Thread.currentThread() + "end");
                    }
                })
                .start();

        Thread.sleep(5000);
    }

    static class OOMObject {

    }

    /**
     * 以下情形会触发类加载： 1. 当new一个实例对象、访问类的静态字段、执行类的静态方法 2. 通过反射调用 3. 当加载一个类的时候，如果发现父类还没有加载，触发父类的加载 4.
     * 执行main方法的类会被初始化
     *
     * <p>下列情形不会触发类加载： 1. 通过子类引用父类的静态字段，不会触发子类的加载 2. 通过数组引用，不会触发类加载 3. 访问类的常量字段(final)，不会触发类加载
     */
    public static class SuperClass {

        static int val = 1;

        static {
            System.out.println("SuperClaSS Init");
        }
    }

    public static class SubClass extends SuperClass {

        static final int HELLOWORLD = 2;

        static {
            System.out.println("SubClass Init");
        }
    }

    /**
     * 类的初始化阶段——执行<clinit()>方法阶段 1. 编译器收集类的所有静态变量和静态代码块语句合并产生<clinit()>方法 2.
     * <clinit()>的执行是并发安全的，如果多个线程初始化一个类，只有一个类可以执行初始化方法，其他线程进入阻塞
     */
    public static class ClinitClass {

        static {
            if (true) {
                while (true) {
                }
            }
        }
    }
}
