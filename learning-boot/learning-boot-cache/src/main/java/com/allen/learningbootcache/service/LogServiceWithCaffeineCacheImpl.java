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
@CacheConfig(cacheNames = "log", cacheManager = "caffeineCacheManager")
public class LogServiceWithCaffeineCacheImpl implements LogServiceWithCaffeineCache {
    @Override
    @Cacheable
    public String get(String username) {
        System.out.println("get");
        return "allen";
    }

    @Override
    public String del(String username) {
        System.out.println("del");
        return "success";
    }
}
