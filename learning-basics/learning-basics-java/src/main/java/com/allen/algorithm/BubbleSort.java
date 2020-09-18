package com.allen.algorithm;

import java.util.Arrays;

/**
 * @author JUN @Description 冒泡排序 思想：比较相邻元素的大小，把大的数字放右边，依次循环后，最大的就冒泡到最右面，如此循环。 算法复杂度为O²
 * @createTime 11:16
 */
public class BubbleSort extends Sort {

    public static void main(String[] args) {
        int[] nums = {9, 5, 2, 7};
        //        int[] nums = { 9 };
        new BubbleSort().sort(nums);
        System.out.println(Arrays.toString(nums));
    }

    @Override
    public void sort(int[] nums) {
        for (int i = nums.length; i > 1; i--) {
            for (int j = 0; j < i - 1; j++) {
                if (less(nums[j + 1], nums[j])) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }
}
