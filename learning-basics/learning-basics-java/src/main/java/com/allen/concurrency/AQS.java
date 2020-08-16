package com.allen.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * @author JUN
 * @Description
 *      CountDownLatch 用于一个线程等待多个线程到达某种状态后，才继续往后执行。不可重用
 *      CyclicBarrier 用于多个线程相互等待到达某种状态，然后一起往后执行。可重用
 *      Semaphore 用于控制资源同时访问的线程数
 * @createTime 11:43
 */
public class AQS {
    
    @Test
    @SneakyThrows
    public void aqs() {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        countDownLatch.countDown();
        countDownLatch.countDown();
        countDownLatch.await();
        
        Semaphore semaphore = new Semaphore(5);
        semaphore.acquire();
        semaphore.release();
        
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        cyclicBarrier.await();
        
    }
    
}
