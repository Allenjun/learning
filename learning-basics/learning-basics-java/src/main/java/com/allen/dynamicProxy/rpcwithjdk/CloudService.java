package com.allen.dynamicProxy.rpcwithjdk;

@RpcClient(url = "http://www.xinhuanet.com")
public interface CloudService {

    @RpcPath("/politics")
    String get();

}
