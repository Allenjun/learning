package com.allen;

/**
 * Unit test for simple ThreadApplication.
 */
public class AppTest {
    
    public static void main(String[] args) {
        String str2 = "SEUCalvin";//新加的一行代码，其余不变
        String str1 = new String("SEU") + new String("Calvin");
        str1 = str1.intern();
        System.out.println(str1 == "SEUCalvin");
    }
    
}

class Father {
    
    public static int i = 0;     // 1
    
    static {     // 2
        System.out.println("Father static block");
    }
    
    public int j = 0;     // 5
    
    {     // 6
        System.out.println("Father not static block");
    }

    public Father() {     // 7
        System.out.println("Father Constructor");
    }
}

class Test extends Father {
    
    public static int i = 0;     // 3

    static {     // 4
        System.out.println("Test static block");
    }
    
    public int j = 0;     // 8
    
    {     // 9
        System.out.println("Test not static block");
    }
    
    public Test() {     // 10
        System.out.println("Test Constructor");
    }
    
    public static void main(String[] args) throws Exception {
        new Test();
    }
}
