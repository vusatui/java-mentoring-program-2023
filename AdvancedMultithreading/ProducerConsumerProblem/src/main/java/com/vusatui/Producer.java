package com.vusatui;

import java.util.Random;


public class Producer implements Runnable {
    private final DataQueue dataQueue;

    private final Random random = new Random();

    public Producer(DataQueue dataQueue) {
        this.dataQueue = dataQueue;
    }


    @Override
    public void run() {
        produce();
    }

    public void produce() {
        while (dataQueue.runFlag) {
            MessageDTO message = generateMessage();
            while (dataQueue.isFull() && dataQueue.runFlag) {
                try {
                    dataQueue.waitOnFull();
                } catch (InterruptedException e) {
                    break;
                }
            }
            if (!dataQueue.runFlag) {
                break;
            }
            dataQueue.add(message);
            dataQueue.notifyAllForEmpty();
        }
    }

    public void stop() {
        dataQueue.runFlag = false;
        dataQueue.notifyAllForFull();
    }

    private MessageDTO generateMessage() {
        System.out.println("Created a message!");
        return new MessageDTO(random.nextInt(), random.nextInt());
    }
}
