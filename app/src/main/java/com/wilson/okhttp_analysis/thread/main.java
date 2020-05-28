package com.wilson.okhttp_analysis.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class main {
    public static void main(String [] args){


//        ExecutorService executorService = Executors.newCachedThreadPool();
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        ExecutorService executorService = Executors.newSingleThreadExecutor();

//        for (int i=0;i<5;i++){
//          executorService.execute(new MyThread());
//        }
//        executorService.shutdown();


        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        MyThread myThread1 = new MyThread();
        scheduledExecutorService.execute(myThread1);
        scheduledExecutorService.execute(myThread1);
        scheduledExecutorService.execute(myThread1);
        MyThread myThread = new MyThread();
        scheduledExecutorService.schedule(myThread,5000, TimeUnit.MICROSECONDS);
        scheduledExecutorService.shutdown();

    }
}



//创建一个线程
class MyThread implements   Runnable{
    @Override
    public void run() {
        System.out.println("---执行线程-"+Thread.currentThread().getName());

    }
}