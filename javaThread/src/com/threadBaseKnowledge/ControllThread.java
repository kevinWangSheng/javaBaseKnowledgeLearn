package com.threadBaseKnowledge;

import java.util.Random;
import java.util.Scanner;

/**
 * @author wang
 * @create 2022-2022-23-21:33
 */
public class ControllThread {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.start();

        new Thread(()->{
            Scanner scanner = new Scanner(System.in);
            while(true){
                synchronized (thread) {
                    String value = scanner.next();
                    if ("Q".equalsIgnoreCase(value)) {
                        try {
                            thread.interrupt();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                        thread.interrupt();
                        break;
                    }
                }
            }
            System.out.println("ending.....");
        },"t1").start();
    }
}

class MyRunnable implements Runnable{
    public void printRandom(){

    }


    @Override
    public void run() {
        while(true){
            System.out.println(Math.random());
            try{ Thread.sleep(1000);} catch(InterruptedException e){ e.printStackTrace();break;}
            if(Thread.currentThread().isInterrupted())
                break;
        }
        System.out.println("ending...");
    }
}
