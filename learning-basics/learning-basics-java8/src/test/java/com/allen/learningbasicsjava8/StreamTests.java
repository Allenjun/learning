package com.allen.learningbasicsjava8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;

/**
 * @author admin
 * @version 1.0.0
 * @Description Java8新特性: 1. lambada
 * <p>
 * 2. Stream 数据流，不会改变数据源，分为中间操作、结束操作，中间操作会在结束操作开始时才执行，也就是中间操作是Lazy执行。
 * @createTime 2019/06/21 11:38:00
 */
public class StreamTests {
    
    List<String> names;
    List<Integer> nums;
    List<Person> persons;
    
    @Before
    public void prepareData() {
        names = new ArrayList<>(Arrays.asList("mark", "jane", "jane", "dannie", "manu", "lernade"));
        nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        persons = new ArrayList<>(
            Arrays.asList(new Person("mark", 1), new Person("jane", 2), new Person("dannie", 3)));
    }
    
    @Test
    public void testForEach() {
        names.forEach(System.out::println);
    }
    
    @Test
    public void testStream() {
        System.out.println("* 中间操作符");
        System.out.println(names);
        names.stream()
            .distinct()     // 去重，返回元素唯一的Stream，
            .filter(name -> !"mark".equals(name))       // 返回满足断言的Stream数据
            .map(name -> name + ", ")   // 返回经过转换的（其他类型/数据）的Stream
            .forEach(System.out::print);
        
        System.out.println();
        names.stream()
            .flatMap(name -> Arrays.stream(name.split("")))     //  将每次遍历产生的Stream，全部放到一个Stream中
            .limit(10)      // 限制Stream返回的数量
            .skip(3)        // 跳过N个元素
            .forEach(System.out::print);
        
        System.out.println();
        
        System.out.println("* match");
        System.out.println(nums);
        boolean anyMatch = nums.stream()
            .anyMatch(num -> num > 5);  // 任意一个元素断言成功，返回True，Stream没元素，返回True
        System.out.println(anyMatch);
        boolean allMatch = nums.stream()
            .allMatch(num -> num > 5);  // 所有的元素断言成功，返回True
        System.out.println(allMatch);
        boolean noneMatch = nums.stream()
            .noneMatch(num -> num > 5);  // 所有的元素断言失败，返回True
        System.out.println(noneMatch);
        
        System.out.println("* count");
        long count = names.stream()
            .count();       // 返回Stream的数量
        System.out.println(count);
        
        System.out.println("* find");
        Optional<String> findAny = names.stream()
            .findAny();    // 返回任意一个元素，没有则返回NULL的Optional
        System.out.println(findAny.orElse(null));
        Optional<String> findFirst = names.stream()
            .findFirst();    // 返回第一个元素，没有则返回NULL的Optional
        System.out.println(findFirst.orElse(null));
        
        System.out.println("* max/min");
        Optional<String> max = names.stream()
            .max(Comparator.comparingInt(String::length));  // 根据Comparator返回最大值
        System.out.println(max.orElse(null));
        Optional<String> min = names.stream()
            .min(Comparator.comparingInt(String::length));  // // 根据Comparator返回最小值
        System.out.println(min.orElse(null));
        
        String[] toArray = names.stream()
            .toArray(String[]::new);        // Stream转数组
        System.out.println(Arrays.toString(toArray));
        List<String> list = names.stream()
            .collect(Collectors.toList());  // Stream转List
        System.out.println(list);
        Map<Integer, String> map = persons.stream()
            .collect(
                Collectors.toMap(Person::getId, Person::getName, (k1, k2) -> k1));     // Stream转Map
        System.out.println(map);
        
        System.out.println("* reduce");
        Integer sum = nums.stream()
            .reduce(0, (acc, item) -> {
                acc += item;
                return acc;
            });     // 计算，acc为上一次运算产生的值，item为当前值，可以指定初始值，
        System.out.println(sum);
        
        System.out.println("* Collector");
        Map<Boolean, List<String>> partitioningBy = names.stream()
            .collect(Collectors.partitioningBy(name -> name.length() > 5));     // Stream 分组
        System.out.println(partitioningBy);
        Map<Integer, List<String>> groupingBy = names.stream()
            .collect(Collectors.groupingBy(String::length));        // Stream 分组
        System.out.println(groupingBy);
        
    }
    
    @Data
    @AllArgsConstructor
    static class Person {
        
        private String name;
        private Integer id;
    }
    
}
