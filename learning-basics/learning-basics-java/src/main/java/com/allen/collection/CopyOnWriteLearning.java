package com.allen.collection;

import com.github.javafaker.Faker;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteLearning {

    private final static Faker faker = new Faker();

    /**
     *  copyOnWriteArrayList使用写时复制技术，对于列表的修改操作都是基于副本，修改完成后替代旧列表，此乃 Fail-Safe
     *  遍历时修改元素也不会抛出异常，但是数据的实时性会降低。
     */
    @Test
    public void test1() {
        List<Object> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            copyOnWriteArrayList.add(faker.name().fullName());
        }
        for (Object o : copyOnWriteArrayList) {
            copyOnWriteArrayList.remove(o);
        }
    }

    /**
     *
     *  在遍历中删除元素，会抛出ConcurrentModificationException，此乃 Fail-Fast（快速失败）
     *  原理：遍历前保存元素个数，当发现expectCount > size时，证明元素被删除
     */
    @Test
    public void test2() {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(faker.name().fullName());
        }
        for (Object o : list) {
            list.remove(o);
        }
    }

}
