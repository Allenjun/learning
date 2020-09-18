package com.allen.learningcloudopenfeign.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author JUN @Description TODO
 * @createTime 21:39
 */
@Configuration
@EnableFeignClients(
        basePackages = "com.allen.learningcloudopenfeign.service.openfeign")
public class CloudConfiguration {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplateRibbon() {
        return new RestTemplate();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
