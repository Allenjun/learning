package com.allen.learningbootsecurity.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

/**
 * @author JUN @Description TODO
 * @createTime 23:47
 */
@RestController
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @RolesAllowed({"NORMAL"})
    @GetMapping("/normal")
    public String normal() {
        return "normal";
    }

    @PreAuthorize("hasRole('SYSTEM')")
    @GetMapping("/sys")
    public String sys() {
        return "SYSTEM";
    }

    @GetMapping("/dynamicPerm")
    public String dynamicPerm() {
        return "dynamicPerm";
    }

    @GetMapping("/csrf")
    public CsrfToken csrf(CsrfToken csrfToken) {
        return csrfToken;
    }

}
