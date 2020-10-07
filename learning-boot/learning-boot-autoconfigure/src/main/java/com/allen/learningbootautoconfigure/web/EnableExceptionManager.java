package com.allen.learningbootautoconfigure.web;

import org.springframework.context.annotation.Import;

/**
 * @author admin
 * @version 1.0.0 @Description TODO
 * @createTime 2019/07/15 15:12:00
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ExceptionManagerAutoConfiguration.class})
public @interface EnableExceptionManager {

}
