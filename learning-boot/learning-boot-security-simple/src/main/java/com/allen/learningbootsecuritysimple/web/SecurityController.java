package com.allen.learningbootsecuritysimple.web;

import com.allen.learningbootsecuritysimple.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 * @version 1.0.0 @Description TODO
 * @createTime 2019/06/21 17:44:00
 */
@RestController
public class SecurityController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @RequestMapping("/get/{username}")
    public String hello(@PathVariable String username) {
        return userDetailsService.loadUserByUsername(username).getPassword();
    }
}
