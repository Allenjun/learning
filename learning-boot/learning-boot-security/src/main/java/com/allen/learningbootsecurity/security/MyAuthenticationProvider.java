package com.allen.learningbootsecurity.security;

import com.allen.learningbootsecurity.pojo.BO.UserBO;
import com.google.common.collect.Sets;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        UserDetails userDetails = check(username, password);
        if (userDetails == null) {
            throw new BadCredentialsException("Login Fail");
        }
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());
    }

    private UserDetails check(String username, String password) {
        return UserBO.builder().username(username).password(password).authorities(Sets.newHashSet(new SimpleGrantedAuthority("ADMIN"))).build();
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
