package com.io.fileOperate;



import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author wang
 * @create 2022-2022-20-23:19
 */
public class BufferOutAndInputStreamCopyTest {
    @Test
    public void copyFile(){
        String srcPath = "d://newFile/1.jpg";
        String desPath = "d://newFile/3.jpg";

        BufferedInputStream bis = null;

        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(new FileInputStream(srcPath));
            bos = new BufferedOutputStream(new FileOutputStream(desPath));

            String line;
            int data = 0;
            byte[] buff = new byte[1024];
            while((data = bis.read(buff))>0)
            {
                bos.write(buff,0 ,buff.length );
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                bis.close();
            } catch (IOException e) {
                System.out.println("bis error");
                e.printStackTrace();
            }
            try {
                bos.close();
            } catch (IOException e) {
                System.out.println("bos error");
                e.printStackTrace();
            }
        }
    }
}
