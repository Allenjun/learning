package com.allen.learningbootmybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.allen.learningbootmybatisplus.mapper")
public class LearningBootMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningBootMybatisPlusApplication.class, args);
    }

}
