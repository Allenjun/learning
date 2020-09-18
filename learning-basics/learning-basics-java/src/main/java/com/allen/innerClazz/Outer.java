package com.allen.innerClazz;

import org.junit.Test;

public class Outer {

    public static String name = "allen";
    public String title = "ding";

    /**
     * 方法内部类
     */
    public int withmethodClazz(int a, int b) {
        class Plus {
            public int plus(int x, int y) {
                return x + y;
            }
        }
        Plus plus = new Plus();
        return plus.plus(a, b);
    }


    /**
     * 成员内部类
     * 1. 不能有static变量、方法
     * 2. 内部有指向外部类对象的引用
     */
    public class Inner {
        // 基本类型、String类型除外
        public final static String lll = "asd";

        public void in() {
            System.out.println("标题是：" + Outer.this.title);
            withmethodClazz(1, 2);
        }
    }

    /***
     * 生命周期与外部类无关，相当于独立的类
     * 外部类加载的时候。并不会加载静态内部类，只有到真正用的时候才会加载，所以可以用静态内部类实现单例模式
     */
    public static class StaticInner {
        public void staticIn() {
            System.out.println(Outer.name);
        }
    }

    @Test
    public void test1() {
        Inner inner = new Outer().new Inner();
    }

    @Test
    public void test2() {
        StaticInner staticInner = new StaticInner();
    }
}
