package com.wilson.okhttp_analysis.thread.producer_consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class BlockingQueueDemo {
    public static void main(final String[] args) throws InterruptedException {


        final BlockingQueue<Integer> arrQueue = new ArrayBlockingQueue<Integer>(100);
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    arrQueue.put(11);
                    Thread.sleep(1000);
                    arrQueue.put(22);
                    arrQueue.put(33);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread producer2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    arrQueue.put(1);
                    arrQueue.put(2);
                    Thread.sleep(1000);
                    arrQueue.put(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("consumer  start ");
                boolean empty = arrQueue.isEmpty();
                while (!arrQueue.isEmpty()) {
                    try {
                        Integer take = arrQueue.take();
                        System.out.println(take);
                        if (arrQueue.isEmpty())
                            break;

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("consumer  end ");
            }
        });

        producer.start();
        producer2.start();
        Thread.sleep(2200);
        consumer.start();

    }
}
