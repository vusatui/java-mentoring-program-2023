package com.vusatui;

import java.math.BigInteger;
import java.util.Collections;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<BigInteger> {

    private final int value;

    public FactorialTask(int value) {
        this.value = value;
    }

    protected BigInteger compute() {
        if (value <= 1) return BigInteger.ONE;
        FactorialTask ft = new FactorialTask(value - 1);
        ft.fork();
        return BigInteger.valueOf(value).multiply(ft.join());
    }
}
