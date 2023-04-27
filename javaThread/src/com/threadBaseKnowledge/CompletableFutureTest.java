package com.threadBaseKnowledge;

import org.junit.Test;

import java.util.StringJoiner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.threadBaseKnowledge.SmallTool.printTimeAndThread;


/**
 * @author wang
 * @create 2022-2022-25-23:20
 */
public class CompletableFutureTest {
    @Test
    public void oneTaskTest(){
        ExecutorService executor = Executors.newFixedThreadPool(3);
        printTimeAndThread("Green Hand go to the restaurant");
        printTimeAndThread("Green Hand ordering");

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println("chef is cooking...");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printTimeAndThread("chef is buy meal in school canteen");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           printTimeAndThread("cooked");
            return "the scrambled egg with tomato";
        });

        printTimeAndThread("Green play king of glory");
        printTimeAndThread(String.format("%s and eating...",cf.join()));

    }

    @Test
    public void twoTask(){
        ExecutorService executor = Executors.newFixedThreadPool(3);
        printTimeAndThread("Green Hand go to the restaurant");
        printTimeAndThread("Green Hand ordering");

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("chef is cooking...");
            SmallTool.sleep(200);
            return "the scrambled egg with tomato";
        }).thenCompose(dish->CompletableFuture.supplyAsync(()->{
            printTimeAndThread("waiter is buy meal in school canteen");
            SmallTool.sleep(200);
            printTimeAndThread("cooked");
            return dish+"rice";
        }));

        printTimeAndThread("Green play king of glory");
        printTimeAndThread(String.format("%s and eating...",cf.join()));
    }

    @Test
    public void twoTaskAsync(){
        printTimeAndThread("Green Hand go to the restaurant");
        printTimeAndThread("Green Hand ordering");

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("chef is cooking...");
            SmallTool.sleep(200);
            return "the scrambled egg with tomato";
        }).thenCombine(CompletableFuture.supplyAsync(()->{
            printTimeAndThread("waiter is buy meal in school canteen");
            SmallTool.sleep(200);
//            printTimeAndThread("cooked");
            return "rice";
        }),(dish,rice)->{
            SmallTool.printTimeAndThread("waitter get the rice ..");
            return "waitter get the "+dish+" and "+rice;
        });

        printTimeAndThread("Green play king of glory");
        printTimeAndThread(String.format("%s and eating...",cf.join()));
    }

    @Test
    public void twoTaskWaitter(){
        printTimeAndThread("put the money");
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            printTimeAndThread("the waitter get the money and ready to get the invoice");
            try{ Thread.sleep(1000);} catch(InterruptedException e){ e.printStackTrace();}
            printTimeAndThread("lisi");
            return 300;
        }).thenApplyAsync(money -> {
            try{ Thread.sleep(100);} catch(InterruptedException e){ e.printStackTrace();}
            printTimeAndThread("the accountant get the money and put the invoice to another waitter");
//            try{ Thread.sleep(100);} catch(InterruptedException e){ e.printStackTrace();}
            printTimeAndThread("wa");

            return "300 invoice";
        });

        printTimeAndThread("Green hand play the king");
        printTimeAndThread("the last is "+cf.join());
    }

    @Test
    public void busTest(){
        printTimeAndThread("watiting for the car");
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            printTimeAndThread("this is the 700");
//            try{ Thread.sleep(100);} catch(InterruptedException e){ e.printStackTrace();}
            return "700";
        }).applyToEither(CompletableFuture.supplyAsync(()->{
            printTimeAndThread("this is the 800");
            return "800";
        }),firstBusCome->{
            if(firstBusCome.startsWith("7"))
            {
                printTimeAndThread("hit the tree");
                throw new RuntimeException("hit the tree");
            }
            return  firstBusCome;
        }).exceptionally((t)->{
            printTimeAndThread("get the taxi to home");
            return "taxi";
        });



        printTimeAndThread("get the "+cf1.join());
    }

    @Test
    public void playPhone(){
        printTimeAndThread("watiting for the calling");
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            printTimeAndThread("accept");
            try{ Thread.sleep(1100);} catch(InterruptedException e){ e.printStackTrace();}
            return "accept";
        }).applyToEither(CompletableFuture.supplyAsync(()->{
            try{ Thread.sleep(1000);} catch(InterruptedException e){ e.printStackTrace();}
            printTimeAndThread("this refence");
            return "refence";
        }),isCall->{
            return  isCall;
        });



        printTimeAndThread("get the "+cf1.join());
    }


}
