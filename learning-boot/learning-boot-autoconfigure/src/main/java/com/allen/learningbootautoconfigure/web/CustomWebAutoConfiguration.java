package com.allen.learningbootautoconfigure.web;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author admin
 * @version 1.0.0
 * @Description TODO
 * @createTime 2019/07/15 15:41:00
 */
@Configuration
@ConditionalOnWebApplication
@Import({})
public class CustomWebAutoConfiguration {

    @Bean
    public HandlerInterceptor requestInterceptor() {
        return new LogInterceptor();
    }

}
