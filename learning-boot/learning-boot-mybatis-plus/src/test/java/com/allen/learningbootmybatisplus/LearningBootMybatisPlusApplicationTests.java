package com.allen.learningbootmybatisplus;

import com.allen.learningbootmybatisplus.entity.UserEntity;
import com.allen.learningbootmybatisplus.mapper.UserMapper;
import com.allen.learningbootmybatisplus.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
class LearningBootMybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    TransactionTemplate transactionTemplate;

    @Test
    public void testQuery() {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.likeRight("name", "J");
        Page<UserEntity> userPage = userMapper.selectPage(new Page<>(0, 3), wrapper);
        System.out.println(userPage.getRecords());
    }

    @Test
    public void test() {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.likeRight("name", "J");
        Page<UserEntity> userPage = userService.getBaseMapper().selectPage(new Page<>(0, 3), wrapper);
        System.out.println(userPage.getRecords());
    }

    @Test
    public void testUpdate() {
        UserEntity entity = userService.getBaseMapper().selectById("1380099118591905794");
        entity.setAge(100);
        userService.updateById(entity);
    }

    @Test
    public void testInsert() {
        UserEntity entity = new UserEntity();
        entity.setName("Jack");
        entity.setAge(18);
        entity.setEmail("1078974797@qq.com");
        userService.save(entity);
    }

    @Test
    public void testDelete() {
        userService.removeById("1380092536550330369");
    }

    @Test
    public void testTransaction() {

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                testUpdate();
            }
        });
        testQuery();
    }
}
