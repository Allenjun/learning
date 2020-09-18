package com.allen;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class CommandFallback extends HystrixCommand<String> {

    protected CommandFallback() {
        super(
                Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                        .andCommandPropertiesDefaults(
                                HystrixCommandProperties.Setter()
                                        .withExecutionTimeoutInMilliseconds(2000))); // 请求超时时间2s
    }

    @Override
    protected String run() throws Exception {
        Thread.sleep(3000);
        return "商品：苹果";
    }

    /**
     * 1. 当依赖服务故障/超时,采取降级处理
     */
    @Override
    protected String getFallback() {
        return "商品：NULL";
    }
}
