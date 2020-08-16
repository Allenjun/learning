package com.allen.algorithm;

import java.util.Arrays;

/**
 * @author JUN
 * @Description 选择排序
 *              思想：比较全部数字，取最小的一个放在第一位；取剩下的数字比较，放在第二位，如此循环。
 *              算法复杂度为O²
 * @createTime 11:16
 */
public class SelectionSort extends Sort {
    
    public static void main(String[] args) {
        int[] nums = { 9, 5, 2, 7 };
        new SelectionSort().sort(nums);
        System.out.println(Arrays.toString(nums));
    }
    
    @Override
    public void sort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int min = i;
            for (int j = i; j < nums.length; j++) {
                if (less(nums[j], nums[min])) {
                    min = j;
                }
            }
            swap(nums, i, min);
        }
    }
    
}
