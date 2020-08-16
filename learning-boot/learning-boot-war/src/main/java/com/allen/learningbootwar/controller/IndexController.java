package com.allen.learningbootwar.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JUN
 * @Description TODO
 * @createTime 21:42
 */
@RestController
public class IndexController {
    
    @RequestMapping("/hello")
    public String hello(String name) {
        return "hello, " + name;
    }
    
}
