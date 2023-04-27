package com.Class_.thread;

/**
 * @author wang
 * @create 2022-2022-17-13:24
 */
public class startThread {
    public static void main(String[] args) {
        Thread myThread = new Thread(()->{
            while(true){
                if(Thread.currentThread().isInterrupted())
                    break;
                System.out.println("我是一个线程，线程id为:2020122013");
            }
            System.out.println("my thread is interrupt");
        });

        myThread.start();

        try{ Thread.sleep(100);} catch(InterruptedException e){ e.printStackTrace();}
        myThread.interrupt();

    }
}
