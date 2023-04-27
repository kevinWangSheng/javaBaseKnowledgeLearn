package com.threadBaseKnowledge;

/**
 * @author wang
 * @create 2022-2022-23-15:12
 */
public class ThreadStartTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for(int i = 0;i<60;i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
                try{ Thread.sleep(1000);} catch(InterruptedException e){ e.printStackTrace();}
            }
        }, "t1");


        thread.start();

        for(int i = 0;i<50;i++){
            System.out.println(Thread.currentThread().getName()+" "+i);
            try{ Thread.sleep(1000);} catch(InterruptedException e){ e.printStackTrace();}
        }




    }
}
