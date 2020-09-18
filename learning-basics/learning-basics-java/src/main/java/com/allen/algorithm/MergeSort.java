package com.allen.algorithm;

import java.util.Arrays;

/**
 * @author JUN @Description 归并排序 思想： 算法复杂度为
 * @createTime 11:16
 */
public class MergeSort extends Sort {

    public static void main(String[] args) {
        //        int[] nums = { 9, 5, 2, 7, 4, 9, 8, 2, 8 };
        int[] nums = {9};
        new MergeSort().sort(nums);
        System.out.println(Arrays.toString(nums));
    }

    @Override
    public void sort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    private void sort(int[] nums, int left, int right) {
        if (left < right) {
            int mid = (right + left) / 2;
            sort(nums, left, mid);
            sort(nums, mid + 1, right);
            merge(nums, left, mid, right);
        }
    }

    private void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[nums.length];
        int cursor = left;
        int lcursor = left;
        int rcursor = mid + 1;
        while (lcursor <= mid && rcursor <= right) {
            if (nums[lcursor] <= nums[rcursor]) {
                temp[cursor] = nums[lcursor];
                lcursor++;
            } else {
                temp[cursor] = nums[rcursor];
                rcursor++;
            }
            cursor++;
        }

        while (lcursor <= mid) {
            temp[cursor] = nums[lcursor];
            lcursor++;
            cursor++;
        }
        while (rcursor <= right) {
            temp[cursor] = nums[rcursor];
            rcursor++;
            cursor++;
        }

        cursor -= 1;
        while (cursor >= left) {
            nums[cursor] = temp[cursor];
            cursor--;
        }
    }
}
