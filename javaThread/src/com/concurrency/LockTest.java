package com.concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author wang
 * @create 2023-2023-21-17:24
 */
public class LockTest<T>{
    private static List<Integer> list = Collections.synchronizedList(new ArrayList<>());

    private static List<Integer> list1;
    public static void main(String[] args) {
        new Thread(()->{
            synchronized (list1) {
                System.out.println("start");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list1.add(5);
                System.out.println(list1.size());
            }
        },"t1").start();
        try{ Thread.sleep(500);} catch(InterruptedException e){ e.printStackTrace();}
        System.out.println(list1.size());
        System.out.println("wangwu");
        list1.add(6);
        System.out.println(Arrays.toString(list1.toArray()));
    }
}
