package com.allen.learningbootsecurity.security;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

/**
 * @author JUN
 * @Description TODO
 * @createTime 23:11
 */
@Component
public class MyCorsConfigurationSource implements CorsConfigurationSource {
    
    @Override
    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        return corsConfiguration;
    }
}
