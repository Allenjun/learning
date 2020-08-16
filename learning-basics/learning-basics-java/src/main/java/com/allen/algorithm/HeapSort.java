package com.allen.algorithm;

import java.util.Arrays;

/**
 * @author JUN
 * @Description 堆排序
 *              思想：进
 *              算法复杂度为：
 * @createTime 11:16
 */
public class HeapSort extends Sort {
    
    public static void main(String[] args) {
        int[] nums = { 9, 5, 2, 7, 4, 9, 8, 2, 8 };
//        int[] nums = { 9 };
        new HeapSort().sort(nums);
        System.out.println(Arrays.toString(nums));
    }
    
    @Override
    public void sort(int[] nums) {
        // 构造最大堆
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            percolateDown(nums, i, nums.length);
        }
        // 不断删除堆顶，并把被删除的只放到堆尾，直到堆被全部删除，得到一个递增序列
        for (int i = nums.length - 1; i > 0; i--) {
            swap(nums, 0, i);
            percolateDown(nums, 0, i);
        }
    }
    
    private void percolateDown(int[] nums, int i, int length) {
        int tmp = nums[i];
        for (int child = i * 2 + 1; child < length; i = child, child = i * 2 + 1) {
            if (child + 1 < length && nums[child + 1] > nums[child]) {
                child++;
            }
            if (nums[child] > tmp) {
                nums[i] = nums[child];
            } else {
                break;
            }
        }
        nums[i] = tmp;
    }
    
}
