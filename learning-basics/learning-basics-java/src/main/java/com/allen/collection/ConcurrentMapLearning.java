package com.allen.collection;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 在JDK1.5中，ConcurrentHashMap通过分段锁（默认16个）来实现并发，每个segment就是一个锁，segment中有多个桶
 * <p>
 * 在JDK1.8以后，ConcurrentHashMap通过CAS和synchronized来实现并发，具体是锁住每一个桶中的头节点，也就是concurrencyLevel在JDK1.8中已经没用
 */
public class ConcurrentMapLearning {

    @Test
    public void test1() {
        Map<Object, Object> concurrentHashMap = new ConcurrentHashMap<>(13, 0.75f, 32);
    }
}
