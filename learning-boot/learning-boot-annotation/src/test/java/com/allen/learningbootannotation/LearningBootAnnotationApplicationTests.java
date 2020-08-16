package com.allen.learningbootannotation;

import com.allen.demospringbootstarter.Author;
import com.allen.learningbootannotation.config.ImportPropertyConfiguration;
import com.allen.learningbootannotation.service.Saler;
import com.allen.otherpackage.Killer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LearningBootAnnotationApplicationTests {
    
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    Saler saler;
    @Autowired
    ImportPropertyConfiguration importPropertyConfiguration;
    @Autowired
    Killer killer;
    @Autowired
    Author author;
    
    @Test
    public void test1() {
        saler.sale();
    }
    
    @Test
    public void test2() {
        System.out.println(importPropertyConfiguration.getName());
    }
    
    @Test
    public void test3() {
        killer.kill();
    }
    
    @Test
    public void test4() {
        author.introduce();
    }
    
}
