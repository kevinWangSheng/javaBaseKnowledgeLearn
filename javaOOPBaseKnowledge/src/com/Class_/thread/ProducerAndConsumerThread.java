package com.Class_.thread;

/**
 * @author wang
 * @create 2022-2022-17-14:11
 */
public class ProducerAndConsumerThread {
    public static void main(String[] args) {
        CommonThread commonThread = new CommonThread();

        ProducterThread producer = new ProducterThread(commonThread);

        ConsumerThread consumer = new ConsumerThread(commonThread);

        Thread producerThread2 = new Thread(producer);

        Thread producerThread = new Thread(producer);

        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();
        producerThread2.start();
    }
}
