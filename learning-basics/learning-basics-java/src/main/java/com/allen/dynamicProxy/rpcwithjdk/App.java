package com.allen.dynamicProxy.rpcwithjdk;

import org.junit.Test;


public class App {
    @Test
    public void test1() {
        CloudService cloudService = new Rpc().target(CloudService.class);
        String s = cloudService.get();
        System.out.println(s);
    }
}
