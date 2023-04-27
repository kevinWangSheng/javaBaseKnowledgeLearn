package com.allStack.knowledge;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author wang
 * @create 2023-2023-19-23:32
 */
public class NioReuse {
    private Selector selector;
    private ByteBuffer buffer = ByteBuffer.allocate(1024);

    public void startServer() throws IOException {
        // 打开 ServerSocketChannel
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 设置为非阻塞模式
        ssc.configureBlocking(false);
        // 绑定监听端口
        ssc.bind(new InetSocketAddress("localhost", 8080));

        // 创建 Selector
        selector = Selector.open();
        // 注册 ServerSocketChannel 到 Selector，监听 OP_ACCEPT 事件
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("Server started, listening on port 8080...");

        while (true) {
            // 阻塞等待就绪的 Channel，最长阻塞时间为 1s
            selector.select(1000);
            // 获取就绪的 SelectionKey 集合
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();

            // 遍历就绪的 SelectionKey 集合，处理对应的事件
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                // 处理完成后，从集合中删除对应的 SelectionKey，避免重复处理
                keyIterator.remove();

                // 判断当前 SelectionKey 所对应的 Channel 是否已经连接
                if (key.isValid() && key.isAcceptable()) {
                    // 处理 OP_ACCEPT 事件
                    accept(key);
                }

                if (key.isValid() && key.isReadable()) {
                    // 处理 OP_READ 事件
                    read(key);
                }

                if (key.isValid() && key.isWritable()) {
                    // 处理 OP_WRITE 事件
                    write(key);
                }
            }

        }
    }

    private void accept(SelectionKey key) throws IOException {
        // 从 SelectionKey 中获取 ServerSocketChannel
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        // 接受客户端连接，并创建 SocketChannel
        SocketChannel sc = ssc.accept();
        // 设置为非阻塞模式
        sc.configureBlocking(false);
        // 注册 SocketChannel 到 Selector，监听 OP_READ 事件
        sc.register(selector, SelectionKey.OP_READ);

        System.out.println("Client " + sc.getRemoteAddress() + " connected.");
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        buffer.clear();
        int len = sc.read(buffer);
        if (len > 0) {
            buffer.flip();
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            String message = new String(bytes);
            System.out.println("Message from client: " + message);

            sc.register(selector, SelectionKey.OP_WRITE, ByteBuffer.wrap(("Echo: " + message).getBytes()));
        }
    }

    private void write(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.flip();
        sc.write(buffer);
        if (!buffer.hasRemaining()) {
            key.interestOps(SelectionKey.OP_READ);
        }
        buffer.compact();
    }

    public static void main(String[] args) throws IOException {
        NioReuse server = new NioReuse();
        server.startServer();
    }
}
