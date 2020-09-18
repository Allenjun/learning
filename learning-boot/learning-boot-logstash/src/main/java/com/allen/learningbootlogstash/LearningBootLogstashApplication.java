package com.allen.learningbootlogstash;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@SpringBootApplication
public class LearningBootLogstashApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningBootLogstashApplication.class, args);
    }

    @GetMapping("/log/{msg}")
    public String log(@PathVariable String msg) {
        log.info(msg);
        return msg;
    }
}
