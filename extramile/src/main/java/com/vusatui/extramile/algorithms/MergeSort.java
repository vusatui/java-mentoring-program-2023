package com.vusatui.extramile.algorithms;

public class MergeSort {

        public static int[] sort(int[] arr) {
            if (arr.length > 1) {
                int arrLength = arr.length - 1;
                int mid = arrLength / 2;

                int[] leftArr = new int[mid + 1];
                int[] rightArr = new int[arrLength - mid];

                for (int p = 0; p < mid + 1; ++p)
                    leftArr[p] = arr[p];
                for (int x = 0; x < arrLength - mid; ++x)
                    rightArr[x] = arr[mid + 1 + x];

                sort(leftArr);
                sort(rightArr);

                int i = 0;
                int j = 0;
                int k = 0;

                while (i < leftArr.length && j < rightArr.length) {
                    if (leftArr[i] < rightArr[j]) {
                        arr[k] = leftArr[i];
                        i++;
                    } else {
                        arr[k] = rightArr[j];
                        j++;
                    }
                    k++;
                }

                while(i < leftArr.length) {
                    arr[k] = leftArr[i];
                    i++;
                    k++;
                }

                while(j < rightArr.length) {
                    arr[k] = rightArr[j];
                    j++;
                    k++;
                }

            }

            return arr;
        }
}
