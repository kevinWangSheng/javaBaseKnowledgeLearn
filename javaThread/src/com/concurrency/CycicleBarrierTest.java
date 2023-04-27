package com.concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author wang
 * @create 2023-2023-23-18:02
 */
public class CycicleBarrierTest {
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(3,()->{
        System.out.println("all the thread is out of the await");
    });

    public static void main(String[] args) {
        CycicleBarrierTest cycicleBarrierTest = new CycicleBarrierTest();

        for(int i = 0;i<cycicleBarrierTest.cyclicBarrier.getParties();i++){
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"start !!!!!");
                    cycicleBarrierTest.cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName()+"end!!!!!!!!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },"t1"+i+1).start();
        }

    }




}
