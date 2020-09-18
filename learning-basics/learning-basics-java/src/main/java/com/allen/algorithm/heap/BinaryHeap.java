package com.allen.algorithm.heap;

import java.util.Arrays;

/**
 * @author JUN @Description TODO
 * @createTime 11:29
 */
public class BinaryHeap<K extends Comparable<K>> {

    int currentSize;
    K[] array;

    @SuppressWarnings("unchecked")
    public BinaryHeap(K[] items) {
        currentSize = items.length;
        array = (K[]) new Comparable[(currentSize + 2) * 11 / 10];
        System.arraycopy(items, 0, array, 1, items.length);
        buildHeap();
    }

    public static void main(String[] args) {
        BinaryHeap<Integer> binaryHeap = new BinaryHeap<>(new Integer[]{5, 4, 8, 6, 2, 10});

        binaryHeap.insert(7);

        Integer min = binaryHeap.deleteMin();
        System.out.println(min);

        binaryHeap.printHeap();
    }

    private void buildHeap() {
        for (int i = currentSize / 2; i > 0; i--) {
            percolateDown(i);
        }
    }

    private void percolateDown(int hole) {
        K tmp = array[hole];
        for (int child = hole * 2; child <= currentSize; hole = child, child = hole * 2) {
            if (child < currentSize && array[child].compareTo(array[child + 1]) > 0) {
                child++;
            }
            if (array[child].compareTo(tmp) < 0) {
                array[hole] = array[child];
            } else {
                break;
            }
        }
        array[hole] = tmp;
    }

    public void insert(K item) {
        if (currentSize == array.length - 1) {
            enlarge(array.length * 2 + 1);
        }
        int hole = ++currentSize;
        while (item.compareTo(array[hole / 2]) < 0) {
            array[hole] = array[hole / 2];
            hole /= 2;
        }
        array[hole] = item;
    }

    @SuppressWarnings("unchecked")
    private void enlarge(int i) {
        Comparable[] tmp = new Comparable[i];
        System.arraycopy(array, 0, tmp, 1, currentSize);
        array = (K[]) tmp;
    }

    public K deleteMin() {
        if (isEmpty()) {
            return null;
        }
        K min = array[1];
        array[1] = array[currentSize];
        array[currentSize--] = null;
        percolateDown(1);
        return min;
    }

    private boolean isEmpty() {
        return currentSize <= 0;
    }

    private void printHeap() {
        System.out.println(Arrays.toString(array));
    }
}
