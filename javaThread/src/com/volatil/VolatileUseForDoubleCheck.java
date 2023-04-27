package com.volatil;

import java.util.PriorityQueue;

/**
 * @author wang
 * @create 2023-2023-26-19:41
 */
public class VolatileUseForDoubleCheck {
    private static VolatileLockTest volatileLockTest;

    public void newInstance(){
        if(volatileLockTest==null){
            synchronized (VolatileUseForDoubleCheck.class){
                if(volatileLockTest==null){
                    volatileLockTest = new VolatileLockTest();
                }
            }
        }


    }

    public static void main(String[] args) {
        Final aFinal = new Final();
        aFinal.a = 1;
        System.out.println(aFinal.a);

    }



}

final class Final{
     int a;

     public void seaA(){

     }
}