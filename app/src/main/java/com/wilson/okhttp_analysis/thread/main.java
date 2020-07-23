package com.wilson.okhttp_analysis.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class main {
    public static void main(String[] args) {


//        ExecutorService executorService = Executors.newCachedThreadPool();
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        ExecutorService executorService = Executors.newSingleThreadExecutor();

//        for (int i=0;i<5;i++){
//          executorService.execute(new MyThread());
//        }
//        executorService.shutdown();


        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.execute(new MyThread("1"));
        scheduledExecutorService.execute(new MyThread("2"));
        scheduledExecutorService.execute(new MyThread("3"));

        scheduledExecutorService.schedule(new MyThread("4"), 5000, TimeUnit.MICROSECONDS);

        scheduledExecutorService.shutdown();

    }
}


//创建一个线程
class MyThread implements Runnable {


    private String name;

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("---执行线程-" + name + Thread.currentThread().getName());

    }
}