package com.allen.learningcloudclient.service;

import com.allen.learningcloudclient.config.CloudConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "learning-cloud-service",
    configuration = CloudConfiguration.FeignConfiguration.class,
//    fallback = CloudConfiguration.ClientFallback.class,
    fallbackFactory = CloudConfiguration.ClientFallbackFactory.class)
public interface Client {
    
    @GetMapping("/store")
    String store();
    
    @GetMapping("/storeTimeOut")
    String storeTimeOut();
    
    @PostMapping(value = "/upload-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadImage(MultipartFile image);
    
    @GetMapping("/seulth")
    String seulth();
    
}
