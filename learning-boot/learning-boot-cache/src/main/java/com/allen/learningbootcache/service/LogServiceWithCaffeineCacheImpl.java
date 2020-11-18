package com.allen.learningbootcache.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
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
        System.out.println("获取数据库数据");
        return username;
    }

    @Override
    @CacheEvict
    public String del(String username) {
        return "success";
    }
}
