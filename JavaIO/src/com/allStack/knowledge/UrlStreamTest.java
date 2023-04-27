package com.allStack.knowledge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author wang
 * @create 2023-2023-19-18:29
 */
public class UrlStreamTest {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.baidu.com");

        InputStream is = url.openStream();

        InputStreamReader isr = new InputStreamReader(is,"utf-8");

        BufferedReader br = new BufferedReader(isr);

        String line = null;
        while(br.readLine()!=null){
            System.out.println(line);
        }
        br.close();
    }
}
