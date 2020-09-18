package com.allen.learningcloudservice.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@RefreshScope
@Component
@Data
public class Info {

    @Value("${server.port}")
    private Integer port;

    @Value("${spring.application.name}")
    private String appname;

    @Value("${author}")
    private String author;
}
