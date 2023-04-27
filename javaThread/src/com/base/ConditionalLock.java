package com.base;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wang
 * @create 2023-2023-27-13:47
 */
public class ConditionalLock {
    private ReentrantLock reentrantLock = new ReentrantLock(true);
    Condition condition = reentrantLock.newCondition();

    public static void main(String[] args) {
        ConditionalLock conditionalLock = new ConditionalLock();
        conditionalLock.after();
        conditionalLock.before();
    }

    public void before(){
        new Thread(()->{
            reentrantLock.lock();
            try {
                System.out.println("before");
                condition.signal();

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
            }
        },"t1").start();
    }

    public void after(){
        new Thread(()->{
            reentrantLock.lock();
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
            }
            System.out.println("after");
        },"t2").start();
    }
}
