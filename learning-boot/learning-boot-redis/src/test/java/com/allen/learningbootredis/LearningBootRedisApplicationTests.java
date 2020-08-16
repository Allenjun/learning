package com.allen.learningbootredis;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.*;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LearningBootRedisApplicationTests {

    @Autowired
    RedissonClient redissonClient;
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void testLock() throws InterruptedException {
        String identity = UUID.randomUUID().toString();
        RLock rLock = redissonClient.getLock(identity);
        try {
            if (rLock.tryLock(5, TimeUnit.SECONDS)) {
                // todo
            }
        } finally {
            rLock.unlock();
        }
    }

    @Test
    public void testRedLock() throws InterruptedException {
        RedissonClient redissonClient1 = Redisson.create(new Config());
        RedissonClient redissonClient2 = Redisson.create(new Config());
        RedissonClient redissonClient3 = Redisson.create(new Config());
        RedissonRedLock redissonRedLock = new RedissonRedLock(redissonClient1.getLock("lock1"), redissonClient2.getLock("lock2"), redissonClient3.getLock("lock3"));
        try {
            // 等待加锁5s,设置锁过期时间3s
            if (redissonRedLock.tryLock(5, 3, TimeUnit.SECONDS)) {

            }
        } finally {
            redissonRedLock.unlock();
        }

    }

    @Test
    public void testBasicLock() {
        String KEY = "lock_product";
        String identity = UUID.randomUUID().toString();
        boolean lock = redisTemplate.opsForValue().setIfAbsent(KEY, identity, 5, TimeUnit.SECONDS).booleanValue();
        try {
            if (lock) {
                // todo
            }
        } finally {
            redisTemplate.execute(RedisScript.of("if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end", Integer.class), Arrays.asList(new String[]{KEY}), new String[]{identity});
        }
    }

    @Test
    public void testBitSet() {
        RBitSet userbits = redissonClient.getBitSet("userbits{xxx}");
        userbits.not();       // 重置
        userbits.clear(126);    //设置0
        userbits.set(126, true);    //设置1
        userbits.get(126);      // 获取第127个bit位值
        userbits.length();      // 被设置成1的最高bit位
        userbits.size();        // bitset长度
        userbits.cardinality(); // 设置成1的个数


        RBitSet userbits2 = redissonClient.getBitSet("userbit2s{xxx}");
        userbits2.set(25, true);

        userbits.xor("userbit2s{xxx}");     // 异或
        System.out.println(userbits.cardinality());

        userbits.or("userbit2s{xxx}");      // 或
        System.out.println(userbits.cardinality());

        userbits.and("userbit2s{xxx}");     // 与
        System.out.println(userbits.cardinality());


    }

    @Test
    public void testBloomFilter() {
        RBloomFilter<Object> stopword = redissonClient.getBloomFilter("stopword");
        stopword.tryInit(500000, 0.03); // 数据量500000，误错率0.03
        stopword.add("shit");
        stopword.add("fuxk");
        stopword.contains("shit");
    }

    @Test
    public void testIncr() {
        RLongAdder clicks = redissonClient.getLongAdder("clicks");  // 自增
        clicks.increment();

        clicks.sum();
    }

    @SneakyThrows
    @Test
    public void testRateLimiter() {
        RRateLimiter req_limits = redissonClient.getRateLimiter("req_limits");
        req_limits.trySetRate(RateType.OVERALL, 100, 10, RateIntervalUnit.SECONDS);     // 对所有用户，10秒内限制100访问量
        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    if (req_limits.tryAcquire(2, TimeUnit.SECONDS)) {
                        // todo
                        log.info("请求成功，返回数据...");
                    }
                    log.info("你被限流了，请稍后再试");
                }
            }).start();
        }
        Thread.sleep(200000);
    }

    @SneakyThrows
    @Test
    public void testSortSet() {
        String key = "recharge";
        ZSetOperations zSet = redisTemplate.opsForZSet();
        zSet.add(key, "allen", 100);
        zSet.add(key, "kiki", 120);
        zSet.add(key, "cici", 130);
        zSet.add(key, "carson", 10);
        zSet.add(key, "annie", 30);

        zSet.reverseRange(key, 0, 2);   // 取分数最高三个
        zSet.range(key, 0, 2);      // 取分数最低三个

        zSet.incrementScore(key, "allen", 1);   // 加1分
    }

    


}
