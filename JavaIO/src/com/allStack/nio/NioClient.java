package com.allStack.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author wang
 * @create 2023-2023-22-16:33
 */
public class NioClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",8000);
            OutputStream os = socket.getOutputStream();

            os.write("write the data to server!".getBytes());

            NioClient.class.wait();
            os.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
