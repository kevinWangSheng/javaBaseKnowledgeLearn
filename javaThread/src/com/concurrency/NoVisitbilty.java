package com.concurrency;

/**
 * @author wang
 * @create 2023-2023-20-15:39
 */
public class NoVisitbilty {
    private static boolean ready = false;
    private static int num = 10;

    public static void main(String[] args) {
        new Thread(()->{
            System.out.println(111);
            while(!ready){
                System.out.println(num);
                Thread.yield();
                System.out.println(num);
            }
        },"t1").start();
        try{ Thread.sleep(100);} catch(InterruptedException e){ e.printStackTrace();}
        num = 32;
        ready = true;
    }
}
