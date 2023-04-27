package com.threadBaseKnowledge;

/**
 * @author wang
 * @create 2022-2022-23-16:00
 */
public class ProxyThreadTest {
    public static void main(String[] args) {
        Person person = new Person();

        ProxyThread thread = new ProxyThread(person);

        thread.start();

        new ProxyThread(new Student()).start();
    }
}
