package com.allen.learningbootautoconfigure.swagger2;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author admin
 * @version 1.0.0 @Description TODO
 * @createTime 2019/07/15 14:54:00
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({Swagger2AutoConfiguration.class})
public @interface EnableSwagger2Doc {

}
