package com.chapt4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wang
 * @create 2023-2023-15-14:25
 */
public class BioServer {
    public static void main(String[] args) throws IOException {
//        use the port to listen the client
        ServerSocket serverSocket = new ServerSocket(8080);

//        wait the client send the data and create a socket to operator
        Socket socket = serverSocket.accept();

//      create the server socket's input
        BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));

        PrintWriter writer = new PrintWriter(socket.getOutputStream());

//      create a reader to reade the client
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        System.out.println(reader.readLine());

        String input = serverInput.readLine();
        while(!input.equals("exit")){
            writer.println("the server's context is:"+input);
            writer.flush();

            System.out.println("the data from client is :"+reader.readLine());
            input = serverInput.readLine();
        }
        writer.println(input);
        writer.flush();
        reader.close();
        writer.close();
        socket.close();
        serverSocket.close();


    }
}
