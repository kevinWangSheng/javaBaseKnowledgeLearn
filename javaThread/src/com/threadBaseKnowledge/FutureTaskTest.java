package com.threadBaseKnowledge;

import org.junit.Test;

import javax.swing.plaf.SliderUI;
import java.util.concurrent.*;


/**
 * @author wang
 * @create 2022-2022-25-19:18
 */
public class FutureTaskTest {
    public static void main(String[] args) {
        FutureTask task = new FutureTask(()->{
            int sum = 0;
            System.out.println("wahahahhahahhaha................");
            try{ Thread.sleep(2000);} catch(InterruptedException e){ e.printStackTrace();}
            System.out.println(".............................");
            sum=(1+100)*100/2;
            return sum;
        });
        FutureTask<String> stringFutureTask = new FutureTask<String>(()->{
            System.out.println("this is the second time");
            try{ Thread.sleep(100);} catch(InterruptedException e){ e.printStackTrace();}
            System.out.println("wahhaa");
            return "wanglaowu";
        });

        ExecutorService executorService = Executors.newFixedThreadPool(2);

//        executorService.execute(task);
//        executorService.execute(stringFutureTask);
        executorService.submit(task);
        executorService.submit(stringFutureTask);

        System.out.println("start to get");
        try {


            Integer result = (Integer) task.get();
            System.out.println("the task return is "+result);
            System.out.println("the string futureTask's get is "+stringFutureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            for(int i = 0;i<10;i++){
                System.out.println("wanglaowu"+Thread.currentThread().getName());
            }
        },"t1").start();



        System.out.println("main end...............");
    }

}
