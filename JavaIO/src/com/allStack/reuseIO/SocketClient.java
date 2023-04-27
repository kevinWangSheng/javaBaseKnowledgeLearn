package com.allStack.reuseIO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wang
 * @create 2023-2023-23-9:44
 */
public class SocketClient {
    static CountDownLatch  countDownLatch = new CountDownLatch(20);
    public static void main(String[] args) throws IOException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for(int i = 0;i<20;i++) {
           executorService.execute(()->{
               try {
                   clientConnectAndSend();
               } catch (IOException e) {
                   e.printStackTrace();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           });
            countDownLatch.countDown();
        }
        try{ Thread.sleep(5000);} catch(InterruptedException e){ e.printStackTrace();}
    }

    public static void clientConnectAndSend() throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1",8011);
        OutputStream os = socket.getOutputStream();
        os.write("我是客户端，请求连接了".getBytes());
        String context = "多路复用IO的优缺点不用再使用多线程来进行IO处理了(包括操作系统内核IO管理模块和应用程序进程而言)。当然实际业务的处理中，应用程序进程还是可以引入线程池技术的同一个端口可以处理多种协议，例如，使用ServerSocketChannel测测的服务器端口监听，既可以处理TCP协议又可以处理UDP协议。操作系统级别的优化: 多路复用IO技术可以是操作系统级别在一个端口上能够同时接受多个客户端的IO事件。同时具有之前我们讲到的阻塞式同步IO和非阻塞式同步IO的所有特点。Selector的一部分作用更相当于“轮询代理器”。都是同步IO: 目前我们介绍的 阻塞式IO、非阻塞式IO甚至包括多路复用IO，这些都是基于操作系统级别对“同步IO”的实现。我们一直在说“同步IO”，一直都没有详细说，什么叫做“同步IO”。实际上一句话就可以说清楚: 只有上层(包括上层的某种代理机制)系统询问我是否有某个事件发生了，否则我不会主动告诉上层系统事件发生了:\n" +
                "------\n" +
                "著作权归@pdai所有\n" +
                "原文链接：https://pdai.tech/md/java/io/java-io-nio-select-epoll.html";
        os.write(context.getBytes());
//        try{ Thread.sleep(1000);} catch(InterruptedException e){ e.printStackTrace();}
        os.write("over".getBytes());
        InputStream is = socket.getInputStream();

        byte[] buffer = new byte[1024];
        is.read(buffer,0,buffer.length-1);
        System.out.println(new String(buffer));
        os.close();
        socket.close();
    }
}
