package com.allStack.knowledge;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author wang
 * @create 2023-2023-19-22:52
 */
public class IOReuseTest {
    private ServerSocketChannel channel = null;
    private Selector selector = null;
    private int port = 9000;

    private void initServer() {
        try {
            channel = ServerSocketChannel.open();
            channel.configureBlocking(false);
            channel.bind(new InetSocketAddress(port));
            selector = Selector.open();
            channel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        initServer();
        System.out.println("the server has start");

        try {
            while(true){
                Set<SelectionKey> keys = selector.keys();
                while (selector.select()>500){
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while(iterator.hasNext()){
                        SelectionKey selectionKey = iterator.next();
                        iterator.remove();
                        if(selectionKey.isAcceptable()){
//                            acceptHandler(selectionKey);
                            System.out.println("is accept able");
                        }else if(selectionKey.isReadable()){
//                            readHandler(selectionKey);
                            System.out.println("is read able");
                        }else if(selectionKey.isWritable())
                        {
                            System.out.println("is writed able");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                selector.close();
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        IOReuseTest ioReuseTest = new IOReuseTest();
        ioReuseTest.start();
    }
}
