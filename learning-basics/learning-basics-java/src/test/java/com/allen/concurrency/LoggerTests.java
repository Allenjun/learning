package com.allen.concurrency;

import org.junit.Test;

public class LoggerTests {

    @Test
    public void test() {
        MyLogger myLogger = new MyLogger(MyLogger.class);
        for (int i = 0; i < 50; i++) {
            myLogger.info("数字" + i);
        }
    }
}
