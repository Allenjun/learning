package com.allen.learningbootmvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author JUN @Description
 * @createTime 17:42
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

  /*@Override
  public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
      // 这个方法是会覆盖默认的HandlerExceptionResolvers
      resolvers.add(new MyExceptionResolver());
  }*/

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyIntercepter());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    }

  /*@Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
      // 这个方法是会覆盖默认的HttpMessageConverters
      converters.add(new MyHttpMessageConverter());
  }*/

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new MyHandlerMethodArgumentResolver());
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        handlers.add(new MyHandlerMethodReturnValueHandler());
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 这个方法是在默认的HttpMessageConverters基础上拓展
        converters.add(new MyHttpMessageConverter());
    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(new MyExceptionResolver());
    }
}
