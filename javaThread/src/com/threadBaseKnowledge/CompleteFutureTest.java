package com.threadBaseKnowledge;

import org.junit.Test;

import java.util.concurrent.*;
import org.junit.Assert;

/**
 * @author wang
 * @create 2022-2022-25-21:04
 */
public class CompleteFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService executors = Executors.newCachedThreadPool();
//        CompletableFuture completableFuture = new CompletableFuture();
//
//        CompletableFuture so = CompletableFuture.supplyAsync(() -> {
//            System.out.println("this is the first");
//            return "laoliu";
//        }).thenApply((str) -> {
//            System.out.println("thsi is the second");
//            return str;
//        });
//
//        System.out.println(so.get());
//
//        CompletableFuture future = CompletableFuture.supplyAsync(()->{
//            System.out.println("wanglaowu");
//            return "123123123";
//        });
//
//        completableFuture.thenApply(name->{
//            return "wanglaowu";
//        }).thenApply((str)->{
//            System.out.println(str);
//            return "lisi";
//        });
//
//        ForkJoinPool pool = new ForkJoinPool();
//
//
//
////        System.out.println(completableFuture.get());
//        System.out.println(future.get());


//        CompletableFuture<String> messge = CompletableFuture.completedFuture("messge");
//
//        System.out.println("the message is done?+ "+messge.isDone());
//
//        System.out.println("value "+messge.getNow(null));
        ExecutorService excutors = Executors.newFixedThreadPool(3);
        CompletableFuture<String> flow = CompletableFuture.runAsync(() -> {
            try{ Thread.sleep(100);} catch(InterruptedException e){ e.printStackTrace();}
            System.out.print("this is the first");
            System.out.println(" you should wash vegetables");
        },excutors).thenApply((str) -> {
            System.out.print("thsi si the second!");
            System.out.println("you should put the vegetables to the table");
            return str;
        }).thenApply((str) -> {
            System.out.print("this is the third ");
            System.out.println("you should dish up oil fired and put the vegetables to the pot");
            int i = 1/0;
            return "done";
        }).thenApply((str) -> {
            System.out.println("then you can eat!!!!");
            return "done";
        });


        flow.whenComplete((t,r)->{
            System.out.println("achieve");
        });
        flow.thenRun(()->{
            System.out.println("66666666666666666666666666");

        }).thenRunAsync(()->{
            System.out.println("888888888888888888888888888");
        });
        flow.thenRunAsync(()->{
            System.out.println("9999999999999999999999999999999");
        });
        flow.exceptionally((t)->{
//            t.printStackTrace();
            System.out.println("error");
            return "wanglaowu";

        });

        CompletableFuture<Void> wahahha = CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("wahahha");
            }
        },excutors);

        excutors.shutdown();
//        wahahha.get();

        System.out.println("************************************");
//        flow.get();

        CompletableFuture.allOf(flow,wahahha);
        System.out.println("---------------end------------------------------");

    }
}
