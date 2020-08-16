package com.allen.learningbootvalidation.web;

import com.allen.learningbootvalidation.pojo.InfoReq;
import com.allen.learningbootvalidation.pojo.MultiReq;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@RestController
public class IndexController {

    @ExceptionHandler(ConstraintViolationException.class)
    public String handler(ConstraintViolationException ex) {
        return ex.getMessage();
    }
/*

    @GetMapping("/phone")
    public String phone(@Validated @PhoneNumber String phone) {
        return "your phone is: " + phone;
    }
*/

    @PostMapping("/hello")
    public InfoReq hello(@Validated(InfoReq.Idyes.class) @RequestBody InfoReq infoReq) {
        return infoReq;
    }

    @GetMapping("/multi")
    public MultiReq multi(@Validated MultiReq multiReq) {
        return multiReq;
    }
}
