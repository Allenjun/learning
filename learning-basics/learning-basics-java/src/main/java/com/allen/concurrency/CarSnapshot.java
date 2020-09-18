package com.allen.concurrency;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarSnapshot {

    /**
     * 需求：获取车辆位置列表的快照信息
     * 1. Collections.unmodifiableList规范不能使用list.set()等修改方法
     * 2. MyPoint的变量设置成final，代表不可以获取MyPoint，然后修改位置
     */
    @Test
    public void test1() {
        ArrayList<MyPoint> locations = new ArrayList<>();
        Collections.addAll(locations, new MyPoint(1, 2), new MyPoint(5, 7), new MyPoint(8, 5));
        List<MyPoint> snapshort = Collections.unmodifiableList(locations);
        // 如果想修改快照，报错
        snapshort.set(1, new MyPoint(3, 3));
    }

    private static class MyPoint {
        private final int x;
        private final int y;

        public MyPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }


}
