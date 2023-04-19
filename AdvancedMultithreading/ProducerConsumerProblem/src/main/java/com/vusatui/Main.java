package com.vusatui;

public class Main {

    private static final int MAX_QUEUE_CAPACITY = 10;

    public static void main(String[] args) {
        DataQueue dataQueue = new DataQueue(MAX_QUEUE_CAPACITY);
//        Producer producer = new Producer(dataQueue);
//        Thread producerThread = new Thread(producer);
//        Consumer consumer = new Consumer(dataQueue);
//        Thread consumerThread = new Thread(consumer);
//        producerThread.start();
//        consumerThread.start();
//        Producer producer = new Producer(dataQueue);
//        for(int i = 0; i < 10; i++) {
//            Thread producerThread = new Thread(producer);
//            producerThread.start();
//        }
//
//        Consumer consumer = new Consumer(dataQueue);
//        for(int i = 0; i < 2; i++) {
//            Thread consumerThread = new Thread(consumer);
//            consumerThread.start();
//        }
//        while (true) {
//            System.out.println(dataQueue.runFlag ? "Running" : "Stopped");
//        }
//        producer.stop();
//        consumer.stop();
    }
}