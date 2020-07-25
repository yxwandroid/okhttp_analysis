package com.wilson.okhttp_analysis.thread.producer_consumer;

import java.util.Vector;

public class ProducerConsumer {
    public static void main(String[] args) {
        TaskQueue taskQueue = new TaskQueue();

        //创建四个线程进行消费任务
        for (int i = 0; i < 4; i++) {
            new Thread(new Consumer(taskQueue)).start();
        }

        //等待线程都启动完成
        sleep(2000);

        //开启创建任务的线程
        new Thread(new Producer(taskQueue)).start();

    }

    private static void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Producer implements Runnable {

    private TaskQueue taskQueue;

    public Producer(TaskQueue taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        while (true) {
            generateTask();
            sleep(2000);
        }
    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void generateTask() {
        int taskNum = (int) (Math.random() * 5 + 1);
        int timestamp = (int) System.currentTimeMillis();
//        for (int i = 0; i < taskNum; i++) {
        for (int i = 0; i < 4; i++) {
            String task = "Task-" + i;
            taskQueue.addTask(task);
        }

    }
}


class Consumer implements Runnable {

    private TaskQueue taskQueue;

    public Consumer(TaskQueue taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {

        execTask();

    }

    private void execTask() {
        while (true) {
            String task = taskQueue.removeTask();
            if (task != null) {
                System.out.println(task + " be done is " + Thread.currentThread().getName());
            }
        }
    }
}


class TaskQueue {
    private Vector<String> taskVector = new Vector<>();

    public synchronized void addTask(String task) {
        System.out.println(task + "  has  generate");
        taskVector.add(task);
        this.notify();
    }

    public synchronized String removeTask() {

        if (!taskVector.isEmpty()) {
            return taskVector.remove(0);
        } else {
            try {
                System.out.println(Thread.currentThread().getName() + " waiting ");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        return null;


    }
}