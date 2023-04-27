package com.threadBaseKnowledge;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * @author wang
 * @create 2022-2022-25-11:39
 */
public class ReentrantLockTest {
    public static void main(String[] args) throws IOException {
//        Sub s  = new Sub();
//
//        new Thread(()->{
//            s.methodChild();
//        },"t1").start();
//
//        new Thread(()->{
//            s.methodFather();
//        },"t2").start();
//        ProcessBuilder builder = new ProcessBuilder("cmd.exe","c","dir");
//
//        Process process = builder.start();
//
//        Scanner s = new Scanner(process.getInputStream());
//        String str;
//        try{ Thread.sleep(100000);} catch(InterruptedException e){ e.printStackTrace();}
//        while((str = s.nextLine())!=null){
//            System.out.println(str);
//        }
//        try{ Thread.sleep(100000);} catch(InterruptedException e){ e.printStackTrace();}
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,999,
                99l, TimeUnit.SECONDS,new LinkedBlockingQueue<>());
        Runnable runnable = ()->{
//            try {
            System.out.println(Thread.currentThread().getName()+" begin at "+System.currentTimeMillis());
//                Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+"end at "+System.currentTimeMillis());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        };
        for(int i = 0;i<4;i++){
            executor.execute(runnable);
        }
//        Thread.sleep(1000);
//        executor.shutdown();
        executor.shutdownNow();
        System.out.println("mian end");
        executor.execute(()->{
            System.out.println("hhahh");
        });
    }
}
class father{
    int i = 10;
    public synchronized  void methodFather(){
        System.out.println(Thread.currentThread().getName()+"father"+i--);
    }
}

class Sub extends father{
    public synchronized void methodChild(){
        while(i>0)
        {
            System.out.println(Thread.currentThread().getName()+"sub"+i--);
            super.methodFather();
        }
    }
}
