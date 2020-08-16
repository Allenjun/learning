package com.allen.learningcloudclient.config;

import com.allen.learningcloudclient.service.Client;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.form.spring.SpringFormEncoder;
import feign.hystrix.FallbackFactory;
import feign.hystrix.HystrixFeign;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author JUN
 * @Description TODO
 * @createTime 21:39
 */
@Configuration
@EnableFeignClients(basePackages = "com.allen.learningcloudclient.service") // 启用Feign声明式服务调用
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
    
    @SuppressWarnings("unchecked")
    @Bean(name = "hystrixRegistrationBean")
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(
            new HystrixMetricsStreamServlet(), "/actuator/hystrix.stream");
        registration.setName("HystrixMetricsStreamServlet");
        registration.setLoadOnStartup(1);
        return registration;
    }
    
    @Configuration
    public class FeignConfiguration {
        
        @Bean
        public SpringFormEncoder springFormEncoder() {
            return new SpringFormEncoder();     // 使用Feign客户端上传文件需要表单提交编码器
        }
        
        @Bean
        public Request.Options options() {
            return new Request.Options(10000, 5000);
        }
        
        @Bean
        public Retryer retryer() {
            return new Retryer.Default(100, 1000, 4);
        }
        
        @Bean
        @Scope("prototype")
        public Feign.Builder feignHystrixBuilder() {
            return HystrixFeign.builder().options(options()).retryer(retryer());
        }
        
    }
    
    @Component
    public class ClientFallbackFactory implements FallbackFactory<Client> {
        
        @Override
        public Client create(Throwable throwable) {
            return new Client() {
                @Override
                public String store() {
                    return "自定义降级策略";
                }
                
                @Override
                public String storeTimeOut() {
                    return "自定义降级策略";
                }
                
                @Override
                public String uploadImage(MultipartFile image) {
                    return "自定义降级策略";
                }
                
                @Override
                public String seulth() {
                    return "自定义降级策略";
                }
            };
        }
    }
    
    @Component
    public class ClientFallback implements Client {
        
        @Override
        public String store() {
            return "fallback";
        }
        
        @Override
        public String storeTimeOut() {
            return "fallback";
        }
        
        @Override
        public String uploadImage(MultipartFile image) {
            return "fallback";
        }
        
        @Override
        public String seulth() {
            return "fallback";
        }
    }
    
}
