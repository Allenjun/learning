package com.allen;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class ObservableCommandHelloWorld extends HystrixObservableCommand<String> {

    public ObservableCommandHelloWorld() {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
    }

    @Override
    protected Observable<String> construct() {
        return Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> observer) {
                        try {
                            if (!observer.isUnsubscribed()) {
                                observer.onNext("Hello");
                                observer.onCompleted();
                            }
                        } catch (Exception e) {
                            observer.onError(e);
                        }
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
