package com.vusatui;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;

public class Main {

    private static final int VALUE = 1200;

    public static void main(String[] args) {
        long start = System.nanoTime();
        BigInteger factorial = new ForkJoinPool().invoke(new FactorialTask(VALUE));
        System.out.printf("ForkJoin variant time %s %n", System.nanoTime() - start);
        System.out.printf("Factorial of %s is %s %n", VALUE, factorial);

        start = System.nanoTime();
        System.out.printf("ForkJoin variant time %s %n", System.nanoTime() - start);
        System.out.printf("Linear variant of %s is %s %n", VALUE, factorial(VALUE));
    }

    private static BigInteger factorial(int value) {
        int nextValue = value - 1;
        if (nextValue == 0) {
            return BigInteger.valueOf(value);
        } else {
            return BigInteger.valueOf(value).multiply(factorial(nextValue));
        }
    }
}