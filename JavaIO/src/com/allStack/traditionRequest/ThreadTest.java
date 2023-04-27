package com.allStack.traditionRequest;

/**
 * @author wang
 * @create 2023-2023-22-13:19
 */
public class ThreadTest {
    public static void main(String[] args) {
        RunnableTest r = new RunnableTest();
        new Thread(r).start();
    }
}
