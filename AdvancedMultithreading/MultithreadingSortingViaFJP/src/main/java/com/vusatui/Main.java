package com.vusatui;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[] {1,3,5,8,1,3,9,10,11,45245,231,521,12};
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        forkJoinPool.invoke(new MergeSortAction(arr));
        System.out.printf("Sorted arr: %s", Arrays.toString(arr));
    }
}