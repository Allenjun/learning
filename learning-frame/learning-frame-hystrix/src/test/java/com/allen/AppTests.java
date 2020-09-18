package com.allen;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import rx.Observable;
import rx.Observer;

@Slf4j
public class AppTests {

    @Test
    @SneakyThrows
    public void test1() {
        CommandFallback commandFallback = new CommandFallback();
        String s = commandFallback.execute();
        System.out.println(s);
    }

    @Test
    @SneakyThrows
    public void test3() {
        HystrixCommand commandObserver = new CommandObserver();
        Observable<String> observe = commandObserver.observe();
        // 观察者模式
        observe.subscribe(
                new Observer<String>() {
                    // 当请求完成时
                    @Override
                    public void onCompleted() {
                        log.info("rpc结果返回");
                    }

                    /** 当依赖服务故障时， 注意：如果重写了Fallback(),那么就不会触发该方法 */
                    @Override
                    public void onError(Throwable e) {
                        log.error("rpc调用失败", e);
                    }

                    // 当请求响应并返回结果时
                    @Override
                    public void onNext(String s) {
                        log.info("rpc响应：{}", s);
                    }
                });
        Thread.sleep(2000);
    }

    @Test
    @SneakyThrows
    public void testCache() {
        HystrixRequestContext hystrixRequestContext = HystrixRequestContext.initializeContext();
        try {
            HystrixCommand commandFallback1 = new CommandCache();
            System.out.println(commandFallback1.execute());
            HystrixCommand commandFallback2 = new CommandCache();
            System.out.println(commandFallback2.execute());
        } finally {
            hystrixRequestContext.shutdown();
        }
    }
}
