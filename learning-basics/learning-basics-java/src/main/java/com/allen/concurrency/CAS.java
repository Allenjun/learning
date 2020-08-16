package com.allen.concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * @author JUN
 * @Description TODO
 * @createTime 11:43
 */
public class CAS {
    
    @Test
    @SneakyThrows
    public void atomic() {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        atomicInteger.compareAndSet(1, 2);
        
        // 通过添加版本号解决ABA问题
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1, 1);
        atomicStampedReference.compareAndSet(1, 2, 1, atomicStampedReference.getStamp() + 1);
    }
    
}
