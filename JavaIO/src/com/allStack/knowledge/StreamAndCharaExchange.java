package com.allStack.knowledge;

import java.io.*;

/**
 * @author wang
 * @create 2023-2023-19-18:36
 */
public class StreamAndCharaExchange {
    public static void main(String[] args) throws IOException {
        String str = "王老五";
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("out1.txt"));
        osw.write(str);
        osw.close();
    }
}
