package com.allen.learningbootcache.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author chan
 * @date 2020/10/23
 * description: TODO
 */
@Service
@CacheConfig(cacheNames = "log", keyGenerator = "keyGenerator")
public class LogServiceWithRedisCacheImpl implements LogServiceWithRedisCache {
    @Override
    @Cacheable
    public String get(String username) {
        System.out.println("获取数据库数据");
        return username;
    }

    @Override
    public String del(String username) {
        return "success";
    }
}
