package com.chapt4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author wang
 * @create 2023-2023-15-21:54
 */
public class NioClient {
    private Selector selector;

    private BufferedReader  clientInput = new BufferedReader(new InputStreamReader(System.in));

    public void init() throws IOException {
        this.selector = Selector.open();
        SocketChannel channel = SocketChannel.open();

        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress("127.0.0.1",8080));
        channel.register(selector, SelectionKey.OP_CONNECT);
    }

    public void start() throws IOException {
        while(true){
//            this method will blocking when don't have the selector
            selector.select();

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while(iterator.hasNext())
            {
                SelectionKey key = iterator.next();
                iterator.remove();
                if(key.isConnectable()) { //connect the key
                    connect(key);
                }
                else if(key.isReadable())
                {   //read the key
                    read(key);
                }
            }

        }

    }

    private void read(SelectionKey key) throws IOException {
//        get the key's channel
        SocketChannel channel = (SocketChannel) key.channel();
//        create a read buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
//        set the client data to the buffer channel and send it to server
        channel.read(buffer);
//        get the server's response
        String response = new String(buffer.array()).trim();
        System.out.println("the server repsonse is:"+response);
//        input the next client data and ready to send it to server
        String input = clientInput.readLine();
//        to write the data to the channel
        ByteBuffer outBuffer = ByteBuffer.wrap(input.getBytes());
        channel.write(outBuffer);

    }

    public void connect(SelectionKey key) throws IOException {
//        get the key's channel and ready to connect
        SocketChannel channel = (SocketChannel) key.channel();
//        judge the key is connecting
        if(channel.isConnectionPending()){
            if(channel.finishConnect()){
                channel.configureBlocking(false);
                channel.register(selector,SelectionKey.OP_READ);
                String request = clientInput.readLine();
                channel.write(ByteBuffer.wrap(request.getBytes()));
            }
            else{
                channel.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        NioClient nioClient = new NioClient();
        nioClient.init();
        nioClient.start();
    }
}
