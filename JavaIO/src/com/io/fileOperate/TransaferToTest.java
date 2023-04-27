package com.io.fileOperate;



import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author wang
 * @create 2022-2022-22-12:55
 */
public class TransaferToTest {
    @Test
    public void transaferToTest() throws IOException {
        FileInputStream fis = new FileInputStream("d://newFile/file2.txt");

        FileOutputStream fos = new FileOutputStream("d://newFile/file1.txt");

        InputStream is = fis;
        fis.transferTo(fos);
        System.out.println("successful");
        System.out.println(System.getProperty("user.dir"));


    }
}
