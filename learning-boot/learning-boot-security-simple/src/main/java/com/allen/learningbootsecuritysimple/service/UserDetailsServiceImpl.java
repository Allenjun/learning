package com.allen.learningbootsecuritysimple.service;

import com.allen.learningbootsecuritysimple.entity.DO.UserEntity;
import com.allen.learningbootsecuritysimple.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

/**
 * @author admin
 * @version 1.0.0 @Description TODO
 * @createTime 2019/06/27 11:40:00
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> op = userRepository.findOneByUsername(username);
        UserEntity entity = op.orElseThrow(() -> new UsernameNotFoundException("username"));
        return new User(username, entity.getPassword(), Collections.emptyList());
    }
}
