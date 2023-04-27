package com.io.ObjectInputAndOutput;



import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author wang
 * @create 2022-2022-21-14:08
 */
public class ExerciseTest {
    @Test
    public void JudegeFileExist() throws IOException {
        String filePath = "d://newFile/mytemp/hello.txt";
        File file = new File(filePath);

        File parentFile = file.getParentFile();

        if(!parentFile.exists())
        {
            parentFile.mkdirs();
            file.createNewFile();
        }
        else{
            if(!file.exists())
            {
                file.createNewFile();
            }
            else
                System.out.println("file is exists");
        }


    }

    @Test
    public void ReaderTest() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("d://newFile/file2.txt"),"gbk"));

        int len = 0;
        String line;
        int lineNum = 0;
        while((line = br.readLine())!=null){
            System.out.println(++lineNum+". " +line);
        }
    }
}
