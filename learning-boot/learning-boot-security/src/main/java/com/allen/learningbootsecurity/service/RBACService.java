package com.allen.learningbootsecurity.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;

public interface RBACService {

    UserDetails loadUserByUsername(String name);

    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
