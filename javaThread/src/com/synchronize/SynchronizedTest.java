package com.synchronize;

/**
 * @author wang
 * @create 2023-2023-26-15:19
 */
public class SynchronizedTest {
    private Object obj = new Object();

    public void method1(){
        synchronized (obj){

        }
        method2();
    }

    private void method2() {
    }
}
