package com.allen.java8;

import com.github.javafaker.Faker;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stream8 {

    private final static Faker faker = new Faker();

    private final static Map<String, String> books = new HashMap<>();
    private final static List<String> addrs = new ArrayList<>();
    private final static List<String> names = new ArrayList<>();

    static {
        books.put(faker.book().author(), faker.book().title());
        books.put(faker.book().author(), faker.book().title());
        books.put(faker.book().author(), faker.book().title());
        addrs.add(faker.address().fullAddress());
        addrs.add(faker.address().fullAddress());
        addrs.add(faker.address().fullAddress());
        names.add(faker.name().fullName());
        names.add(faker.name().fullName());
        names.add(faker.name().fullName());
        names.add(faker.name().fullName());
    }

    /**
     * 过滤
     */
    @Test
    public void test1() {
        addrs.stream().filter(addr -> !addr.contains("allen")).collect(Collectors.toList());
    }

    /**
     * 元素一对一映射
     */
    @Test
    public void test2() {
        addrs.stream().map(addr -> addr.toUpperCase()).collect(Collectors.toList());
    }

    /**
     * 查找元素
     */
    @Test
    public void test3() {
        addrs.stream().filter(addr -> "allen".equals(addr)).findAny().orElse("none");
    }

    /**
     * 每一个元素映射一个stream
     */
    @Test
    public void test4() {
        addrs.stream().flatMap(addr -> Stream.of(addr.split(" "))).collect(Collectors.toList());
    }

    /**
     * 获取指定数量/跳过指定数量
     * 相当于分页
     */
    @Test
    public void test5() {
        addrs.stream().skip(1).limit(2).collect(Collectors.toList());
    }

    /**
     * 排序
     */
    @Test
    public void test6() {
        addrs.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
    }

    /**
     * 匹配
     */
    @Test
    public void test7() {
        addrs.stream().anyMatch(addr -> "allen".equals(addr));
    }

    /**
     * 元素组合计算
     */
    @Test
    public void test8() {
        addrs.stream().reduce("", String::concat);
    }

    /**
     * 并行计算
     */
    @Test
    public void test9() {
        addrs.parallelStream().count();
    }

}
