package com.allen;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommandCache extends HystrixCommand<String> {

    protected CommandCache() {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
    }

    @Override
    protected String run() throws Exception {
        log.info("发送rpc请求...");
        return "商品：苹果";
    }

    /**
     * 1. 返回NULL，代表不启用请求缓存 2. 返回String，代表启用
     */
    @Override
    protected String getCacheKey() {
        //        return "goods";
        return null;
    }
}
