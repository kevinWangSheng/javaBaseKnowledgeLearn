package com.allStack.reuseIO;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author wang
 * @create 2023-2023-22-19:34
 */
public class ReactorClient {
    public static void main(String[] args) {
//        try {
//            SocketChannel channel = SocketChannel.open();
//            channel.bind(new InetSocketAddress("127.0.0.1",8011));
//            channel.configureBlocking(false);
//            while(!channel.isConnected()){
//
//            }
//            System.out.println("connect successful!!");
//            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
//            buffer.put("this is the client".getBytes());
//            channel.write(buffer);
//
//            buffer.clear();
//
//            ByteBuffer responseBuffer = ByteBuffer.allocate(1024);
//            int read = channel.read(responseBuffer);
//            String message = "";
//            while(read>0){
//                byte[] bytes = new byte[1024];
//                responseBuffer.get(bytes);
//                message+=new String(bytes);
//                responseBuffer.clear();
//                read = channel.read(responseBuffer);
//            }
//            System.out.println("the message is :"+message);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try {
//            Socket socket = new Socket("localhost", 8011);
//            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//
//            // 向服务器发送消息
//            out.println("Hello Server");
//
//            // 读取服务器返回的消息
//            String response = in.readLine();
//            System.out.println("Server response: " + response);
//
//            // 关闭连接
//            in.close();
//            out.close();
//            socket.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
            Socket socket = new Socket("localhost", 8011);
            for(int i = 0;i<20;i++){
                ReactorClientThread reactorClientThread = new ReactorClientThread(socket);
                new Thread(reactorClientThread).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class ReactorClientThread implements  Runnable{
    private Socket socket;
    public ReactorClientThread(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try {

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // 向服务器发送消息
            out.println("Hello Server");

            // 读取服务器返回的消息
            String response = in.readLine();
            System.out.println("Server response: " + response);

            // 关闭连接
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}