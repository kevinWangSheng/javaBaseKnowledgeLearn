package com.chapt4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author wang
 * @create 2023-2023-15-22:16
 */
public class NioServer {
    private Selector selector;

    public void init() throws IOException {
//        config the selector
        selector = Selector.open();
//        get the server's socket channel
        ServerSocketChannel channel = ServerSocketChannel.open();
//        config the channel
        channel.configureBlocking(false);
        ServerSocket socket = channel.socket();
        socket.bind(new InetSocketAddress(8080));
        channel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void start() throws IOException {
        while(true){
//            the method select() is blocked
            selector.select();
            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
            while(iter.hasNext()){
                SelectionKey key = iter.next();
                iter.remove();
                if(key.isAcceptable()){
                    accept(key);
                }
                else if(key.isReadable()){
                    read(key);
                }
            }
        }
    }

    private void accept(SelectionKey key) throws IOException {
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        SocketChannel channel = server.accept();

        channel.configureBlocking(false);
        channel.register(selector,SelectionKey.OP_READ);

    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        System.out.println("the client's data is:"+new String(buffer.array()).trim());
        ByteBuffer outBuffer = ByteBuffer.wrap("the data has receive".getBytes());
        channel.write(outBuffer);
    }

    public static void main(String[] args) throws IOException {
        NioServer nioServer = new NioServer();
        nioServer.init();
        nioServer.start();
    }
}
