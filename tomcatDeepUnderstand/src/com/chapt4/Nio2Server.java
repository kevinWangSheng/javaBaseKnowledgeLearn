package com.chapt4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.concurrent.*;

/**
 * @author wang
 * @create 2023-2023-16-16:32
 */
public class Nio2Server {
    private ExecutorService taskExecutor;
    private AsynchronousServerSocketChannel serverChannel;

    class Work implements Callable<String>{

        private CharBuffer buffer;
        private CharsetDecoder decoder = Charset.defaultCharset().newDecoder();
        private AsynchronousSocketChannel channel;
        Work(AsynchronousSocketChannel channel)
        {
            this.channel = channel;
        }

        @Override
        public String call() throws Exception {
            final ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            while(channel.read(buffer).get()!=-1)
            {
                buffer.flip();
                String request = decoder.decode(buffer).toString().trim();
                System.out.println("from the client's data is:"+request);

                ByteBuffer outBuffer = ByteBuffer.wrap("server get the data!".getBytes());
                channel.write(outBuffer).get();
                if(buffer.hasRemaining()){
                    buffer.compact();
                }
                else{
                    buffer.clear();
                }
            }
            channel.close();
            return "OK";
        }
    }

    public void init() throws IOException {
        taskExecutor = Executors.newCachedThreadPool(Executors.defaultThreadFactory());
        serverChannel = AsynchronousServerSocketChannel.open();
        if(serverChannel.isOpen()){
            serverChannel.setOption(StandardSocketOptions.SO_RCVBUF,4*1024);
            serverChannel.setOption(StandardSocketOptions.SO_REUSEADDR,true);
            serverChannel.bind(new InetSocketAddress("127.0.0.1",8080));
        } else
        {
          throw new RuntimeException("the channel is close");
        }
    }

    public void start(){
        System.out.println("wait the client request...");
        while(true){
            Future<AsynchronousSocketChannel> future = serverChannel.accept();

            try {
                AsynchronousSocketChannel channel = future.get();
                taskExecutor.submit(new Work(channel));
            } catch (Exception e) {
                System.err.println(e);
                System.err.println("the server close");
                while(!taskExecutor.isTerminated()){
                }
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Nio2Server nio2Server = new Nio2Server();
        nio2Server.init();
        nio2Server.start();
    }

}
