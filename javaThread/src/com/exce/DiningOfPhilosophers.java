package com.exce;

import java.util.Queue;

/**
 * @author wang
 * @create 2022-2022-27-8:49
 */
public class DiningOfPhilosophers {
    private static final int CHOPSTICKS_NUMBER = 6;

    private volatile static Boolean[] chopsticks = new Boolean[CHOPSTICKS_NUMBER];


    public static void initChopsitck(){
        for(int i = 0;i<CHOPSTICKS_NUMBER;i++){
            chopsticks[i] = false;
        }
    }

    public static void tryEat(int position){
        if(!chopsticks[position])
        {
            System.out.println(Thread.currentThread().getName()+" get the left chopstock");
            chopsticks[position] = true;
        }
        else{
            System.out.println(Thread.currentThread().getName()+" try get left");
            try{ Thread.sleep(100);} catch(InterruptedException e){ e.printStackTrace();}
            if (!chopsticks[position]) {
                chopsticks[position] = true;
                System.out.println(Thread.currentThread().getName()+" put the left chopstick");
            }
            else{
                System.out.println(Thread.currentThread().getName()+ " abandon eating....");
                return;
            }
        }
        if(!chopsticks[(position+1)%CHOPSTICKS_NUMBER]){
            System.out.println(Thread.currentThread().getName()+" get the right chopstock");
            chopsticks[(position+1)%CHOPSTICKS_NUMBER] = true;

            System.out.println(Thread.currentThread().getName()+" \t|"+Thread.currentThread().getId()+"\t | is eating successful....");

            try{ Thread.sleep(2000);} catch(InterruptedException e){ e.printStackTrace();}
            chopsticks[position] = false;
            chopsticks[(position+1)%CHOPSTICKS_NUMBER] = false;
            System.out.println(Thread.currentThread().getName()+" is eating... over");

            return;
        }
        else{
            System.out.println(Thread.currentThread().getName()+" try to get the right chopstick....");
            try{ Thread.sleep(100);} catch(InterruptedException e){ e.printStackTrace();}
            if(!chopsticks[(position+1)%CHOPSTICKS_NUMBER]){
                System.out.println(Thread.currentThread().getName()+" get the right chopstick");
                chopsticks[(position+1)%CHOPSTICKS_NUMBER] = true;

                System.out.println(Thread.currentThread().getName()+" \t|"+Thread.currentThread().getId()+"\t | is eating.... successful*****");

                try{ Thread.sleep(2000);} catch(InterruptedException e){ e.printStackTrace();}
                chopsticks[position] = false;
                chopsticks[(position+1)%CHOPSTICKS_NUMBER] = false;
                System.out.println(Thread.currentThread().getName()+" is eating over....");

            }
            else{
                System.out.println(Thread.currentThread().getName()+"put the left chopsticks down");
                chopsticks[(position)%CHOPSTICKS_NUMBER] = false;
                System.out.println(Thread.currentThread().getName()+"abandon eating.....");
                return;
            }
        }

    }
}
