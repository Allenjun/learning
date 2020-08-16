package com.allen.concurrency;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * @author JUN
 * @Description TODO
 * @createTime 14:52
 */
public class ThreadApplication {
    
    @Test
    public void waitNotify() {
        Product product = new Product();
        Object lock = new Object();
        Thread produce = new Thread(() -> {
            synchronized (lock) {
                while (true) {
                    while (product.getStore() > 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    product.produce();
                    lock.notifyAll();
                }
            }
        });
        Thread consume = new Thread(() -> {
            synchronized (lock) {
                while (true) {
                    while (product.getStore() == 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    product.consume();
                    lock.notifyAll();
                }
            }
        });
        produce.start();
        consume.start();
        try {
            produce.join();
            consume.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    @SneakyThrows
    public void thread() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    System.out.println(Thread.currentThread().getName() + ": 执行完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("捕获线程异常");
            }
        });
        t1.setDaemon(true);     // 设置线程为当前线程的守护线程
        t1.start();
        t1.join();  // join方法要在start()方法之后执行

        t1.isInterrupted();  // 返回中断状态
        t1.interrupt();  // 中断线程
        Thread.interrupted();   // 返回当前线程的中断状态，并重置中断状态
        System.out.println("执行完毕");
    }
    
    @Test
    @SneakyThrows
    public void callable() {
        new Thread(new FutureTask<>(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        })).start();

    }
    
    class Product {

        int store;

        public void produce() {
            store++;
            System.out.println("生产一个，剩下: " + store);
        }

        public void consume() {
            store--;
            System.out.println("消费一个，剩下: " + store);
        }

        public int getStore() {
            return store;
        }
    }
    
}
