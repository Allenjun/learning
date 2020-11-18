package com.allen.java8;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stream8 {

    private final static Faker faker = new Faker();

    private final static Map<String, String> books = new HashMap<>();
    private final static List<String> addrs = new ArrayList<>();
    private final static List<String> names = new ArrayList<>();
    private final static List<Entry> entrys = new ArrayList<>();

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
        entrys.add(new Entry("allen", "1234"));
        entrys.add(new Entry("allen", "1234"));
        entrys.add(new Entry("allen", "2313"));
    }

    /**
     * 去重
     *  1: 使用 Collectors.collectingAndThen
     *  2: 使用 filter,传入方法参数
     *  3. lombok的 @Data注解默认重写 equals() 和 hashcode()，直接用
     */
    @Test
    public void test0() {
        entrys.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Entry::getCode))), ArrayList::new));
        entrys.stream().filter(distinctByKey(Entry::getCode)).collect(Collectors.toList());
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
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

    @Data
    @AllArgsConstructor
    public static class Entry {
        private String name;
        private String code;
    }

}
