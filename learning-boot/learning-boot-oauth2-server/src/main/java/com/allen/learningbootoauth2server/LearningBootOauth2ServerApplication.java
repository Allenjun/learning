package com.allen.learningbootoauth2server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class LearningBootOauth2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningBootOauth2ServerApplication.class, args);
    }

    @GetMapping("/code")
    public String code(@RequestParam String code) {
        return code;
    }

    @GetMapping("/callback")
    public String callback() {
        return "";
    }
}
