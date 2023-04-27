package com.threadBaseKnowledge;

import java.lang.reflect.Proxy;

/**
 * @author wang
 * @create 2022-2022-23-15:57
 */
public class ProxyThread {

    private Runnable target;

    public ProxyThread(Runnable taget)
    {
        this.target = taget;
    }

    public void run(){
        System.out.println("this is the proxyThread");
    }

    public void start(){
        start0();
    }

    private void start0() {
        target.run();
    }
}
