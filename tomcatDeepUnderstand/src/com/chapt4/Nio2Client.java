package com.chapt4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.concurrent.ExecutionException;

/**
 * @author wang
 * @create 2023-2023-16-15:56
 */
public class Nio2Client {
    private AsynchronousSocketChannel channel;
    private BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));
    private CharBuffer charBuffer;
    private CharsetDecoder decoder = Charset.defaultCharset().newDecoder();

    public void init() throws IOException, ExecutionException, InterruptedException {
        channel = AsynchronousSocketChannel.open();
        if(channel.isOpen()){
            channel.setOption(StandardSocketOptions.SO_RCVBUF,128*1024);
            channel.setOption(StandardSocketOptions.SO_SNDBUF,128*1024);
            channel.setOption(StandardSocketOptions.SO_KEEPALIVE,true);
            Void connect = channel.connect(new InetSocketAddress("127.0.0.1", 8080)).get();
            if(connect!=null){
                throw new RuntimeException("server connect faile");
            }
        }
        else{
            throw new RuntimeException("server was not open");
        }
    }

    public void start() throws IOException, ExecutionException, InterruptedException {
        System.out.println("input the request data");
        String request = clientInput.readLine();

        channel.write(ByteBuffer.wrap(request.getBytes()));
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        if(channel.read(buffer).get()!=-1){
            buffer.flip();
            charBuffer = decoder.decode(buffer);
            String response = charBuffer.toString().trim();
            System.out.println("the data from server is:"+response);
            if(buffer.hasRemaining()){
                buffer.compact();
            }
            else {
                buffer.clear();
            }
            request = clientInput.readLine();
            channel.write(ByteBuffer.wrap(request.getBytes()));
        }

    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        Nio2Client nio2Client = new Nio2Client();
        nio2Client.init();
        nio2Client.start();
    }
}
