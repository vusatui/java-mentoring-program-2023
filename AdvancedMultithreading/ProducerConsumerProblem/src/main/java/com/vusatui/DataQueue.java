package com.vusatui;

import java.util.LinkedList;
import java.util.Queue;

public class DataQueue {
    private final Queue<MessageDTO> queue = new LinkedList<>();
    private final int maxSize;
    private final Object FULL_QUEUE = new Object();
    private final Object EMPTY_QUEUE = new Object();
    public boolean runFlag = true;

    public DataQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public Boolean isFull() {
        return queue.size() == maxSize;
    }

    public Boolean isEmpty() {
        return queue.isEmpty();
    }

    public void waitOnFull() throws InterruptedException {
        synchronized (FULL_QUEUE) {
            FULL_QUEUE.wait();
        }
    }

    public void notifyAllForFull() {
        synchronized (FULL_QUEUE) {
            FULL_QUEUE.notifyAll();
        }
    }

    public void waitOnEmpty() throws InterruptedException {
        synchronized (EMPTY_QUEUE) {
            EMPTY_QUEUE.wait();
        }
    }

    public void notifyAllForEmpty() {
        synchronized (EMPTY_QUEUE) {
            EMPTY_QUEUE.notify();
        }
    }

    public void add(MessageDTO message) {
        synchronized (queue) {
            queue.add(message);
        }
    }

    public MessageDTO remove() {
        synchronized (queue) {
            return queue.poll();
        }
    }
}
