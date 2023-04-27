package com.allStack.traditionRequest;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

/**
 * @author wang
 * @create 2023-2023-22-11:11
 */
public class SocketClientRequestThread implements Runnable {

    static{
        BasicConfigurator.configure();
    }

    private static final Log logger = LogFactory.getLog(SocketClientRequestThread.class);

    private CountDownLatch countDownLatch;

    private Integer clientIndex;

    public SocketClientRequestThread(CountDownLatch countDownLatch, Integer clientIndex) {
        this.countDownLatch = countDownLatch;
        this.clientIndex = clientIndex;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public Integer getClientIndex() {
        return clientIndex;
    }

    public void setClientIndex(Integer clientIndex) {
        this.clientIndex = clientIndex;
    }

    @Override
    public void run() {
        Socket socket = null;
        OutputStream os = null;
        InputStream is = null;

        try {
            socket = new Socket("localhost",89);
            os = socket.getOutputStream();
            is = socket.getInputStream();

            countDownLatch.await();

            os.write(("这是第"+clientIndex+"条线程发送过来的信息").getBytes());
            os.flush();

            logger.info("第" + this.clientIndex + "个客户端的请求发送完成，等待服务器返回信息");
            int readLen = 0;
            int maxLen = 1024;
            byte[] readBuffer = new byte[maxLen];
            String message = "";
            while((readLen=is.read(readBuffer,0,maxLen))!=-1){
                message+=new String(readBuffer);
            }
            logger.info("这是从服务器接收到的消息:"+message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            try {
                os.close();
                is.close();
                socket.close();
            } catch (IOException e) {
                logger.error("关闭失败了");
                e.printStackTrace();
            }
        }
    }
}
