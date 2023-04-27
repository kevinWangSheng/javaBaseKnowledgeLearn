package com.CompletableExec;

import org.junit.Test;

import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * @author wang
 * @create 2022-2022-28-19:06
 */
public class ProcessBuilderTest {
    @Test
    public void prcessTest(){
        ProcessBuilder builder = new ProcessBuilder("ipconfig","/all");
//        builder.redirectErrorStream(true);
        builder.redirectError(new File("d://newFile/errorFile1.txt"));
        Process process = null;
        InputStream processInputStream = null;
        OutputStream os = null;

        try {
            process = builder.directory(new File("d://newFile")).start();

            processInputStream = process.getInputStream();


            BufferedReader br = new BufferedReader(new InputStreamReader(processInputStream,"GBK"));


            os = new FileOutputStream("d://newFile/process.txt");

            byte[] buff = new byte[1024];
            int len = 0;
            String line = null;
            while((len=processInputStream.read(buff))>0){
//                System.out.println("aa");
                os.write(buff);

//                System.out.println(line);
            }
            System.out.println("wahah");

            InputStream is_error = process.getErrorStream();
            BufferedReader br_error = new BufferedReader(new InputStreamReader(is_error,"GBK"));

            while(null != (line = br_error.readLine())){
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if (processInputStream!=null) {

                try {
                    processInputStream.close();
                } catch (IOException e) {
                    System.out.println("processInputStream error");
                }
            }
            if(os!=null){

                try {
                    os.close();
                } catch (IOException e) {
                    System.out.println("os error");
                }
            }
        }
        try{ Thread.sleep(10000);} catch(InterruptedException e){ e.printStackTrace();}
        System.out.println(process.exitValue());
        process.destroy();
    }

    @Test
    public void startProcess() throws IOException, InterruptedException {
        Process builder = Runtime.getRuntime().exec("C:\\\\Windows\\\\System32\\\\cmd.exe");

//        builder.start();

        builder.waitFor(100, TimeUnit.SECONDS);
    }
}
