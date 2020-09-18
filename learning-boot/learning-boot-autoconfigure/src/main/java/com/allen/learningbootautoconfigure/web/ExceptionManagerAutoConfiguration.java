package com.allen.learningbootautoconfigure.web;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 * @version 1.0.0 @Description TODO
 * @createTime 2019/07/15 15:12:00
 */
@Configuration
@ConditionalOnWebApplication
@ComponentScan(basePackageClasses = {ExceptionManager.class})
public class ExceptionManagerAutoConfiguration {

}
