package com.allen.learningcloudservice.controller;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author JUN
 * @Description TODO
 * @createTime 21:27
 */
@Slf4j
@RestController
public class ProductController {
    
    private AtomicInteger store = new AtomicInteger(100);
    
    @Value("${server.port}")
    private int port;
    @Value("${spring.application.name}")
    private String appname;
    @Value("${info.name}")
    private String name;
    
    @GetMapping("/storeTimeOut")
    public String storeTimeOut() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return appname + ":" + port + ":" + store.get();
    }
    
    @GetMapping("/store")
    public String store() {
        return appname + ":" + port + ":" + store.get();
    }
    
    @GetMapping("/consume")
    public String consume() {
        return appname + ":" + port + ":" + store.decrementAndGet();
    }
    
    @GetMapping("/cloud-config")
    public String cloudConfig() {
        return name;
    }
    
    @GetMapping("/seulth")
    public String seulth() {
        log.info("trace-2");
        return "trace-2";
    }
    
    @PostMapping("/upload-image")
    public String uploadImage(MultipartFile image) {
        return image.getOriginalFilename();
    }
}
