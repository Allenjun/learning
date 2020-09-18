package com.allen.concurrency;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

public class MyLogger {

    private final ExecutorService exec = new ThreadPoolExecutor(1, 1, 2000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(),
            Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    private Class clazz;


    public MyLogger(Class clazz) {
        this.clazz = clazz;
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    stop();
                } catch (InterruptedException e) {
                }
            }
        }));
    }

    public void info(String message) {
        exec.execute(new LoggerRunable(message));
    }

    public void stop() throws InterruptedException {
        exec.shutdown();
        exec.awaitTermination(5000, TimeUnit.MILLISECONDS);
    }

    private static class MyLoggerFactory implements ThreadFactory {

        private ThreadFactory threadFactory = Executors.defaultThreadFactory();

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = threadFactory.newThread(r);
            thread.setUncaughtExceptionHandler((t, e) -> {
            });
            return thread;
        }
    }

    private class LoggerRunable implements Runnable {

        private String message;

        LoggerRunable(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            if (!Thread.currentThread().isInterrupted()) {
                System.out.println(MessageFormat.format("[{0}][{1}]: {2}",
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")),
                        MyLogger.this.clazz.getCanonicalName(), this.message));
            }
        }
    }


}
