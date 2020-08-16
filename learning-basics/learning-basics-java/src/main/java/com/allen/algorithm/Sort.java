package com.allen.algorithm;

/**
 * @author JUN
 * @Description TODO
 * @createTime 11:20
 */
public abstract class Sort {
    
    public abstract void sort(int[] nums);
    
    protected boolean less(int v, int w) {
        return v - w < 0;
    }
    
    protected void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
