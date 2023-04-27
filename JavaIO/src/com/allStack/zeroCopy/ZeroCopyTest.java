package com.allStack.zeroCopy;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

/**
 * @author wang
 * @create 2023-2023-24-0:11
 */
public class ZeroCopyTest {
    private final static String CONTEXT = "Zero copy implemented by MappedByteBuffer";
    private final static String FILE_NAME = "\\mmap.txt";
    private final static String CHARSET = "utf-8";
    private final static String SOURCE_FILE = "\\source.txt";
    private final static String TARGET_FILE = "\\target.txt";
    @Test
    public void writeToFileByMappedByteBuffer(){
//        Path path = Paths.get(getClass().getResource(FILE_NAME).getPath());
        InputStream inputStream = getClass().getResourceAsStream(FILE_NAME);
        Path path = Paths.get(System.getProperty("user.dir") + FILE_NAME);
        byte[] bytes = CONTEXT.getBytes(Charset.forName(CHARSET));
        try {
            FileChannel channel = FileChannel.open(path, StandardOpenOption.READ,StandardOpenOption.WRITE,StandardOpenOption.TRUNCATE_EXISTING);
            MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, bytes.length);
            if(mappedByteBuffer!=null){
                mappedByteBuffer.put(bytes);
                mappedByteBuffer.force();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void readToFileByMappedByteBuffer(){
        Path path = Paths.get(System.getProperty("user.dir") + FILE_NAME);
        int length = CONTEXT.getBytes(Charset.forName(CHARSET)).length;
        try(FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)){
            MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0, length);
            if(map!=null){
                byte[] bytes = new byte[length];
                map.get(bytes);
                String text = new String(bytes, StandardCharsets.UTF_8);
                System.out.println(CONTEXT.equals(text));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void transferTo(){
        Path path = Paths.get(System.getProperty("user.dir") + SOURCE_FILE);
        try(FileChannel channel = FileChannel.open(path,StandardOpenOption.READ,StandardOpenOption.WRITE,StandardOpenOption.TRUNCATE_EXISTING)){
            byte[] bytes = CONTEXT.getBytes(Charset.forName(CHARSET));
            channel.write(ByteBuffer.wrap(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ChannelTransferTo(){
        try(FileChannel from = new RandomAccessFile(System.getProperty("user.dir")+SOURCE_FILE,"rw").getChannel();
            FileChannel to = new RandomAccessFile(System.getProperty("user.dir")+TARGET_FILE,"rw").getChannel()){

            long position = 0L;
            long offset = to.size();
            from.transferFrom(to,position,offset);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void dfs(){
        String target = "";
        int lastIndexOfPoint = target.lastIndexOf('.');
        StringBuilder sb = new StringBuilder();
        sb.append(1);
        sb.append(2);
        sb.deleteCharAt(0);
        System.out.println(sb.toString());
        sb.deleteCharAt(sb.length()-1);
        String str = "123123";

        int i = str.lastIndexOf('.');
        System.out.println(str.lastIndexOf('3'));
        String[] split = str.split("2");
        System.out.println(Arrays.toString(split));
        System.out.println(str.substring(str.length()-2,str.length()));
        System.out.println("255".compareTo("254")>0);
        System.out.println(str+"1");
        System.out.println(str);
        String ip = "2552551.1.1.35";
        String[] split1 = ip.split(".");
        System.out.println(Arrays.toString(split1));
    }

}
