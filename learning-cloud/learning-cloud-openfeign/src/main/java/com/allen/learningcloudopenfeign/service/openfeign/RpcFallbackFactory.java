package com.allen.learningcloudopenfeign.service.openfeign;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RpcFallbackFactory implements FallbackFactory<RpcService> {

    @Autowired
    RpcServiceFallback rpcServiceFallback;

    @Override
    public RpcService create(Throwable throwable) {
        log.error("rpc调用出错", throwable);
        return rpcServiceFallback;
    }
}
