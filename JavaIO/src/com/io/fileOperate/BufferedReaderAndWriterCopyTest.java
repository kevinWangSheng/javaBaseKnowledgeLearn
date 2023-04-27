package com.io.fileOperate;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author wang
 * @create 2022-2022-20-23:03
 */
public class BufferedReaderAndWriterCopyTest {
    @Test
    public void bufferCopy(){
//        String srcPath = "d://IntegerTest.java";
//        String destPath = "d://newFile//IntegerTest.java";

        String srcPath = "d://newFile//1.jpg";
        String destPath = "d://newFile//2.jpg";

        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            br=new BufferedReader(new FileReader(srcPath));

            bw = new BufferedWriter(new FileWriter(destPath));

            String line = null;

            while((line = br.readLine())!=null){
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(br!=null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bw!=null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
