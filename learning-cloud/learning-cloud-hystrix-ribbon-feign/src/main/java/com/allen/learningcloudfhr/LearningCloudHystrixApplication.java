package com.allen.learningcloudfhr;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LearningCloudHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningCloudHystrixApplication.class, args);
    }

    @Configuration
    public static class HystrixConfiguration {

        @Bean
        public HystrixCommandAspect hystrixCommandAspect() {
            return new HystrixCommandAspect();
        }

        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }

    @Configuration
    public static class RibbonConfiguration {

        @Bean
        public BaseLoadBalancer baseLoadBalancer() {
            BaseLoadBalancer baseLoadBalancer = new BaseLoadBalancer();
            baseLoadBalancer.addServer(new Server("www.microsoft.com", 80));
            baseLoadBalancer.addServer(new Server("www.yahoo.com", 80));
            baseLoadBalancer.addServer(new Server("www.google.com", 80));
            baseLoadBalancer.setRule(new RandomRule());
            return baseLoadBalancer;
        }

    }

}
