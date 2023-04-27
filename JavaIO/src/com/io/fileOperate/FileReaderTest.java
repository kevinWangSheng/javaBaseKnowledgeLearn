package com.io.fileOperate;



import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;

/**
 * @author wang
 * @create 2022-2022-20-18:44
 */
public class FileReaderTest {
    @Test
    public void readTest() throws IOException {
        String filePath = "d://newFile//file2.txt";

        FileReader fileReader = new FileReader(filePath);

        int data = 0;

        while((data = fileReader.read())!=-1)
        {
            System.out.print((char)data);

        }
    }

    @Test
    public void readTestWay2() throws IOException {
        String filePath = "d://newFile//file2.txt";

        FileReader fileReader = new FileReader(filePath);

        int dataLength = 0;
        char buff[] = new char[10];

        while((dataLength = fileReader.read(buff))!=-1)
        {
            System.out.println(new String(buff));

        }
    }
}
