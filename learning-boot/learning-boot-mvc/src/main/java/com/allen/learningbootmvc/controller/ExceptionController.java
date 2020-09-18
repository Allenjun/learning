package com.allen.learningbootmvc.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

/**
 * @author JUN @Description TODO
 * @createTime 16:50
 */
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public String handleEx(HandlerMethod handlerMethod, Exception ex) {
        return "ExceptionHandler: " + ex.getMessage();
    }
}
