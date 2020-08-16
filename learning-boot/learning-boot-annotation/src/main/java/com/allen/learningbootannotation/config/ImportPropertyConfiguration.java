package com.allen.learningbootannotation.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author JUN
 * @Description TODO
 * @createTime 16:44
 */
@Configuration
@ConfigurationProperties(prefix = "info")
@PropertySource("classpath:info.properties")
public class ImportPropertyConfiguration {
    
    private String name;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
