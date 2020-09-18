package com.allen.learningcloudfhr.hystrix;

import com.netflix.hystrix.*;
import org.springframework.web.client.RestTemplate;


public class ClassHystrix extends HystrixCommand<String> {

    private RestTemplate restTemplate;

    public ClassHystrix(RestTemplate restTemplate) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("class_group"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("demo"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("demo_pool"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(2000).withExecutionTimeoutEnabled(true))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(10)));
        this.restTemplate = restTemplate;
    }

    @Override
    protected String run() throws Exception {
        return restTemplate.getForEntity("https://www.baidu.com/", String.class).getBody();
    }

}
