package com.wilson.okhttp_analysis;


import java.util.ArrayDeque;
import java.util.LinkedList;

/***
 * 双端队列
 *  具有栈和队列性质的类
 *
 *  使用ArrayDeque的原因是效率高
 *   当做为栈使用比Stack 更快
 *   当做为队列的使用的时候比LinkedList 快
 *
 *   ArrayDeque  底层是循环数组  查找比较看
 *   LinkedList  底层是链表     增删比较快查询慢
 * https://blog.csdn.net/aishang5wpj/article/details/8797854
 */

public class ArrayDequeDemo {



    public static  void main(String[] args){


        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        ArrayDeque<Integer> aDeque = new ArrayDeque<>();
        for (int i = 0; i <arr.length; i++) {
            aDeque.addFirst(arr[i]);
        }


        while (!aDeque.isEmpty()){
            System.out.println(aDeque.getLast());
            aDeque.removeLast();
        }


    }
}
