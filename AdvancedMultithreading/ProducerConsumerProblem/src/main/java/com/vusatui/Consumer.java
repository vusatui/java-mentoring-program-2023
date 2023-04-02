package com.vusatui;

public class Consumer implements Runnable {
    private final DataQueue dataQueue;

    public Consumer(DataQueue dataQueue) {
        this.dataQueue = dataQueue;
    }

    @Override
    public void run() {
        consume();
    }

    public void consume() {
        while (dataQueue.runFlag) {
            MessageDTO message;
            if (dataQueue.isEmpty() && dataQueue.runFlag) {
                try {
                    dataQueue.waitOnEmpty();
                } catch (InterruptedException e) {
                    break;
                }
            }
            if (!dataQueue.runFlag) {
                break;
            }
            message = dataQueue.remove();
            dataQueue.notifyAllForFull();
            useMessage(message);
        }
    }

    public void stop() {
        dataQueue.runFlag = false;
        dataQueue.notifyAllForEmpty();
    }

    private void useMessage(MessageDTO message) {
        System.out.println("Using a message!");
    }
}
