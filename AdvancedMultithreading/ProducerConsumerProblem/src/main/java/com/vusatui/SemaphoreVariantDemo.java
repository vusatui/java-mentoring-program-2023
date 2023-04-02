package com.vusatui;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class SemaphoreVariantDemo {

    private static final int LIMIT = 10;

    private static final Random random = new Random();

    private static final Semaphore semaphore = new Semaphore(1, true);

    private static final Queue<Integer> list = new LinkedList<>();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            Thread producerThread = new Thread(SemaphoreVariantDemo::produce);
            producerThread.start();
        }

        for (int i = 0; i < 3; i++) {
            Thread consumerThread = new Thread(SemaphoreVariantDemo::consume);
            consumerThread.start();
        }

        Thread.sleep(2000);
        System.out.println();
        System.exit(0);
    }

    private static Boolean isFull() {
        return list.size() == LIMIT;
    }

    private static Boolean isEmpty() {
        return list.isEmpty();
    }

    private static void produce() {
        while (true) {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (!isFull()) {
                Integer value = random.nextInt();
                list.add(value);
                System.out.printf("[%s] Value produced: %s\n", Thread.currentThread().getName(), value);
            }
            semaphore.release();
        }
    }

    private static void consume() {
        while (true) {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (!isEmpty()) {
                Integer value = list.poll();
                // Consume value
                System.out.printf("[%s] Value consumed: %s\n", Thread.currentThread().getName(), value);
            }
            semaphore.release();
        }
    }
}
