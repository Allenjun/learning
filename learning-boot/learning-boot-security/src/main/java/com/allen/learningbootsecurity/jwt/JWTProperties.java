package com.allen.learningbootsecurity.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author JUN
 * @Description TODO
 * @createTime 22:26
 */
@Data
@ConfigurationProperties(prefix = "jwt")
public class JWTProperties {
    
    private String secret;
    private int expire;
    
}
