package com.allen.concurrency;

/**
 * @author JUN
 * @Description TODO
 * @createTime 11:56
 */
public class VolatileApplication {
    
    static volatile boolean stop = false;
    
    public static void stopIt() {
        stop = true;
    }
    
    public static void main(String[] args) {
        new Thread1().start();
        while (!stop) {

        }
        System.out.println("stop");
    }
    
    static class Thread1 extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stopIt();
        }
    }
    
}
