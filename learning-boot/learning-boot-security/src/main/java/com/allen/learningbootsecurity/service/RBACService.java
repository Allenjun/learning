package com.allen.learningbootsecurity.service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public interface RBACService {
    
    UserDetails loadUserByUsername(String name);
    
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
    
}
