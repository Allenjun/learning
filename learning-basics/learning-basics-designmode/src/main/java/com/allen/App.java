package com.allen;

/**
 * Hello world!
 *
 */
public class App {
    
    public static void main(String[] args) {
        Single2 single2 = Single2.getInstance();
        System.out.println(single2);
        single2 = Single2.getInstance();
        System.out.println(single2);
    }
}

/* 双重检查 */
class Single1 {
    
    private static volatile Single1 INSTANCE;
    
    private Single1() {

    }
    
    public static Single1 getInstance() {
        if (INSTANCE == null) {
            synchronized (Single1.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Single1();
                }
            }
        }
        return INSTANCE;
    }
}

/* 静态内部类 */
class Single2 {
    
    private Single2() {

    }
    
    public static Single2 getInstance() {
        return InnerClass.INSTANCE;
    }
    
    private static class InnerClass {
        
        private final static Single2 INSTANCE = new Single2();
    }
}
