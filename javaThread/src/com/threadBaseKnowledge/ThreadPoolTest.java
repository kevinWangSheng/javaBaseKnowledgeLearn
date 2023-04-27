package com.threadBaseKnowledge;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wang
 * @create 2022-2022-25-16:38
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3,6,7l, TimeUnit.SECONDS,
                                                                new LinkedBlockingQueue<>(),new ThreadPoolExecutor.DiscardPolicy());

        for(int i = 0;i<5;i++ ){
            executor.execute(()->{
                System.out.println(Thread.currentThread().getName()+" yyyyyyyy");
            });
        }

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                while(true){
                    System.out.println("this is the yyyy");
                }
            }
        };
        executor.execute(r2);

//        try{ Thread.sleep(1000);} catch(InterruptedException e){ e.printStackTrace();};
        executor.remove(r2);

        executor.shutdown();

    }
}
