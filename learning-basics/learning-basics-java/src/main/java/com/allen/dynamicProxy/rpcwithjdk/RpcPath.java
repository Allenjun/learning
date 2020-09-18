package com.allen.dynamicProxy.rpcwithjdk;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RpcPath {

    String value() default "";
}
