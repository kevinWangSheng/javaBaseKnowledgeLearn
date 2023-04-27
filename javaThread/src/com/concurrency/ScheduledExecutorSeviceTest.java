package com.concurrency;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wang
 * @create 2023-2023-27-17:43
 */
public class ScheduledExecutorSeviceTest {
    private static final ScheduledExecutorService cancleService = new ScheduledThreadPoolExecutor(5);

    public static void timeRun(Runnable r, long timeout, TimeUnit unit){
        final Thread threadTask = new Thread(r);
        threadTask.start();
        cancleService.schedule(()->{
            System.out.println(Thread.currentThread().getName()+"interrupt!");
            threadTask.interrupt();
        }, timeout,unit );
        r.run();
    }

    public static void main(String[] args) {
        timeRun(()->{
            System.out.println(Thread.currentThread().getName()+"this is the task");
        },5,TimeUnit.SECONDS);
        System.out.println("run end");
        cancleService.shutdown();
    }
}
