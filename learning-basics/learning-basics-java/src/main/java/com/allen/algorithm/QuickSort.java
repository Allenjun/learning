package com.allen.algorithm;

import java.util.Arrays;

/**
 * @author JUN
 * @Description 快速排序
 *              思想：
 *              算法复杂度为：NlogN
 * @createTime 11:16
 */
public class QuickSort extends Sort {
    
    public static void main(String[] args) {
        int[] nums = { 9, 5, 2, 7, 4, 9, 8, 2, 8 };
//        int[] nums = { 9 };
        new QuickSort().sort(nums);
        System.out.println(Arrays.toString(nums));
    }
    
    @Override
    public void sort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }
    
    private void sort(int[] nums, int low, int high) {
        if (low < high) {
            int cursor = quick(nums, low, high);
            sort(nums, low, cursor - 1);
            sort(nums, cursor + 1, high);
        }
    }
    
    private int quick(int[] nums, int low, int high) {
        int criterion = nums[low];
        while (low < high) {
            while (low < high && criterion <= nums[high]) {
                high--;
            }
            nums[low] = nums[high];
            while (low < high && criterion >= nums[low]) {
                low++;
            }
            nums[high] = nums[low];
        }
        nums[low] = criterion;
        return low;
    }
    
}
