package com.allen.dynamicProxy.rpcwithjdk;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class Rpc {

    private final static OkHttpClient client = new OkHttpClient();

    private interface Target<T> {
        String url();

        Class<T> type();
    }

    private interface MethodHandler {
        Object invoke(Object[] args) throws Throwable;
    }

    private class RpcMethodHandler implements MethodHandler {

        private Target target;
        private Method method;

        public RpcMethodHandler(Target target, Method method) {
            this.target = target;
            this.method = method;
        }

        @Override
        public Object invoke(Object[] args) throws Throwable {
            Request request = new Request.Builder()
                    .get()
                    .url(target.url() + method.getAnnotation(RpcPath.class).value())
                    .build();
            return client.newCall(request).execute().body().string();
        }
    }

    private class TargetImpl<T> implements Target<T> {

        private Class<T> type;
        private String url;

        public TargetImpl(Class<T> type, String url) {
            this.type = type;
            this.url = url;
        }

        @Override
        public String url() {
            return this.url;
        }

        @Override
        public Class<T> type() {
            return this.type;
        }
    }

    public <T> T target(Class<T> clazz) {
        Target<T> target = createTarget(clazz);
        Map<Method, MethodHandler> dispather = createDispather(target);
        InvocationHandler handler = createInvocationHandler(target, dispather);
        return (T) Proxy.newProxyInstance(target.type().getClassLoader(), new Class<?>[]{target.type()}, handler);
    }

    private <T> InvocationHandler createInvocationHandler(Target<T> target, Map<Method, MethodHandler> dispather) {
        return (proxy, method, args) -> dispather.get(method).invoke(args);
    }

    private <T> Map<Method, MethodHandler> createDispather(Target<T> target) {
        Map<Method, MethodHandler> dispather = new HashMap<>();
        for (Method method : target.type().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(RpcPath.class)) {
                continue;
            }
            dispather.put(method, new RpcMethodHandler(target, method));
        }
        return dispather;
    }

    private <T> Target<T> createTarget(Class<T> clazz) {
        RpcClient annotation = clazz.getAnnotation(RpcClient.class);
        return new TargetImpl<T>(clazz, annotation.url());
    }

}
