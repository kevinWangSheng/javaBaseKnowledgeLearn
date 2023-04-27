package com.allStack.aio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author wang
 * @create 2023-2023-23-16:22
 */
public class AsynchronousClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",8012);
            OutputStream os = socket.getOutputStream();
            os.write("this is the frist client".getBytes());
            os.write("over".getBytes());
            os.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{ Thread.sleep(100000);} catch(InterruptedException e){ e.printStackTrace();}
    }
}
