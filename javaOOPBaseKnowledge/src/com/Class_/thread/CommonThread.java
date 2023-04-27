package com.Class_.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wang
 * @create 2022-2022-17-13:48
 */
public class CommonThread {
    private int production[];
    private int count;
    private static final int BUFF_SIZE=6;

    Lock lock = new ReentrantLock();

    public CommonThread(){
        production = new int[BUFF_SIZE];
        count=0;
    }

    public synchronized int get(){
        int result;

        try {
            while(count<=0)
                wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        result= production[count--];
        System.out.println("正在消费第"+(count+1)+"个产品 学号：2020122013");
        notifyAll();
        return result;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static int getBuffSize() {
        return BUFF_SIZE;
    }

    public synchronized void set(int productor){
        try {
            while(count>=BUFF_SIZE-1)
                wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        production[count++] = productor;
        System.out.println("生产者生产的第"+(count+"个商品被放入缓冲区,学号：2020122013"+"缓冲区目前空余"+(BUFF_SIZE-count)));

        notify();

    }


}
