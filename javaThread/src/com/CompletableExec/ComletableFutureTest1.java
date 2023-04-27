package com.CompletableExec;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * @author wang
 * @create 2022-2022-26-0:02
 */
public class ComletableFutureTest1 {
    @Test
    public void test1(){
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " cal.....");
            return (1 + 100) * 100 / 2;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName()+" asking the people");
            return "ask over!";
        }), (sum, result) -> {
            return sum + result;
        });

        System.out.println("waiting....");
        System.out.println(Thread.currentThread().getName()+" "+cf.join());
    }
}
