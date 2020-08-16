package com.allen.learningbootmybatis.config;

import com.github.pagehelper.PageInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JUN
 * @Description TODO
 * @createTime 16:11
 */
@Slf4j
@Configuration
//@MapperScan(basePackages = "com.allen.learningbootmybatis.mapper")
public class MybatisConfiguration {
    
    @Bean
    Interceptor logInterceptor() {
        return new LogInterceptor();
    }
    
    @Bean
    Interceptor pageInterceptor() {
        return new PageInterceptor();
    }
    
    @Bean
    TypeHandler sexTypeHandler() {
        return new SexTypeHandler();
    }
}
