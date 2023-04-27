package com.allStack.reuseIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author wang
 * @create 2023-2023-22-18:10
 */
public class Reactor implements Runnable{

    private ServerSocketChannel serverSocketChannel;
    private Selector selector ;
    public Reactor(int port){
        SelectionKey key = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();
//        因为是选择器在监听，所以需要配置成为非阻塞的状态，不然的话半路就会阻塞，那么就线程没法往下执行。
            serverSocketChannel.configureBlocking(false);
            selector = Selector.open();
            key = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            serverSocketChannel.bind(new InetSocketAddress("127.0.0.1",port));
            key.attach(new Acceptor(serverSocketChannel));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                selector.select();
                Set<SelectionKey> keys = selector.keys();
//                这些事件是连接过来的请求，然后在把他转发给acceptor，acceptor在通过subReactor中的线程池在分布处理，这里的分布处理又使用了selector，在处理完成一个事件的时候唤醒另一个事件。
                Iterator<SelectionKey> keyIterator = keys.iterator();
                while(keyIterator.hasNext()){
                    SelectionKey selectionKey = keyIterator.next();
                    dispatch(selectionKey);
//                    keyIterator.remove();
                }
                selector.selectNow();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dispatch(SelectionKey key){
        Runnable r = (Runnable) key.attachment();
        r.run();
    }
}
