package com.allen.algorithm;

import java.util.Arrays;

/**
 * @author JUN @Description 插入排序 思想： 算法复杂度为最好的情况是O；最坏的情况是O²（倒序）
 * @createTime 11:16
 */
public class InsertSort extends Sort {

    public static void main(String[] args) {
        int[] nums = {9, 5, 2, 7};
        //        int[] nums = { 9 };
        new InsertSort().sort(nums);
        System.out.println(Arrays.toString(nums));
    }

    @Override
    public void sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(nums[j], nums[j - 1])) {
                    swap(nums, j, j - 1);
                }
            }
        }
    }
}
