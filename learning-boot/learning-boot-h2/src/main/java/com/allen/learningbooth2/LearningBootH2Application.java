package com.allen.learningbooth2;

import com.allen.learningbooth2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LearningBootH2Application {
    
    @Autowired
    UserRepository userRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(LearningBootH2Application.class, args);
    }
    
    @GetMapping("get/{username}")
    public String get(@PathVariable String username) {
        return userRepository.findOneByUsername(username).get().getPassword();
    }
    
}
