package com.allen.learningbootmvc.config;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author JUN
 * @Description TODO
 * @createTime 13:37
 */
public class MyHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {
    
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return true;
    }
    
    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest) throws Exception {
        System.out.println("我是返回值处理器，但是我什么都不做");
    }
}
