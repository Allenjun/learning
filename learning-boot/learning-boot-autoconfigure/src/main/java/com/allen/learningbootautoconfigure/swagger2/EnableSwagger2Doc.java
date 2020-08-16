package com.allen.learningbootautoconfigure.swagger2;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

/**
 * @author admin
 * @version 1.0.0
 * @Description TODO
 * @createTime 2019/07/15 14:54:00
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ Swagger2AutoConfiguration.class })
public @interface EnableSwagger2Doc {

}
