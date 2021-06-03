package com.allen.parallel;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class Parallel {

    private Executor executor;

    @Before
    public void before() {
        executor = new ThreadPoolExecutor(8, 16, 300, TimeUnit.SECONDS, new ArrayBlockingQueue<>(8), r -> new Thread(r, UUID.randomUUID().toString()), new ThreadPoolExecutor.AbortPolicy());
    }

    @Test
    public void completedFuture() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.completedFuture("Hello!");
        stringCompletableFuture = stringCompletableFuture.handle((s, t) -> {
            if (t == null) {
                return s;
            }
            return "Error";
        });
        try {
            String s = stringCompletableFuture.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void runAsync() {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            try {
                delay(5);
                System.out.println("bbb");
            } catch (InterruptedException e) {
                System.out.println("aaa");
                Thread.currentThread().interrupt();
            }
        }, executor);
        try {
            Boolean delayExec = delayExec(2, () -> {
                return voidCompletableFuture.cancel(false);
            });
            System.out.println(delayExec);
            voidCompletableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        try {
            delay(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void delay(long seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);
    }

    private <T> T delayExec(long seconds, Supplier<T> supplier) throws InterruptedException, ExecutionException {
        return ((ExecutorService) executor).submit(() -> {
            delay(seconds);
            return supplier.get();
        }).get();
    }
}
