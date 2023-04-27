package com.allStack.traditionRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author wang
 * @create 2023-2023-22-11:50
 */
public class SocketServerHandlerThread extends Thread{

    static {
        BasicConfigurator.configure();
    }

    private static final Log logger = LogFactory.getLog(SocketServerHandlerThread.class);
    private Socket socket;

    public SocketServerHandlerThread(Socket socket) {
        logger.info("创建对象"+Thread.currentThread().getName());
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();
            int readLen = 0;
            int maxLne = 1024;
            byte[] contexByte = new byte[maxLne];
            readLen = is.read(contexByte, 0, contexByte.length - 1);
            String message = new String(contexByte);

            logger.info("收到来自客户端的信息:"+message);

            logger.info("发送回去客户端的信息:");

            os.write("返回消息".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(socket!=null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
