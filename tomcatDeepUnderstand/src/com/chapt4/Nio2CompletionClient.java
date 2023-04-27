package com.chapt4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.concurrent.ExecutionException;

/**
 * @author wang
 * @create 2023-2023-16-17:12
 */
public class Nio2CompletionClient {
    class ClientCompletionHandler implements CompletionHandler<Void,Void>{
        private AsynchronousSocketChannel channel;
        private CharBuffer charBuffer = null;
        private CharsetDecoder decoder = Charset.defaultCharset().newDecoder();
        BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));

        ClientCompletionHandler(AsynchronousSocketChannel channel)
        {
            this.channel = channel;
        }

        @Override
        public void completed(Void result, Void attachment) {
            try {
                System.out.println("input the request");
                String request = clientInput.readLine();
                channel.write(ByteBuffer.wrap(request.getBytes()));
                ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
                while(channel.read(buffer).get()!=-1){
                    buffer.flip();
                    String response = decoder.decode(buffer).toString().trim();
                    System.out.println("the data from server is:"+response);
                    if(buffer.hasRemaining()){
                        buffer.compact();
                    }else{
                        buffer.clear();
                    }
                    request = clientInput.readLine();
                    channel.write(ByteBuffer.wrap(request.getBytes()));
                }
            } catch (Exception e) {
                System.err.println(e);
            }finally{
                try {
                    channel.close();
                } catch (IOException e) {
                    System.err.println(e);
                }
            }

        }

        @Override
        public void failed(Throwable exc, Void attachment) {
            throw new RuntimeException("server connect faile");
        }
    }

    public void start() throws IOException, InterruptedException {
        AsynchronousSocketChannel channel = AsynchronousSocketChannel.open();
        if(channel.isOpen()){
            channel.setOption(StandardSocketOptions.SO_RCVBUF,128*1024);
            channel.setOption(StandardSocketOptions.SO_SNDBUF,128*1024);
            channel.setOption(StandardSocketOptions.SO_KEEPALIVE,true);
            channel.connect(new InetSocketAddress("127.0.0.1",8080),null,
                    new ClientCompletionHandler(channel));
            while(true){
                Thread.sleep(5000);
            }
        }else {
            throw new RuntimeException("the channel is close");
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Nio2CompletionClient nio2Client = new Nio2CompletionClient();
        nio2Client.start();
    }
}
