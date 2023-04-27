package com.concurrency;

import java.util.Random;

/**
 * @author wang
 * @create 2023-2023-19-21:35
 */
public class UnSafeSequence {
    private static final Father father = new Father();
    private static Object obj = new Object();
    private static int num = 0;
    public static void main(String[] args) {
//        int total = new Random().nextInt(100);
//
//        Thread[] threads = new Thread[2];
//
//        for(int i = 0;i<threads.length;i++){
//            threads[i] = new Thread(()->{
//                for(int j = 0;j<10;j++){
//                    addNumber();
//                }
//            });
//
//            threads[i].start();
//        }
//
//        addNumber();
//        System.out.println("*************"+num);
        Children children = new Children();

        children.doChildren();

        System.out.println();

    }

    public static void addNumber(){
        synchronized (obj) {
            num++;
        }
        System.out.println(Thread.currentThread().getName()+" "+num);
    }
}

class Father{
     int a;
    public Father(){
        System.out.println("father new");
    }
    public synchronized void doFather(){
        System.out.println("this is your father!");
    }
}

class Children extends Father{
    public synchronized void doChildren(){
        System.out.println("this is you son!");
        super.doFather();
    }
}
