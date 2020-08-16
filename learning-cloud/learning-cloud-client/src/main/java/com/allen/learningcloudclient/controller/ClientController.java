package com.allen.learningcloudclient.controller;

import com.allen.learningcloudclient.service.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author JUN
 * @Description TODO
 * @createTime 21:41
 */
@Slf4j
@RestController
@RequestMapping("/client")
public class ClientController {
    
    @Autowired
    LoadBalancerClient loadBalancerClient;
    @Autowired
    @Qualifier("restTemplate")
    RestTemplate restTemplate;
    @Autowired
    @Qualifier("restTemplateRibbon")
    RestTemplate restTemplateRibbon;
    @Autowired
    Client client;
    
    @GetMapping("/store")
    public String store() {
        ServiceInstance instance = loadBalancerClient.choose("learning-cloud-service");
        return restTemplate
            .getForObject("http://" + instance.getHost() + ":" + instance.getPort() + "/store", String.class);
    }
    
    @GetMapping("/store-ribbon")
    public String storeRibbon() {
        return restTemplateRibbon
            .getForObject("http://learning-cloud-service/store", String.class);
    }
    
    @GetMapping("/store-feign")
    public String storeFeign() {
        return client.store();
    }
    
    @GetMapping("/store-feign-hystrix")
    public String storeFeignHystrix() {
        return client.storeTimeOut();
    }
    
    @GetMapping("/log-sleuth")
    public String sleuth() {
        log.info("trace-1");
        return client.seulth();
    }
    
}
