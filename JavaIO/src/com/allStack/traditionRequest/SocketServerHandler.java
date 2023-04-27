package com.allStack.traditionRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wang
 * @create 2023-2023-22-11:33
 */
public class SocketServerHandler {
    static{
        BasicConfigurator.configure();
    }

    private static final Log logger = LogFactory.getLog(SocketServerHandler.class);

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(89);
        Socket accept = null;
        while(true){
            try {
                accept = server.accept();
//                OutputStream outputStream = accept.getOutputStream();
//                InputStream inputStream = accept.getInputStream();
//
//                int readLen = 0;
//                int maxLen = 1024;
//                byte[] readBuffer = new byte[maxLen];
//                inputStream.read(readBuffer,0,readBuffer.length-1);
//                logger.info("收到来自客户端的信息:"+new String(readBuffer));
//
//                logger.info("回应给客户端的消息:");
//                outputStream.write("回应消息".getBytes());
//
//                outputStream.close();
//                inputStream.close();

//                 new SocketServerHandlerThread(accept).start();
//                new Thread(serverThread).start();

                SocketServerThread1 socketServerThread1 = new SocketServerThread1(accept);
                Thread t1 = new Thread(socketServerThread1);
                t1.start();



                try{ Thread.sleep(500);} catch(InterruptedException e){ e.printStackTrace();}
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
//                accept.close();
            }
        }
    }
}


class SocketServerThread implements Runnable {

    /**
     * 日志
     */
    private static final Log LOGGER = LogFactory.getLog(SocketServerThread.class);

    private Socket socket;

    public SocketServerThread (Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream in = null;
        OutputStream out = null;
        try {
            //下面我们收取信息
            in = socket.getInputStream();
            out = socket.getOutputStream();
            Integer sourcePort = socket.getPort();
            int maxLen = 1024;
            byte[] contextBytes = new byte[maxLen];
            //使用线程，同样无法解决read方法的阻塞问题，
            //也就是说read方法处同样会被阻塞，直到操作系统有数据准备好
            int realLen = in.read(contextBytes, 0, maxLen);
            //读取信息
            String message = new String(contextBytes , 0 , realLen);

            //下面打印信息
            SocketServerThread.LOGGER.info("服务器收到来自于端口: " + sourcePort + "的信息: " + message);

            //下面开始发送信息
            out.write("回发响应信息！".getBytes());
        } catch(Exception e) {
            SocketServerThread.LOGGER.error(e.getMessage(), e);
        } finally {
            //试图关闭
            try {
                if(in != null) {
                    in.close();
                }
                if(out != null) {
                    out.close();
                }
                if(this.socket != null) {
                    this.socket.close();
                }
            } catch (IOException e) {
                SocketServerThread.LOGGER.error(e.getMessage(), e);
            }
        }
    }
}
