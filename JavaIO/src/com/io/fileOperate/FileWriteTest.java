package com.io.fileOperate;



import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author wang
 * @create 2022-2022-20-21:19
 */
public class FileWriteTest {
    @Test
    public void writeTest(){
        String filePath = "d://newFile/write.txt";
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath);

            fileWriter.write(1);

            fileWriter.write(new char[]{'a','b','c'});

            fileWriter.write(new char[]{'a','b','c'},0,1);

            fileWriter.write("wangwu");

            fileWriter.write("老刘干嘛呀",0,3);


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(fileWriter!=null)
                    fileWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
