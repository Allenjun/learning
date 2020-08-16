package com.allen.learningbootsecurity;

import com.allen.learningbootsecurity.mapper.SysMenuMapper;
import com.allen.learningbootsecurity.mapper.SysRoleMapper;
import com.allen.learningbootsecurity.mapper.SysUserMapper;
import com.allen.learningbootsecurity.pojo.DO.SysMenu;
import com.allen.learningbootsecurity.pojo.DO.SysRole;
import com.allen.learningbootsecurity.pojo.DO.SysUser;
import com.allen.learningbootsecurity.utils.RedisLockHelper;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LearningBootSecurityApplicationTests {
    
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    SysMenuMapper sysMenuMapper;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    
    @Test
    public void test1() {
        Optional<SysUser> optionalSysUser = sysUserMapper.findByUserName("root");
        if (optionalSysUser.isPresent()) {
            System.out.println(optionalSysUser.get());
            List<SysRole> sysRoles = sysRoleMapper.selectByUserId(optionalSysUser.get().getUserId());
            sysRoles.forEach(sysRole -> {
                List<SysMenu> sysMenus = sysMenuMapper.selectByRoleId(sysRole.getRoleId());
                System.out.println(sysMenus);
            });
        }
    }
    
    @Test
    public void test2() {
        String encode = new BCryptPasswordEncoder().encode("test");
        System.out.println(encode);
    }
    
    @Test
    public void test3() {
        BoundListOperations phones = redisTemplate.boundListOps("phones");
        System.out.println(phones.leftPush("apple"));
        System.out.println(phones.leftPush("huawei"));
        System.out.println(phones.leftPush("xiaomi"));
    }
    
    @Test
    public void test4() {
        BoundListOperations phones = redisTemplate.boundListOps("phones");
        Long size = phones.size();
        for (int i = 0; i < size + 1; i++) {
            System.out.println(phones.rightPop(5, TimeUnit.SECONDS));
        }
    }
    
    @Test
    public void test6() {
        String key = "/index";
        redisTemplate.opsForValue().setBit(key, 0, true);
        System.out.println(redisTemplate.opsForValue().getBit(key, 0));
        byte[] bytes = redisTemplate.getKeySerializer().serialize(key);
        System.out
            .println(
                redisTemplate.execute((RedisCallback<Long>) connection -> connection.bitCount(bytes)));
    }
    
    @Test
    public void test7() {
        String key = "book";
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        for (int i = 0; i < 1000; i = i + 5) {
            zSetOperations.add(key, "我的世界" + i, i);
            zSetOperations.add(key, "我的弟弟" + i + 1, i + 1);
            zSetOperations.add(key, "我的亚索" + i + 2, i + 2);
            zSetOperations.add(key, "我的杰斯" + i + 3, i + 3);
            zSetOperations.add(key, "我的阿木木" + i + 4, i + 4);
        }
        Cursor scan = zSetOperations.scan(key, ScanOptions.scanOptions().build());
        while (scan.hasNext()) {
            System.out.println(((TypedTuple) scan.next()).getValue());
        }
    }
    
    @Test
    public void test5() throws InterruptedException {
        class Product {
            
            int store;
            
            public void produce() {
                store++;
            }
            
            public void consume() {
                store--;
            }
            
            public int getStore() {
                return store;
            }
        }
        
        Product product = new Product();
        for (int i = 0; i < 50; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        try {
                            RedisLockHelper.tryLock("test", "123", 1000);
                            product.produce();
                        } finally {
                            RedisLockHelper.release("test", "123");
                        }
                    }
                }
            }.start();
        }
        Thread.sleep(15000);
        System.out.println(product.getStore());
    }
    
}
