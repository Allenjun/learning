package com.allen.learningbootsecuritysimple;

import com.allen.learningbootsecuritysimple.entity.DO.UserEntity;
import com.allen.learningbootsecuritysimple.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LearningBootSecuritySimpleApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void contextLoads() {
        List<UserEntity> all = userRepository.findAll();
        System.out.println(all);
    }
}
