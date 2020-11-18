package com.allen.learningbootcache;

import com.allen.learningbootcache.service.LogServiceWithCaffeineCache;
import com.allen.learningbootcache.service.LogServiceWithRedisCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chan
 * @date 2020/10/23
 * description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LearningBootCacheApplicationTests {

    @Autowired
    LogServiceWithRedisCache logServiceWithRedisCache;

    @Autowired
    LogServiceWithCaffeineCache logServiceWithCaffeineCache;

    @Test
    public void test1() {
        System.out.println(logServiceWithRedisCache.get("aa"));
        System.out.println(logServiceWithRedisCache.get("aa"));
    }

    @Test
    public void test2() {
        System.out.println(logServiceWithCaffeineCache.get("aa"));
        System.out.println(logServiceWithCaffeineCache.get("aa"));
    }

}
