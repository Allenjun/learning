package com.allen.learningbootmvc.controller;

import java.util.Map;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author JUN @Description TODO
 * @createTime 21:42
 */
@RestController
public class IndexController {
    
    public String common() {
        return "";
    }
    
    @RequestMapping("/hello")
    public String hello(String name) {
        return "hello, " + name;
    }
    
    @RequestMapping(value = "/user")
    public User user(User user) {
        return user;
    }
    
    @RequestMapping(value = "/body", method = RequestMethod.POST)
    public Map body(@RequestBody Map body) {
        return body;
    }
    
    @RequestMapping("/throwEx")
    public String throwEx() {
        int i = 1 / 0;
        return "";
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @RequestMapping("/badRequest")
    public String badRequest() {
        return "";
    }
    
    @RequestMapping("/file")
    public String file(MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        return "";
    }
    
    @GetMapping(value = "/memory")
    public String leak() {
        System.out.println("模拟内存泄漏");
        ThreadLocal<Byte[]> localVariable = new ThreadLocal<Byte[]>();
        localVariable.set(new Byte[4096 * 1024]);// 为线程添加变量
        return "ok";
    }
    
    @Data
    public static class User {
        
        private String name;
        private int age;
    }
}
