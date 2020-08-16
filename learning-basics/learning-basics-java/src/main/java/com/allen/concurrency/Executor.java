package com.allen.concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * @author JUN
 * @Description TODO
 * @createTime 12:22
 */
public class Executor {
    
    @Test
    @SneakyThrows
    public void futureTaskTest() {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {

            @Override
            public String call() throws Exception {
                return "异步返回";
            }
        });
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
    
    @Test
    @SneakyThrows
    public void pool() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 5, 5000, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(5), r -> null, new AbortPolicy());
        Future<Boolean> submit = executor.submit(new Callable<Boolean>() {
            
            @Override
            public Boolean call() throws Exception {
                return null;
            }
        });
        submit.get();
        
        executor.shutdown();    // 设置状态为SHUTDOWN，不接受新任务，等待正在运行的任务和队列中的任务运行完毕
        executor.shutdownNow();     // 设置状态为STOP，不接受新任务，尝试结束正在运行的任务，清空并返回队列中的任务
        
        Executors.newCachedThreadPool();    // 会出现创建线程过多问题
        Executors.newSingleThreadExecutor();    // 只创建一个线程，但是队列容量无限
        Executors.newFixedThreadPool(5);    // 只创建5个线程，但是队列容量无限
    }
    
    /**
     * @description: 优雅地关闭线程池
     * @params
     *   @param: pool
     * @return:
     * @throw
     */
    private void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown();
        try {
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow();
                if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("Pool did not terminate");
                }
            }
        } catch (InterruptedException ie) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
