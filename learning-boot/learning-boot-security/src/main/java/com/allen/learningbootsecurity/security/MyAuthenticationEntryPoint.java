package com.allen.learningbootsecurity.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * @author JUN
 * @Description 认证失败处理器
 * @createTime 12:20
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException, ServletException {
//        response.sendError(HttpStatus.UNAUTHORIZED.value(), "认证失败: " + authException.getMessage());
        response.sendRedirect("/login");
    }
}
