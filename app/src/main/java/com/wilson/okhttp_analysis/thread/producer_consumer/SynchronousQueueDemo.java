package com.wilson.okhttp_analysis.thread.producer_consumer;

import java.util.concurrent.SynchronousQueue;

/**
 * SynchronousQueue
 * 经典的生产者消费者模式
 * 可以有多个生产者  可以并发生产消息 放入队列中 队列满了 生产者就会阻塞
 * 可以有多个消费者  可以并发获取消息  若是队列为空 消费者就会阻塞
 *
 *  SynchronousQueue 是一个没有数据缓冲的BlockingQueue   producer执行插入操作put 必须等到消费者移出操作take
 *  SynchronousQueue内部并没有数据缓存空间，你不能调用peek()方法来看队列中是否有数据元素，
 *  参考
 *  https://www.cnblogs.com/java-chen-hao/p/10238762.html
 */
public class SynchronousQueueDemo {


    public static void main(String[] args) throws InterruptedException {

        final SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<Integer>();



        //producer  生产者
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("put thread start");
//                try {
//                    synchronousQueue.put(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                System.out.println("pud thread end");
            }
        });
        Thread producer2 = new Thread(new Runnable() {
            @Override
            public void run() {
                 System.out.println("pud thread2  start");
//                try {
//                //    synchronousQueue.put(102);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println("pud thread2 end ");
            }
        });

        //消费者consumer
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("take thread start");
                try {
                    Integer take = synchronousQueue.take();
                    System.out.println("take  consumer " + take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("take thread end");

            }
        });

        Thread consumer2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("take2 thread start");
                Integer take = null;
                try {
                    take = synchronousQueue.take();
                    System.out.println("take2 consumer "+ take);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("take2 consumer end" );

            }
        });



        producer.start();
        producer2.start();
        Thread.sleep(1000);
        consumer.start();
        consumer2.start();

    }
}
