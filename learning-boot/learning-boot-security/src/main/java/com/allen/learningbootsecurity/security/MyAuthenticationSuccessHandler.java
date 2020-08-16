package com.allen.learningbootsecurity.security;

import com.allen.learningbootsecurity.jwt.JWTTokenUtil;
import com.allen.learningbootsecurity.jwt.JWTTokenUtil.Payload;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author JUN
 * @Description TODO
 * @createTime 23:24
 */
@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws ServletException, IOException {
        String jws = JWTTokenUtil.generateToken(Payload.builder().sub(authentication.getName()).build());
        Cookie bearer = new Cookie("Bearer", jws);
        bearer.setMaxAge(3600);
        response.addCookie(bearer);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
