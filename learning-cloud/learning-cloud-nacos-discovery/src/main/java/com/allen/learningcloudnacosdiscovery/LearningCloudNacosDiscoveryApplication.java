package com.allen.learningcloudnacosdiscovery;

import com.allen.ribbon.DefaultConfiguration;
import com.allen.ribbon.DiscoveryConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients(basePackages = "com.allen.learningcloudnacosdiscovery")
@EnableDiscoveryClient
@SpringBootApplication
@RibbonClients(
        value = {
                @RibbonClient(
                        name = "learning-cloud-nacos-discovery",
                        configuration = DiscoveryConfiguration.class)
        },
        defaultConfiguration = DefaultConfiguration.class)
public class LearningCloudNacosDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningCloudNacosDiscoveryApplication.class, args);
    }

    @FeignClient(name = "learning-cloud-nacos-discovery", fallback = EchoServiceFallback.class)
    public static interface EchoService {

        @GetMapping(value = "/namespace/{name}")
        String echo(@PathVariable("name") String name);

        @GetMapping(value = "/config/name")
        String config();
    }

    @Component
    public static class EchoServiceFallback implements EchoService {

        @Override
        public String echo(String name) {
            return "fallback";
        }

        @Override
        public String config() {
            return "fallback";
        }
    }

    @Configuration
    public static class ClientConfiguration {

        @LoadBalanced
        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }

    @RefreshScope
    @RestController
    public class EchoController {

        @Value("${name:xxx}")
        private String name;

        @GetMapping(value = "/namespace/{name}")
        public String echo(@PathVariable String name) {
            return "Hello Nacos Discovery " + name;
        }

        @GetMapping(value = "/config/name")
        public String config() {
            return name;
        }
    }
}
