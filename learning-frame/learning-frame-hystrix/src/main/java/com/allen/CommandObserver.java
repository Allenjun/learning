package com.allen;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class CommandObserver extends HystrixCommand<String> {

    protected CommandObserver() {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
    }

    @Override
    protected String run() throws Exception {
        if (true) {
            throw new RuntimeException();
        }
        return "商品：苹果";
    }
}
