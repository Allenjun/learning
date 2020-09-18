package com.allen.learningbootmybatis;

import com.allen.learningbootmybatis.mapper.UserMapper;
import com.allen.learningbootmybatis.pojo.DO.Sex;
import com.allen.learningbootmybatis.pojo.DO.UserDO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LearningBootMybatisApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Test
    public void test1() {
        Optional<UserDO> op = userMapper.findById("1");
        System.out.println(op.orElseThrow(() -> new IllegalArgumentException("not found")));
    }

    @Test
    public void test2() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Object o =
                sqlSession.selectOne("com.allen.learningbootmybatis.mapper.UserMapper.findById", "1");
        System.out.println(o);
    }

    @Test
    public void test3() {
        List<UserDO> userDOS = userMapper.findByName("all");
        System.out.println(userDOS);
    }

    @Test
    public void test4() {
        UserDO condition = new UserDO();
        condition.setId(1);
        condition.setName("all");
        condition.setPassword("654841");
        List<UserDO> userDOS = userMapper.findByCondition(condition);
        System.out.println(userDOS);
    }

    @Test
    public void test5() {
        List<UserDO> userDOS = userMapper.findListByIds(new ArrayList<>(Arrays.asList(3, 2)));
        System.out.println(userDOS);
    }

    @Test
    public void test6() {
        UserDO user = new UserDO();
        user.setName("bebe");
        user.setPassword("123456");
        user.setSex(Sex.MALE);
        int update = userMapper.updateById(1, user);
        System.out.println(update);
    }

    @Test
    public void testA() {
        Optional<UserDO> op = userMapper.findByIdAnno("1");
        System.out.println(op.orElseThrow(() -> new IllegalArgumentException("not found")));
    }

    @Test
    public void testB() {
        UserDO condition = new UserDO();
        condition.setId(1);
        condition.setName("all");
        condition.setPassword("654841");
        Optional<UserDO> op = userMapper.findByConditionAnno(condition);
        System.out.println(op.orElseThrow(() -> new IllegalArgumentException("not found")));
    }

    @Test
    public void testC() {
        UserDO add = new UserDO();
        add.setName("all");
        add.setPassword("654841");
        add.setSex(Sex.MALE);
        int id = userMapper.insertOneAnno(add);
        System.out.println("影响记录数：" + id);
        System.out.println(add);
    }

    @Test
    public void testD() {
        UserDO add = new UserDO();
        add.setName("adsa");
        add.setPassword("654841");
        //        add.setSex(Sex.MALE);
        UserDO add2 = new UserDO();
        add2.setName("asdfferg");
        add2.setPassword(null);
        add2.setSex(Sex.MALE);
        int id = userMapper.insertAnno(new ArrayList<>(Arrays.asList(add, add2)));
        System.out.println("影响记录数：" + id);
        System.out.println(add);
        System.out.println(add2);
    }
}
