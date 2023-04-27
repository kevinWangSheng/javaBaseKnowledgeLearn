package com.exce;

import com.threadBaseKnowledge.SmallTool;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author wang
 * @create 2022-2022-27-13:20
 */
public class BakeRollTest {
    @Test
    public void buyBakeRoll(){
        SmallTool.printTimeAndThread("start to do the bake Roll");
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("do the bake rooll.......");
            SmallTool.sleep(3000);
            return "ten bake roll";
        }).thenApply(bake -> {
            System.out.println("the customer get the " + bake);
            return "done";
        });

        SmallTool.printTimeAndThread(stringCompletableFuture.join());
    }
    @Test
    public void xiaomingBuyShaoBingTest() throws InterruptedException {
        final int count = 120;

        BlockingQueue<String> queue = new LinkedBlockingQueue<>(3);
//        List<String> queue = new ArrayList<>();
        List<String> xiaobaiMsg = new LinkedList<>();

        List<String> customerMsg = new LinkedList<>();
        List<String> chefMsg = new LinkedList<>();

        List<String> customerBMsg = new LinkedList<>();

        Thread xiaoming = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                String temp = String.format("%d shao bing", i + 1);
                try {
                    queue.put(temp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                queue.add(temp);
                try{ Thread.sleep(10);} catch(InterruptedException e){ e.printStackTrace();}
                xiaobaiMsg.add(String.format("xiao bai zai %d make %s shaobing remain shaobing %d",
                        System.currentTimeMillis(), temp,queue.size()));
            }

        }, "xiao ming");
        Thread chef = new Thread(() -> {
            for (int i = 5; i < 10; i++) {
                String temp = String.format("%d shao bing", i + 1);
                try {
                    queue.put(temp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                queue.add(temp);
                chefMsg.add(String.format("xiao bai zai %d make %s shaobing",
                        System.currentTimeMillis(), temp));
            }

        }, "chef");


        Thread customer_a = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                try {
                    String shaobing = queue.take();
//                    String shaobing = queue.get(queue.size() - 1);
                    customerMsg.add(Thread.currentThread().getName() + " buy " + shaobing);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, "Customer A");

//        Thread customer_b = new Thread(() -> {
//            for (int i = 5; i < 10; i++) {
//                try {
//                    String shaobing = queue.take();
//                    customerBMsg.add(Thread.currentThread().getName() + " buy " + shaobing);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, "Customer B");


        xiaoming.start();
//        chef.start();
        customer_a.start();
//        customer_b.start();

        try {
            xiaoming.join();
//
            customer_a.join();
//            chef.join();
//            customer_b.join();

        } catch (InterruptedException e) {
            SmallTool.printTimeAndThread("exception");
        }

//        try{ Thread.sleep(100);} catch(InterruptedException e){ e.printStackTrace();}

//        try{ Thread.sleep(30);} catch(InterruptedException e){ e.printStackTrace();}

        List<String> xiaomingBuyMsg = xiaobaiMsg.subList(xiaobaiMsg.size() - 1, xiaobaiMsg.size());
        SmallTool.printTimeAndThread(xiaomingBuyMsg.stream().collect(Collectors.joining("\n")));
        System.out.println("----------------------------------------");
//        List<String> chefBuyMsg = xiaobaiMsg.subList(chefMsg.size() - 1, chefMsg.size());
//        SmallTool.printTimeAndThread(chefBuyMsg.stream().collect(Collectors.joining("\n")));
//
//        System.out.println("-----------------------------------------");

        List<String> customerAMsg = customerMsg.subList(customerMsg.size() - 5, customerMsg.size());

        SmallTool.printTimeAndThread(customerAMsg.stream().collect(Collectors.joining("\n")));

        System.out.println("------------------------------------------");
//        List<String> customerbMsg = customerBMsg.subList(customerBMsg.size() - 5, customerBMsg.size());
//
//        SmallTool.printTimeAndThread(customerbMsg.stream().collect(Collectors.joining("\n")));
        System.out.println(queue.size());


//        SmallTool.printTimeAndThread(xiaobaiMsg.get(xiaobaiMsg.size()-1));
//        SmallTool.printTimeAndThread(customerMsg.get(customerMsg.size()-1)+customerMsg.size());
    }

    @Test
    public void joinTest() throws InterruptedException {
        Thread thread = new Thread(() -> {
            for(int i = 0;i<10;i++) {
                System.out.println(Thread.currentThread().getName());
                try{ Thread.sleep(100);} catch(InterruptedException e){ e.printStackTrace();}
            }
        }, "t1");

        Thread thread1 = new Thread(() -> {
            for(int i = 0;i<10;i++) {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");

        thread.start();
        thread1.start();

        thread.join();
        System.out.println("end");
    }
    @Test
    public void shaobingCut_off(){
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(3);

        LinkedList<String> xiaomingMsg = new LinkedList<>();
        LinkedBlockingQueue<String> cumtomer_A_MSG = new LinkedBlockingQueue<>();

        Thread xiaoming = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try{ Thread.sleep(100);} catch(InterruptedException e){
                    SmallTool.printTimeAndThread("go home to play the king of glory");
                    break;}
                if (Thread.currentThread().isInterrupted())
                    return;
                String shaobing = String.format("making the %d shaobing", i + 1);
                try {
                    queue.put(shaobing);
                } catch (InterruptedException e) {
                    SmallTool.printTimeAndThread("go home to play king of glory");
                }

                xiaomingMsg.add(String.format("%d alread make %s", System.currentTimeMillis(), shaobing));
            }
        });

        Thread customer_a = new Thread(() -> {

            String shaobing = null;
//            try {
//                shaobing = queue.poll(3, TimeUnit.SECONDS);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            shaobing = queue.poll();
            if (shaobing == null) {
                cumtomer_A_MSG.add("A doesn't buy anyone shaobing");
            } else {
                cumtomer_A_MSG.add("A buy the " + shaobing);
            }

        });

        customer_a.start();
        try{ Thread.sleep(100);} catch(InterruptedException e){ e.printStackTrace();}
        xiaoming.start();

        try {
            xiaoming.join();
            customer_a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            SmallTool.printTimeAndThread("join error!......");
        }



        SmallTool.printTimeAndThread(xiaomingMsg.stream().collect(Collectors.joining("\n")));
        SmallTool.printTimeAndThread(cumtomer_A_MSG.stream().collect(Collectors.joining()));


    }

    @Test
    public void synchronQueueTest(){
        SynchronousQueue<String> synQueue = new SynchronousQueue();

        Thread xiaobai = new Thread(() -> {
            String shaobing = "make the first shaobing";
            SmallTool.printTimeAndThread(shaobing);
            try {
                synQueue.put(shaobing);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            shaobing = "make the second shaobing ";
            SmallTool.printTimeAndThread(shaobing);
            try {
                synQueue.put(shaobing);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "xiaobai");

        Thread customerA = new Thread(()->{
            String shaobing = synQueue.poll();
            if(shaobing == null){
                SmallTool.printTimeAndThread("wait a minute");
                shaobing = synQueue.poll();
                if(shaobing==null)
                    SmallTool.printTimeAndThread("fuck,i would never buy it again");
            }
            SmallTool.printTimeAndThread("get the "+shaobing);

            try {
                shaobing = synQueue.poll(2,TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(shaobing == null){
                SmallTool.printTimeAndThread("doesn't buy it");
            }
            else{
                SmallTool.printTimeAndThread("get the "+shaobing);
            }

        },"t1");

        customerA.start();
        try{ Thread.sleep(1000);} catch(InterruptedException e){ e.printStackTrace();}
        xiaobai.start();

        try {
            customerA.join();

            xiaobai.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end ing ......");



    }

    @Test
    public void priporityQueueTest(){
        PriorityBlockingQueue<String> pbq = new PriorityBlockingQueue<>(2,(str1,str2)->{
            int len1 = str1.length();
            int len2 = str2.length();

            for(int i = 0;i<len1||i<len2;i++){
                char char1 = str1.charAt(i);
                char char2 = str2.charAt(i);

                if(char1==char2)
                    continue;
                else if(char1>char2)
                    return 1;
                else
                    return -1;
            }
            if(len1!=len2)
            {
                if(len1>len2)
                    return 1;
                else
                    return -1;
            }
            return 0;
        });

        Thread xiaoming = new Thread(()->{

            String shaobing = "this is the first shaobing, is the flavor shaobing";
            pbq.put(shaobing);
            SmallTool.printTimeAndThread(shaobing);

            shaobing = "1 this is the second shaobing, is the flavor shaobing";
            pbq.put(shaobing);
            SmallTool.printTimeAndThread(shaobing);

        },"xiaoming");

        Thread customerA = new Thread(()->{
            String shaobing = pbq.poll();
            if(shaobing == null){
                SmallTool.printTimeAndThread("wait a minute");
                shaobing = pbq.poll();
                if(shaobing==null)
                    SmallTool.printTimeAndThread("fuck,i would never buy it again");
            }
            SmallTool.printTimeAndThread("get the "+shaobing);

            try{ Thread.sleep(1000);} catch(InterruptedException e){ e.printStackTrace();}

            try {
                shaobing = pbq.poll(2,TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(shaobing == null){
                SmallTool.printTimeAndThread("doesn't buy it");
            }
            else{
                SmallTool.printTimeAndThread("get the "+shaobing);
            }

        },"customer");

        xiaoming.start();
        try{ Thread.sleep(100);} catch(InterruptedException e){ e.printStackTrace();}
        customerA.start();

        try {
            xiaoming.join();
            customerA.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
