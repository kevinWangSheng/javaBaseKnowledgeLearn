package com.exce;

/**
 * @author wang
 * @create 2022-2022-27-13:11
 */
public class SleepTest {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();


        Thread thread1 = new Thread(() -> {
            long end = start;

            long time = 3000;

            while (end - start < time) {
                try {
                    Thread.sleep(start + time - end);
                    end = System.currentTimeMillis();
                } catch (InterruptedException e) {
                    end = System.currentTimeMillis();
                }

            }
            System.out.println(end - start);
        }, "t1");

        thread1.start();

        thread1.interrupt();

    }
}
