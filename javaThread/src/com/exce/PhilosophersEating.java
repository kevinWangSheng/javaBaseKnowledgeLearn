package com.exce;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wang
 * @create 2022-2022-27-9:03
 */
public class PhilosophersEating {
    public static void main(String[] args) {
        DiningOfPhilosophers.initChopsitck();

        Thread[] philosopher = new Thread[6];

        for(int i = 0;i<philosopher.length;i++)
        {
            final int position = i;
            philosopher[i] = new Thread(()->{
                for(int j= 0;j<3;j++){
                    try{ Thread.sleep(1000);} catch(InterruptedException e){ e.printStackTrace();}
                    DiningOfPhilosophers.tryEat(position);
                    System.out.println("**************************************");
                }
            },"t"+i);
            philosopher[i].start();
        }


    }

}
