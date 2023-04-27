package com.threadBaseKnowledge;

/**
 * @author wang
 * @create 2022-2022-23-20:13
 */
public class YieldTest {
    public static void main(String[] args) {
        new Thread(()->{
            for(int i = 0;i<10;i++){
                System.out.println("t1......"+i);
                try{ Thread.sleep(1000);} catch(InterruptedException e){ e.printStackTrace();}
                if(i==5){
                    try {
                        Thread.currentThread().join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    Thread.yield();
                }
            }
        },"t1").start();

        for(int i = 0;i<10;i++){
            System.out.println("main..."+i);
            if(i == 2)
//                Thread.yield();
            {
//                try {
//                    Thread.currentThread().join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
            try{ Thread.sleep(1000);} catch(InterruptedException e){ e.printStackTrace();}
        }
    }
}
