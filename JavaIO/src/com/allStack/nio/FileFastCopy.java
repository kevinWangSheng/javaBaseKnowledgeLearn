package com.allStack.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author wang
 * @create 2023-2023-22-15:30
 */
public class FileFastCopy {


    public static void main(String[] args) {
        FileFastCopy fastCopy = new FileFastCopy();
        fastCopy.fastCopy("src/input.txt","src/outCopy.txt");
    }

    public void fastCopy(String src,String desc){
        try {
            FileInputStream fis = new FileInputStream(src);

            FileChannel fcin = fis.getChannel();

            FileOutputStream fos = new FileOutputStream(desc);

            FileChannel fcout = fos.getChannel();

            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            while(true){
                int len = fcin.read(buffer);
                if(len==-1){
                    break;
                }

                buffer.flip();
                fcout.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
