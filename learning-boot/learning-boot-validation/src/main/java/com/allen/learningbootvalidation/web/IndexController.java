package com.allen.learningbootvalidation.web;

import com.allen.learningbootvalidation.pojo.InfoReq;
import com.allen.learningbootvalidation.pojo.MultiReq;
import com.allen.learningbootvalidation.validator.PhoneNumber;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Validated
public class IndexController {


    @GetMapping("/phone")
    public String hello(@PhoneNumber String phone) {
        return phone;
    }

    @PostMapping("/hello")
    public InfoReq hello(@RequestBody @Validated InfoReq infoReq) {
        return infoReq;
    }

    @GetMapping("/multi")
    public MultiReq multi(@Validated MultiReq multiReq) {
        return multiReq;
    }
}
