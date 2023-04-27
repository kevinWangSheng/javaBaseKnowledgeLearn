package com.allStack.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author wang
 * @create 2023-2023-22-16:11
 */
public class NioServer {


    public static void main(String[] args) {
        try {
            Selector selector = Selector.open();

            ServerSocketChannel channel = ServerSocketChannel.open();

            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_ACCEPT);

            ServerSocket socket = channel.socket();
            socket.bind(new InetSocketAddress("127.0.0.1",8000));

            while(true){
    //            获取到达的事件
                selector.select();
                Set<SelectionKey> keys = selector.keys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while(iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    if(selectionKey.isAcceptable()){
//                        获取准备好的IO事件的通道
                        ServerSocketChannel socketChannel = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel acceptChannel = socketChannel.accept();
                        acceptChannel.configureBlocking(false);
                        acceptChannel.register(selector,SelectionKey.OP_READ);
                    }else if(selectionKey.isReadable()){
                        SocketChannel sChannel = (SocketChannel) selectionKey.channel();
                        System.out.println(sChannel);
                        System.out.println("read");
                        sChannel.close();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String DataFormatSocketChannel(SocketChannel channel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        StringBuilder sb = new StringBuilder();
        while(true){
            int len = channel.read(buffer);
            if(len==-1){
                break;
            }
            buffer.flip();
            sb.append(buffer);

        }
        return sb.toString();
    }
}
