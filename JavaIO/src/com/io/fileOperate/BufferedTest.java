package com.io.fileOperate;



import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author wang
 * @create 2022-2022-20-22:45
 */
public class BufferedTest {
    @Test
    public void readTest() throws IOException {
        String filePath = "d://IntegerTest.java";

        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

        String line = null;

        while((line = bufferedReader.readLine())!=null)
        {
            System.out.println(line);
        }

        bufferedReader.close();

    }

    @Test
    public void writeTest() throws IOException {
        String filePath = "d://newFile//file2.txt";

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true));

        writer.write("韩顺平教育");
        writer.newLine();

        writer.write("王老五",0,3);

        writer.close();

    }
}
