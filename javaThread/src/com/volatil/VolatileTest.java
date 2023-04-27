package com.volatil;

/**
 * @author wang
 * @create 2023-2023-26-17:58
 */
public class VolatileTest {
    private  boolean stop;

    volatile  long num = 0;
    public static void main(String[] args) {
        VolatileTest volatileTest = new VolatileTest();
//        new Thread(()->{
//            while(!volatileTest.stop){
//                System.out.println();
//            }
//            System.out.println(Thread.currentThread().getName()+" is stop");
//        },"t1").start();

        for(int i = 0;i<1000;i++){
            new Thread(()->{
                try{ Thread.sleep(10);} catch(InterruptedException e){ e.printStackTrace();}
                volatileTest.add();
            },"t1").start();
        }
        try{ Thread.sleep(2000);} catch(InterruptedException e){ e.printStackTrace();}
        System.out.println("main thread stop");
        volatileTest.stop = true;
        try{ Thread.sleep(1000);} catch(InterruptedException e){ e.printStackTrace();}
        System.out.println(volatileTest.num);
    }

    public void add(){
        num++;
    }
}
