package com.allen.learningbootaspectj.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author JUN
 * @Description TODO
 * @createTime 12:09
 */
@Service
@Transactional
public class HelloServiceImpl implements HelloService {
    
    @Override
    public void hello() {
        System.out.println("hello");
    }
}
