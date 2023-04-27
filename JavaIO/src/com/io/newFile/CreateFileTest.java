package com.io.newFile;



import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author wang
 * @create 2022-2022-20-12:34
 */
public class CreateFileTest {
    @Test
    public void createFile1(){
        String filePath = "d://newFile/file1.txt";

        File file = new File(filePath);

        try {
            file.createNewFile();
            System.out.println("create successful!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void createFile2(){
        String parentPath = "d://newFile";

        File parent = new File(parentPath);

        File file = new File(parent, "/file2.txt");

        try {
            file.createNewFile();
            System.out.println("create successful");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void createFile3(){
        String parentPath = "d://newFile";

        File parent = new File(parentPath);

        File file = new File("d:/newFile", "/file3.txt");

        try {
            file.createNewFile();
            System.out.println("create successful");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getFileInformation(){
        File file = new File("d://newFile/file1.txt");

        System.out.println(file.getName());

        System.out.println(file.getAbsoluteFile());

        System.out.println(file.getPath());

        System.out.println(file.getParent());

        System.out.println(file.length());

        System.out.println(file.isFile());

        System.out.println(file.isDirectory());
    }

    @Test
    public void judgeAndRemoveFile(){
        String filePath = "d://newFile/file1.txt";
        File file = new File(filePath);

        if(file.exists()){
            System.out.println("file exist");
            file.delete();
        }
        else
            System.out.println("file is no exist");
    }

    @Test
    public void mkdirTest(){
        String filePath = "d://newFile//a//c//d";
        File file = new File(filePath);

        if(!file.exists())
        {
            if (file.mkdir()) {
                System.out.println("create the file successful");

            }
            else
                System.out.println("create the directory unsuccessful");
        }
    }

    @Test
    public void readFile(){
        try {
            FileInputStream fileInputStream = new FileInputStream("d://newFile/file3.txt");

            int fileData = 0;

            byte buff[] = new byte[5];

//            use the one character to read
//            while((fileData=fileInputStream.read())!=-1) {
////                fileData = fileInputStream.read();
//                System.out.print((char) fileData);
//            }

//            use byte[] to read
            int buffLength = 0;
            while((buffLength = fileInputStream.read(buff))>0)
            {
                System.out.println(new String(buff,0,buffLength));
            }
            System.out.println("read ending...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void outputStreamTest(){
        String outPath = "d://newFile//a.txt";

        try {
//            default the add to is false;
            FileOutputStream fileOutputStream = new FileOutputStream(outPath,true);

            String str = "wanglaowu s ni ma ";

            fileOutputStream.write(str.getBytes(),0,str.length());
//            fileOutputStream.write('H');

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void fileCopy() throws IOException {
        String fileInput = "d://newFile/1.jpg";
        String fileOutput = "d://newFile/wanglaowu/1.jpg";


        File  inputFile = new File(fileInput);
        File  outFile = new File(fileOutput);
        if (inputFile.getParentFile().exists()) {
            inputFile.getParentFile().mkdirs();
        }
        if(!outFile.getParentFile().exists())
        {
            System.out.println("");
            outFile.getParentFile().mkdirs();
        }

        FileInputStream fileInputStream = new FileInputStream(fileInput);

        FileOutputStream fileOutputStream = new FileOutputStream(fileOutput);

        byte[] buff = new byte[1024];
        int readLength = 0;
        while((readLength = fileInputStream.read(buff))>0){
            fileOutputStream.write(buff,0,readLength);
        }

        if(fileInputStream!=null)
            fileInputStream.close();

        if(fileOutputStream!=null)
            fileOutputStream.close();

    }
}
