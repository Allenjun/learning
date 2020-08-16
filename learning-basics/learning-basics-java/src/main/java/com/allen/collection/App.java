package com.allen.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * @author JUN
 * @Description TODO
 * @createTime 12:54
 */
public class App {
    
    @Test
    public void list() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(1);
        list.addAll(new ArrayList<>());
        
        int[] nums = { 1, 2, 3 };
        List<int[]> ints = Arrays.asList(nums);
        Integer[] nums1 = { 1, 2, 3 };
        List<Integer> integers = Arrays.asList(nums1);  // Array -> List
        try {
            integers.add(4);
        } catch (Exception e) {
            System.out.println("Arrays.asList(args) 返回的List是Arrays的一个内部类，并没有实现add方法，所以会报错！！！");
        }
        ArrayList<Integer> integers1 = new ArrayList<>(integers);   // 解决Arrays.asList(args)不能添加元素的问题
        
        Object[] objects = list.toArray();  // List -> Array
        
        LinkedList<Object> linkedList = new LinkedList<>();
        linkedList.add(1);
        
        Vector<Object> vector = new Vector<>();
        
        CopyOnWriteArrayList<Object> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        
    }
    
    @Test
    public void map() {
        Hashtable<Object, Object> hashtable = new Hashtable<>();
        
        HashMap<Object, Object> hashMap = new HashMap<>();
        
        TreeMap<Object, Object> treeMap = new TreeMap<>((o1, o2) -> 0); // 传入Comparator
        
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
        
        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
        
    }
    
    @Test
    public void set() {
        HashSet<Object> hashSet = new HashSet<>();
        
        TreeSet<Object> treeSet = new TreeSet<>((o1, o2) -> 0);
        
        LinkedHashSet<Object> linkedHashSet = new LinkedHashSet<>();
        
    }
    
    @Test
    @SneakyThrows
    public void queue() {
       /* ArrayBlockingQueue<Object> arrayBlockingQueue = new ArrayBlockingQueue<>(1);
        arrayBlockingQueue.add(1);  // 添加元素，如果队列已满，则抛异常
        arrayBlockingQueue.offer(2);    // 添加元素，如果队列已满，则返回false
        arrayBlockingQueue.put(3);  // 添加元素，如果队列已满，则阻塞
        
        arrayBlockingQueue.peek();   // 返回头部元素，如果队列为空，则返回NULL
        arrayBlockingQueue.element();   // 返回头部元素，如果队列为空，则抛异常
        arrayBlockingQueue.remove();    // 删除并返回头部元素，如果队列为空，则抛异常
        arrayBlockingQueue.take();  // 删除并返回头部元素，如果队列为空，则阻塞
        arrayBlockingQueue.poll();  // 删除并返回头部元素，如果队列为空，则返回NULL
        
        LinkedBlockingQueue<Object> linkedBlockingQueue = new LinkedBlockingQueue<>(1);
        
        DelayQueue<Delayed> delayQueue = new DelayQueue<>();
        
        PriorityBlockingQueue<Object> priorityBlockingQueue = new PriorityBlockingQueue<>(1, (o1, o2) -> 0);
    
        SynchronousQueue<Object> synchronousQueue = new SynchronousQueue<>(true);*/
        
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
    }
    
}
