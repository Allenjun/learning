package com.allen.jvm;

import org.junit.Test;

/**
 * 实例化对象时，初始化顺序是：
 * 1. 执行类对象的初始化
 * 1.1 父类静态变量的赋0值，然后赋实际值
 * 1.2 父类执行静态代码块
 * 1.3 子类静态变量的赋0值，然后赋实际值
 * 1.4 子类执行静态代码块
 * 2. 执行对象实例化
 * 2.1 父类成员变量赋值
 * 2.2 父类执行构造代码块、构造方法
 * 2.3 子类成员变量赋值
 * 2.4 子类执行构造代码块、构造方法
 */
public class ObjectInstantiationTests {
    @Test
    public void test() {

    }

    private static class Father {

        public static int i = 0; // 1

        static { // 2
            System.out.println("Father static block");
        }

        public int j = 0; // 5

        { // 6
            System.out.println("Father not static block");
        }

        public Father() { // 7
            System.out.println("Father Constructor");
        }
    }

    private static class Son extends Father {

        public static int i = 0; // 3

        static { // 4
            System.out.println("Test static block");
        }

        public int j = 0; // 8

        { // 9
            System.out.println("Test not static block");
        }

        public Son() { // 10
            System.out.println("Test Constructor");
        }
    }
}


