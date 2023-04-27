package com.allStack.reuseIO;

/**
 * @author wang
 * @create 2023-2023-22-19:33
 */
public class ReactorServer {
    public static void main(String[] args) {
        Reactor reactor = new Reactor(8011);
        new Thread(reactor).start();
    }
}
