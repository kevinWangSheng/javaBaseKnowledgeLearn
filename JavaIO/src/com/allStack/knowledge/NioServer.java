package com.allStack.knowledge;

/**
 * @author wang
 * @create 2023-2023-19-23:37
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioServer {

    private Selector selector;

    public static void main(String[] args) {
        NioServer client = new NioServer();
        client.init();
        client.connect();
    }

    private void init() {
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connect() {
        try {
            // 打开SocketChannel，并连接到服务器
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("localhost", 8080));
            socketChannel.register(selector, SelectionKey.OP_CONNECT);

            while (true) {
                selector.select();

                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();

                    if (selectionKey.isConnectable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        if (channel.isConnectionPending()) {
                            channel.finishConnect();
                        }
                        channel.register(selector, SelectionKey.OP_WRITE);
                        System.out.println("连接成功，请输入要发送的消息：");
                    } else if (selectionKey.isWritable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        String message = new BufferedReader(new InputStreamReader(System.in)).readLine();
                        channel.write(ByteBuffer.wrap(message.getBytes()));
                        channel.register(selector, SelectionKey.OP_READ);
                    } else if (selectionKey.isReadable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        channel.read(buffer);
                        String message = new String(buffer.array()).trim();
                        System.out.println("接收到服务器的消息：" + message);
                        channel.register(selector, SelectionKey.OP_WRITE);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

