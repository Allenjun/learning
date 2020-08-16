package com.allen.guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.google.common.util.concurrent.RateLimiter;
import java.util.stream.IntStream;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author JUN
 * @Description 布隆过滤器、令牌桶
 * @createTime 21:43
 */
public class UtilApp {
    
    @Test
    public void bloomFilter() {
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), 50000, 0.01);
        IntStream.range(0, 32000).forEach(bloomFilter::put);
        Assert.assertTrue(bloomFilter.mightContain(26000));
    }
    
    @Test
    public void limiter() throws InterruptedException {
        RateLimiter rateLimiter = RateLimiter.create(10);
        Thread.sleep(2000);
        for (int i = 0; i < 20; i++) {
            new Thread() {
                @Override
                public void run() {
                    if (!rateLimiter.tryAcquire()) {
                        Assert.fail("限流中");
                    }
                    System.out.println("开始执行");
                }
            }.start();
        }
        Thread.sleep(10000);
    }
    
}
