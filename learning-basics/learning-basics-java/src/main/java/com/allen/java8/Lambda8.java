package com.allen.java8;

import com.github.javafaker.Faker;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于集合的迭代
 * 1. 当参数值只有一个的时候，可以省略()
 * 2. 当方法体内的代码只有一行，且输入参数与提供的参数吻合时，可简略写：books.forEach(Lambda::print);
 * 3. 用于构建更简洁的匿名内部类
 */
public class Lambda8 {

    private final static Faker faker = new Faker();

    private final static Map<String, String> books = new HashMap<>();
    private final static List<String> addrs = new ArrayList<>();

    static {
        books.put(faker.book().author(), faker.book().title());
        books.put(faker.book().author(), faker.book().title());
        books.put(faker.book().author(), faker.book().title());
        addrs.add(faker.address().fullAddress());
        addrs.add(faker.address().fullAddress());
        addrs.add(faker.address().fullAddress());
    }

    private static void print(String k, String v) {
        System.out.println(k + ": " + v);
    }

    @Test
    public void test() {
        books.forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });
        books.forEach(Lambda8::print);
    }

    private Runnable create() {
        return () -> {
            System.out.println("异步执行");
        };
    }

}
