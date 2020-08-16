package com.allen.algorithm;

import java.util.Arrays;

/**
 * @author JUN
 * @Description 希尔排序
 *              思想：进阶版的插入排序，把一个数组以gap区间分成若干数组，执行插入排序，
 *                  再gap = gap/2,不断执行，
 *                  直到gap=1(也就是一个完整数组)执行最后一次插入排序。
 *              算法复杂度为：
 * @createTime 11:16
 */
public class ShellSort extends Sort {
    
    public static void main(String[] args) {
        int[] nums = { 9, 5, 2, 7, 4, 9, 8, 2, 8 };
//        int[] nums = { 9 };
        new ShellSort().sort(nums);
        System.out.println(Arrays.toString(nums));
    }
    
    @Override
    public void sort(int[] nums) {
        int gap = nums.length / 2;
        while (gap >= 1) {
            for (int i = gap; i < nums.length; i++) {
                int j = i - gap;
                while (j >= 0 && less(nums[i], nums[j])) {
                    swap(nums, i, j);
                    j -= gap;
                }
            }
            gap /= 2;
        }
    }
    
}
