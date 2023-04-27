package com.allStack.reuseIO;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.*;

/**
 * @author wang
 * @create 2023-2023-22-18:27
 */
public class Acceptor implements Runnable {
    private ExecutorService poolExecutor = new ThreadPoolExecutor(20,20, 3, TimeUnit.MINUTES,new LinkedBlockingQueue<>());
    private ServerSocketChannel channel;

    public Acceptor(ServerSocketChannel socketChannel){
        this.channel = socketChannel;
    }


    @Override
    public void run() {
        try {
            SocketChannel socket = channel.accept();
            if(socket!=null){
                poolExecutor.execute(new Handler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
