package com.threadBaseKnowledge;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wang
 * @create 2022-2022-23-21:24
 */
public class DeadLock {
    public static void main(String[] args) {
        Lock lockFirst = new ReentrantLock();
        Lock lockSecond = new ReentrantLock();

        DieLock dieLock = new DieLock(lockFirst, lockSecond);

        new Thread(()->{
            dieLock.first();
        },"t1").start();

        new Thread(()->{
            dieLock.second();
        },"t2").start();
    }

}

class DieLock{
    private Lock lockFirst;

    private Lock lockSecond;

    public DieLock(Lock lockFirst, Lock lockSecond) {
        this.lockFirst = lockFirst;
        this.lockSecond = lockSecond;
    }

    public void first(){
        lockFirst.lock();
        System.out.println("enter the first");
        lockSecond.lock();
        System.out.println("enter the second");
        lockSecond.unlock();
        System.out.println("unlock the second....");
        lockFirst.unlock();
        System.out.println("unlock the first....");
    }

    public void second(){
//        try{ Thread.sleep(100);} catch(InterruptedException e){ e.printStackTrace();}
        lockSecond.lock();
        System.out.println("enter the third");
        lockFirst.lock();
        System.out.println("enter the fourth");
        lockFirst.unlock();
        System.out.println("unlock the fourth....");
        lockSecond.unlock();
        System.out.println("unlock the third...");
    }
}
