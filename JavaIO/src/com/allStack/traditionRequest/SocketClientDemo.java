package com.allStack.traditionRequest;

import java.util.concurrent.CountDownLatch;

/**
 * @author wang
 * @create 2023-2023-22-11:42
 */
public class SocketClientDemo {
    private CountDownLatch countDownLatch;
    private int threadCount = 20;

    public static void main(String[] args) {
        SocketClientDemo socketClientDemo = new SocketClientDemo();
        socketClientDemo.countDownLatch = new CountDownLatch(socketClientDemo.threadCount);
        for(int i = 0;i<socketClientDemo.threadCount;i++,socketClientDemo.countDownLatch.countDown()){
            SocketClientRequestThread client = new SocketClientRequestThread(socketClientDemo.countDownLatch, i);
            new Thread(client).start();
//            try{ Thread.sleep(1000);} catch(InterruptedException e){ e.printStackTrace();}
        }

        synchronized (SocketClientDemo.class){
            try {
                SocketClientDemo.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


