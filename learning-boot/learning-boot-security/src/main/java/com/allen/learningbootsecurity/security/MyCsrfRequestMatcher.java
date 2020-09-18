package com.allen.learningbootsecurity.security;

import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author JUN @Description 匹配需要进行CSRF验证的请求的，SpringSecurity默认是拦截POST/PUT/PUT/PATCH/DELETE请求
 * @createTime 16:56
 */
@Component
public class MyCsrfRequestMatcher implements RequestMatcher {

    private final HashSet<String> allowedMethods =
            new HashSet<>(Arrays.asList("GET", "HEAD", "TRACE", "OPTIONS"));

    @Override
    public boolean matches(HttpServletRequest request) {
        return !allowedMethods.contains(request.getMethod());
    }
}
