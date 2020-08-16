package com.allen.learningboottransaction;

import com.allen.learningboottransaction.pojo.DO.facebook.BookDO;
import com.allen.learningboottransaction.pojo.DO.facebook.UserDO;
import com.allen.learningboottransaction.pojo.DO.google.TeacherDO;
import com.allen.learningboottransaction.service.IndexService;
import com.github.javafaker.Faker;
import java.text.MessageFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LearningBootTransactionApplicationTests {
    
    @Autowired
    IndexService indexService;
    @Autowired
    ApplicationContext applicationContext;
    
    @Test
    public void test3() {
        for (String s : applicationContext.getBeanNamesForType(IndexService.class)) {
            System.out.println(s);
        }
    }
    
    @Test
    public void test1() {
        Faker faker = new Faker();
        BookDO bookDO = new BookDO();
        bookDO.setDescription("");
        bookDO.setTitle(faker.book().title());
        
        UserDO userDO = new UserDO();
        userDO.setName(faker.name().fullName());
        
        indexService.doInAnnoTransaction(bookDO, userDO);
        
        System.out.println(MessageFormat.format("添加书本数据，id = {1}, title = {0}", bookDO.getTitle(), bookDO.getId()));
        System.out.println(MessageFormat.format("添加用户数据，id = {1}, name = {0}", userDO.getName(), userDO.getId()));
    }
    
    @Test
    public void test4() {
        Faker faker = new Faker();
        BookDO bookDO = new BookDO();
        bookDO.setDescription("");
        bookDO.setTitle(faker.book().title());
        
        UserDO userDO = new UserDO();
        userDO.setName(faker.name().fullName());
        
        indexService.doInCodeTransaction(bookDO, userDO);
        
        System.out.println(MessageFormat.format("添加书本数据，id = {1}, title = {0}", bookDO.getTitle(), bookDO.getId()));
        System.out.println(MessageFormat.format("添加用户数据，id = {1}, name = {0}", userDO.getName(), userDO.getId()));
    }
    
    @Test
    public void test2() {
        Faker faker = new Faker();
        TeacherDO teacherDO = new TeacherDO();
        teacherDO.setName(faker.funnyName().name());
        
        indexService.doInAnnoTransaction(teacherDO);
        
        System.out.println(MessageFormat.format("添加教师数据，id = {1}, name = {0}", teacherDO.getName(), teacherDO.getId()));
    }
    
    @Test
    public void test5() {
        Faker faker = new Faker();
        TeacherDO teacherDO = new TeacherDO();
        teacherDO.setName(faker.funnyName().name());
        
        indexService.doInCodeTransaction(teacherDO);
        
        System.out.println(MessageFormat.format("添加教师数据，id = {1}, name = {0}", teacherDO.getName(), teacherDO.getId()));
    }
    
}
