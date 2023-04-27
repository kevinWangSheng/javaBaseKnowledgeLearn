package com.volatil;

/**
 * @author wang
 * @create 2023-2023-26-19:00
 */
public class VolatileLockTest {
    private volatile  int num;

    public static void main(String[] args) {
        VolatileLockTest volatileLockTest = new VolatileLockTest();
        System.out.println(++volatileLockTest.num);
    }
}
