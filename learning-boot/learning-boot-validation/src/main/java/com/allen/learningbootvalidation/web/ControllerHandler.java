package com.allen.learningbootvalidation.web;

import com.allen.learningbootvalidation.pojo.Result;
import com.allen.learningbootvalidation.pojo.ResultCode;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * @author chan
 * @date 2020/10/22
 * description: TODO
 */
@RestControllerAdvice
public class ControllerHandler implements ResponseBodyAdvice<Object> {
    // 处理非@Requestbody的对象参数校验
    @ExceptionHandler(BindException.class)
    public Result BindExceptionHandler(BindException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(objectError -> ((FieldError) objectError).getField() + ": " + objectError.getDefaultMessage()).collect(Collectors.joining());
        return Result.fail(ResultCode.PARAMVALIDATEERROR);
    }

    //处理方法级别上的参数校验，需要在Controller类上添加 @Validated 注解
    @ExceptionHandler(ConstraintViolationException.class)
    public Result ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        return Result.fail(ResultCode.PARAMVALIDATEERROR);
    }

    //处理 @Requestbody 的对象参数校验
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return Result.fail(ResultCode.PARAMVALIDATEERROR);
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof Result) {
            return body;
        }
        return Result.success(body);
    }


}
