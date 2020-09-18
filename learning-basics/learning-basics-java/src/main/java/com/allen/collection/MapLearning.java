package com.allen.collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 知识点：
 * 1. 当链表长度达到8且桶数量达到64，才会转成红黑树
 * 2. 当红黑树的节点数量达到6，就会退回到链表
 */
public class MapLearning {


    /**
     * 桶数量是2的幂次方的原因：
     * 1. 当桶数量是2的幂次方时，tableSize-1就是11111，而HashMap计算桶位置的时候，是采用 （tableSize-1） & hash ,这样即达到取模效果，效率也更快。
     * 而如果桶数量不是2的幂次方时，tableSize-1就是11110，即出现 0 位，后续&运算就会损失一位，即那一位永远也取不到 1 ，浪费桶位置。
     * <p>
     * 2. 扩容时，桶数量 << 1（即1倍），rehash后的桶位置，要么是原来的位置，要么是(oldIndex + oldThreshold)，扩容效率提高
     */
    @Test
    public void test1() {
        int i = 1 << 4;
        System.out.println("扩容前桶数量：" + i);
        System.out.println("hash值为5的key，在hash桶中的位置：" + ((i - 1) & 5));
        System.out.println("hash值为21的key，在hash桶中的位置：" + ((i - 1) & 21));
        i = i << 1;
        System.out.println("扩容后桶数量：" + i);
        System.out.println("hash值为5的key，在hash桶中的位置：" + ((i - 1) & 5));
        System.out.println("hash值为21的key，在hash桶中的位置：" + ((i - 1) & 21));
    }

    /**
     * 如果不指定构造参数：默认(16, 0.75f)
     * 7：代表桶数量（threshold），最后并不是7，而是比7大的最近的2的幂次方
     * 0.75f：代表负载因子（loadFactor），用于确定扩容时机，当元素个数达到 threshold * loadFactor 时，触发扩容
     */
    @Test
    public void test2() {
        Map<Object, Object> map = new HashMap<>(7, 0.75f);
        System.out.println(tableSizefor(7));
    }


    private int tableSizefor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }
}
