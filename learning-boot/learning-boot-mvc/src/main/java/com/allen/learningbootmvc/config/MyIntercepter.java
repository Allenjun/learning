package com.allen.learningbootmvc.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author JUN
 * @Description 拦截器步骤：
 *              1. 自定义拦截器，实现逻辑
 *              2. 把拦截器添加到WebMvcConfigurer的addInterceptors()方法中
 *              【注意：只是在拦截器类上添加@Component是不会被加入到拦截器链中的】
 * @createTime 12:06
 */
public class MyIntercepter implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return true;
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) {
        
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
        Exception ex) {
        System.out.println("afterCompletion捕获异常， 被ExceptionResolver处理的异常获取不到:" + ex); // 不一定可以捕获异常，具体原因可以看源码
    }
}
