package com.chapt4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.concurrent.ExecutionException;

/**
 * @author wang
 * @create 2023-2023-16-17:39
 */
public class Nio2CompletionServer {
    private AsynchronousServerSocketChannel serverChannel;

    class ServerCompletionHandler implements CompletionHandler<AsynchronousSocketChannel,Void>{
        private AsynchronousServerSocketChannel channel;
        private CharBuffer charBuffer;
        private ByteBuffer buffer = ByteBuffer.allocate(1024);
        private CharsetDecoder decoder = Charset.defaultCharset().newDecoder();

        public ServerCompletionHandler(AsynchronousServerSocketChannel channel)
        {
            this.channel = channel;
        }

        @Override
        public void completed(AsynchronousSocketChannel result, Void attachment) {
            serverChannel.accept(null,this);
            try {
                while(result.read(buffer).get()!=-1){
                    buffer.flip();
                    charBuffer = decoder.decode(buffer);
                    String request = charBuffer.toString().trim();
                    System.out.println("the request's data is:"+request);
                    ByteBuffer outBuffer = ByteBuffer.wrap("the data has receive!".getBytes());
                    result.write(outBuffer);
                    Thread.sleep(2000);
                    result.write(ByteBuffer.wrap("这就是你输入的？".getBytes()));

                    if(buffer.hasRemaining()){
                        buffer.compact();
                    }else{
                        buffer.clear();
                    }
                }
            } catch (Exception e) {
                System.err.println(e);
            }finally{
                try {
                    result.close();
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
        }
        @Override
        public void failed(Throwable exc, Void attachment) {
            channel.accept(null,this);
            throw new RuntimeException("the server connect faile");
        }
    }

    public void init() throws IOException {
        serverChannel = AsynchronousServerSocketChannel.open();
        if(serverChannel.isOpen()){
            serverChannel.setOption(StandardSocketOptions.SO_RCVBUF,4*1024);
            serverChannel.setOption(StandardSocketOptions.SO_REUSEADDR,true);
            serverChannel.bind(new InetSocketAddress("127.0.0.1",8080));
        }else{
            throw new RuntimeException("the server channel open faile");
        }
    }

    public void start() throws InterruptedException {
        System.out.println("wait the client send the request...");
        serverChannel.accept(null,new ServerCompletionHandler(serverChannel));
        while(true){
            Thread.sleep(5000);
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        Nio2CompletionServer server = new Nio2CompletionServer();
        server.init();
        server.start();
    }
}
