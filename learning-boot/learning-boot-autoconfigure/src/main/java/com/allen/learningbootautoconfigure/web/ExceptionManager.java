package com.allen.learningbootautoconfigure.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ExceptionManager {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public RestMessage doExceptionWithJson(Exception ex) {
        log.error("", ex);
        return RestMessage.fail(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(BindException.class)
    public RestMessage doExceptionWithJson2(BindException ex) {
        String error =
                ex.getAllErrors().stream()
                        .map(ObjectError::getDefaultMessage)
                        .collect(Collectors.joining(","));
        return RestMessage.fail(error);
    }
}
