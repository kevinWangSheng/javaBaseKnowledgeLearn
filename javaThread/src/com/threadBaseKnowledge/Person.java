package com.threadBaseKnowledge;

/**
 * @author wang
 * @create 2022-2022-23-15:59
 */
public class Person implements Runnable {

    @Override
    public void run() {
        System.out.println("this is the person");
        try{ Thread.sleep(1000);} catch(InterruptedException e){ e.printStackTrace();}
        System.out.println("person end...");
    }
}
