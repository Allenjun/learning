package com.allen.learningbootoauth2resource.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

/**
 * @author JUN @Description TODO
 * @createTime 23:47
 */
@RestController
public class IndexController {

    @GetMapping(value = "/hello")
    public String hello() {
        return "hello";
    }

    @RolesAllowed({"ADMIN"})
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @RolesAllowed({"NORMAL"})
    @GetMapping("/normal")
    public String normal() {
        return "normal";
    }

    @RolesAllowed({"NORMAL"})
    @GetMapping("/common")
    public String common() {
        return "common";
    }
}
