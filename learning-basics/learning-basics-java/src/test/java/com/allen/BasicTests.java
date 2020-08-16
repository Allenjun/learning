package com.allen;

import org.junit.Test;

public class BasicTests {

    final int fa = 1;

    @Test
    public void ufunc() {
        int a = 3;
        int b = 8;
        System.out.println(a | b);  // 按位或
        System.out.println(a & b);  // 按位与
        System.out.println(b >> 2); // /2/2
        System.out.println(b << 2); // *2*2
        System.out.println(a ^ b);  // 按位异或
    }

    @Test
    public void classload() {
        new LoaddSon();
    }

    @Test
    public void singleObject() {
        Singlea.getInstance();
    }


}

class Singlea {
    private static Singlea ins = null;

    private Singlea() {
    }

    public static Singlea getInstance() {
        if (ins == null) {
            synchronized (Singlea.class) {
                if (ins == null) {
                    ins = new Singlea();
                }
            }
        }
        return ins;
    }
}

class Loadd {
    public Loadd() {
        System.out.println("Block Method");
    }

    {
        System.out.println("Block");
    }

    static {
        System.out.println("static Block");
    }
}

class LoaddSon extends Loadd {
    public LoaddSon() {
        System.out.println("son Block Method");
    }

    {
        System.out.println("son Block");
    }

    static {
        System.out.println("son static Block");
    }
}
