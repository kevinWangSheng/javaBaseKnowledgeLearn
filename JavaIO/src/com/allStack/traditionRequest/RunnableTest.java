package com.allStack.traditionRequest;

/**
 * @author wang
 * @create 2023-2023-22-13:19
 */
public class RunnableTest implements Runnable{
    public RunnableTest(){
        System.out.println("this is a test");
    }
    @Override
    public void run() {
        System.out.println("run!");
    }
}
