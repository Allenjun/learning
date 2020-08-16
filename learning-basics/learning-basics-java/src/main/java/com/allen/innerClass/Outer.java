package com.allen.innerClass;

/**
 * @author JUN
 * @Description TODO
 * @createTime 16:42
 */
public class Outer {
    
    private static int j = 1;
    int i = 0;
    
    public static void main(String[] args) {
        
        /* 成员内部类 */
        MemberInner memberInner = new Outer().new MemberInner();
        Outer outer = new Outer();
        memberInner = outer.new MemberInner();
        
        /* 局部内部类 */
        StaticInner staticInner = new StaticInner();
        staticInner.print();
    }
    
    public void done() {
        // 外部类访问成员内部类
        MemberInner memberInner = new MemberInner();
        class TopoInner {

        }
    }
    
    public static class StaticInner {
        
        public void print() {
            System.out.println(j);
        }
    }
    
    /**
     * @description:
     *      1.
     */
    public class MemberInner {
        
        int i = 0;
        
        public void print() {
            System.out.println(i);
            System.out.println(Outer.this.i);
        }
    }
}
