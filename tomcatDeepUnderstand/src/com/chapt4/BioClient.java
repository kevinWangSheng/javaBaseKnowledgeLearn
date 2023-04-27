package com.chapt4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author wang
 * @create 2023-2023-15-14:18
 */
public class BioClient {
    public static void main(String[] args) throws IOException {
//        创建一个socket
        Socket socket = new Socket("127.0.0.1",8080);

//        获取对应的client的输入
        BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));

//        获取socket的输出
        PrintWriter writer = new PrintWriter(socket.getOutputStream());

//        获取socket的输入
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));


        String input = clientInput.readLine();

        while(!input.equals("exit"))
        {
//            输出客户端输入的内容
            writer.println(input);
            writer.flush();

//            输出服务器响应的内容
            System.out.println("the data from server's context is:"+reader.readLine());
            input = clientInput.readLine();
        }
        writer.println(input);
        writer.flush();

        writer.close();
        reader.close();
        socket.close();

    }
}
