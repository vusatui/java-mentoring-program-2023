package com.vusatui.extramile.algorithms;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class BinarySearch {

    public static <V> Optional<V> search(List<V> list, V desired) {
        Optional<V> result = Optional.empty();

        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            V leftValue = list.get(left);
            if (leftValue == desired) {
                result = Optional.of(leftValue);
                break;
            }

            V rightValue = list.get(right);
            if (rightValue == desired) {
                result = Optional.of(rightValue);
                break;
            }

            left++;
            right--;
        }

        return result;
    }
}
