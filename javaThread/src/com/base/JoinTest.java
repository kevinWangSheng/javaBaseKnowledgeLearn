package com.base;

/**
 * @author wang
 * @create 2023-2023-27-13:34
 */
public class JoinTest {
    static class ThreadA extends Thread{
        @Override
        public void run() {
            try{ Thread.sleep(1000);} catch(InterruptedException e){ e.printStackTrace();}
            System.out.println("A");
        }
    }

    static class ThreadB extends Thread{
        private ThreadA threadA;

        public ThreadB(ThreadA threadA) {
            this.threadA = threadA;
        }

        @Override
        public void run() {
            try {
                threadA.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B");
        }
    }

    public void test(){
        ThreadA a = new ThreadA();
        ThreadB b = new ThreadB(a);
        b.start();
        a.start();
    }

    public static void main(String[] args) {
        new JoinTest().test();
    }
}
