package com.allen.learningbootaspectj;

import com.allen.learningbootaspectj.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LearningBootAspectjApplicationTests {

    @Autowired
    HelloService helloService;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void test1() {
        System.out.println(helloService.hello());
    }

    @Test
    public void test2() {
        HelloService bean = applicationContext.getBean(helloService.getClass());
        bean.hello();
        System.out.println(applicationContext);
        System.out.println(bean);
    }
}
