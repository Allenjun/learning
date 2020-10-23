package com.allen.learningbootaspectj.service;

import org.springframework.stereotype.Service;

/**
 * @author JUN @Description TODO
 * @createTime 12:09
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello() {
//        int i = 1 / 0;
        System.out.println("hello");
        return "success";
    }
}
