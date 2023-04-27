package com.CompletableExec;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author wang
 * @create 2022-2022-28-18:02
 */
public class CompletableToEitherTest {
    @Test
    public void toNither() throws ExecutionException, InterruptedException {
        CompletableFuture<String> whoCallYou = CompletableFuture.supplyAsync(() -> {
            System.out.println("grandma call you ");
            try{ Thread.sleep(100);} catch(InterruptedException e){ e.printStackTrace();}
            return "grandma";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("grandFather call you");
            return "grandfather";
        }), (who -> {
            System.out.println(who);
            return who;
        }));

        System.out.println(whoCallYou.get());
        int value = 1;
        CompletableFuture.completedFuture(value).
                thenCombineAsync(CompletableFuture.supplyAsync(()->{
                    return "wanglaowu";
                }),(value1,value2)->{
                    return value+value2;
                }).thenAccept(res->{

        });

        try{ Thread.sleep(10000);} catch(InterruptedException e){ e.printStackTrace();}

    }
}
