package com.allen.learningbootmvc.config;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author JUN
 * @Description TODO
 * @createTime 17:33
 */
public class MyExceptionResolver implements HandlerExceptionResolver {
    
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
        Exception ex) {
        try {
            response.getWriter().write("MyExceptionResolver: " + ex.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView();
    }
}
