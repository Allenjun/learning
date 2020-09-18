package com.allen.learningbootsecurity.security;

import com.allen.learningbootsecurity.service.RBACService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author JUN @Description TODO
 * @createTime 14:26
 */
@Component
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private RBACService rbacService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails userDetails = rbacService.loadUserByUsername(s);
        if (userDetails != null) {
            return userDetails;
        }
        throw new UsernameNotFoundException(s);
    }
}
