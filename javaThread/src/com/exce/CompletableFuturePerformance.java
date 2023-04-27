package com.exce;

import com.threadBaseKnowledge.SmallTool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author wang
 * @create 2022-2022-27-12:45
 */
public class CompletableFuturePerformance {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        long start = System.currentTimeMillis();

        ArrayList<CompletableFuture> clist = new ArrayList<>();

        for(int i = 0;i<10;i++){
            final int number = i;
            clist.add(CompletableFuture.supplyAsync(()->{
               new Dish().make("vegetable"+number,1000);
               return "vegtable"+number;
            },executor));
        }

        CompletableFuture.allOf(clist.toArray(new CompletableFuture[clist.size()])).join();

        System.out.println();

        long endTime = System.currentTimeMillis();

        System.out.println("the total time is "+(endTime-start));

        Future<?> submit = executor.submit(() -> {
            System.out.println();
//            return "wanglaowu";
            try{ Thread.sleep(2000);} catch(InterruptedException e){ e.printStackTrace();}

        },"laoliu");

        ExecutorCompletionService service = new ExecutorCompletionService(executor);

        service.submit(()->{
            return "nimabi";
        });

        System.out.println(submit.get());
        System.out.println(service.take().get());


        executor.shutdown();


    }
}
