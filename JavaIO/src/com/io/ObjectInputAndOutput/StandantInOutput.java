package com.io.ObjectInputAndOutput;



import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author wang
 * @create 2022-2022-21-12:47
 */
public class StandantInOutput {
    @Test
    public void standrantInOutput(){
        System.out.println(System.in);
        System.out.println(System.in.getClass().getName());

        System.out.println(System.out);
        System.out.println(System.out.getClass().getName());
    }

    @Test
    public void buffTest() throws IOException {
        String filePath = "d://newFile/file2.txt";
       InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath),"gb2312");

        BufferedReader br = new BufferedReader(isr);

        String line = br.readLine();

        System.out.println(line);

        br.close();
    }

    @Test
    public void buffWriteTest() throws IOException {
        String filePath = "d://newFile/file1.txt";
        String charSet = "utf-8";

        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath),charSet);

        BufferedWriter bw = new BufferedWriter(osw);

        bw.write("wanglaowu",0,8);
        bw.newLine();

        bw.write("老六是你啊");

        bw.close();



    }


}
