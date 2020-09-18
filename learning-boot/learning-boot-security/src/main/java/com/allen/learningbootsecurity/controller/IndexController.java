package com.allen.learningbootsecurity.controller;

import org.springframework.http.MediaType;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

/**
 * @author JUN @Description TODO
 * @createTime 23:47
 */
@RestController
public class IndexController {

    @GetMapping(
            value = "/index",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public String index() {
        return "index";
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

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

    @GetMapping("/csrf")
    public CsrfToken csrf(CsrfToken csrfToken) {
        return csrfToken;
    }

    @GetMapping("/dynamicPerm")
    public String dynamicPerm() {
        return "dynamicPerm";
    }

    @RolesAllowed({"NORMAL"})
    @PostMapping("/postRequest")
    public String postRequest(@RequestBody String name) {
        return name;
    }
}
