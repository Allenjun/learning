package com.allen.learningbootcache.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author chan
 * @date 2020/10/23
 * description: TODO
 */
@Configuration
@EnableCaching
public class CacheConfiguration {

    @Bean
    @Primary
    public CacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheManager redisCacheManager = RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(redisConnectionFactory)
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5)))
                .withInitialCacheConfigurations(ImmutableMap
                        .of("log", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10)))
                        .of("book", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(30))))
                .build();
        return redisCacheManager;
    }

    @Bean
    public CacheManager caffeineCacheManager() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterAccess(Duration.ofMinutes(5))
                .initialCapacity(100)
                .maximumSize(100)
        );
        caffeineCacheManager.setCacheNames(ImmutableList.of("log", "book"));
        return caffeineCacheManager;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            String key = target.getClass().getName()
                    + ":" + method.getName()
                    + ":" + Arrays.stream(params).map(param -> param.getClass().getName()).collect(Collectors.joining(","));
            return key;
        };
    }

}
