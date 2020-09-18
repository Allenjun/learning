package com.allen.learningbootsecurity.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author JUN @Description TODO
 * @createTime 15:05
 */
@Component
public class RedisLockHelper {

    private static final Long RELEASE_SUCCESS = 1L;
    private static RedisTemplate redisTemplate;

    public RedisLockHelper(RedisTemplate redisTemplate) {
        RedisLockHelper.redisTemplate = redisTemplate;
    }

    @SuppressWarnings({"unchecked", "ConstantConditions"})
    public static boolean tryLock(String key, String requestId, int expireTime) {
        for (; ; ) {
            if (redisTemplate
                    .opsForValue()
                    .setIfAbsent(key, requestId, expireTime, TimeUnit.MILLISECONDS)) {
                return true;
            }
        }
    }

    @SuppressWarnings({"unchecked", "ConstantConditions"})
    public static boolean release(String key, String requestId) {
        return RELEASE_SUCCESS.equals(
                redisTemplate.execute(
                        RedisScript.of(
                                "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end",
                                Long.class),
                        Collections.singletonList(key),
                        requestId));
    }
}
