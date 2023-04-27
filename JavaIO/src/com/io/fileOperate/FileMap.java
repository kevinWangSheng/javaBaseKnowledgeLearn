package com.io.fileOperate;



import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;


/**
 * @author wang
 * @create 2022-2022-23-9:09
 */
public class FileMap {
    @Test
    public void fileMap()  {


        OpenOption option = new OpenOption() {

        };
        MappedByteBuffer map = null;
        try {
            FileChannel channel = FileChannel.open(Paths.get("d://newFile/file3.txt"), StandardOpenOption.READ);

            map = channel.map(FileChannel.MapMode.READ_ONLY, 1, 2);
        } catch (IOException e) {
            System.out.println("wahahah");
//            e.printStackTrace();
        }

        System.out.println("laoliu");

        ByteOrder order = map.order();

        System.out.println(order);

        var objects = new ArrayList<Object>();

    }

    @Test
    public void crc32Test() throws IOException {
        Path fileName = Paths.get("d://newFile/a.txt");

        System.out.println("checkInputStream..");
        long startTime = System.currentTimeMillis();
        long value = Crc32Compare.checkInputStream(fileName);
        long endTime = System.currentTimeMillis();
        System.out.println("the value is "+value);
        System.out.println("the time that you cost is "+(endTime - startTime));
        System.out.println();
        System.out.println("---------------------------");

        System.out.println("check BufferedInputStream....");
        startTime = System.currentTimeMillis();
        value = Crc32Compare.checkBufferedInputStream(fileName);
        endTime = System.currentTimeMillis();
        System.out.println("the value is "+value);
        System.out.println("the time that you cost is "+(endTime - startTime));
        System.out.println();
        System.out.println("---------------------------");

        System.out.println("check RandomAccessFile....");
        startTime = System.currentTimeMillis();
        value = Crc32Compare.checkAccessRandomFile(fileName);
        endTime = System.currentTimeMillis();
        System.out.println("the value is "+value);
        System.out.println("the time that you cost is "+(endTime - startTime));
        System.out.println();
        System.out.println("---------------------------");


        System.out.println("check MappedFile....");
        startTime = System.currentTimeMillis();
        value = Crc32Compare.checkMappFile(fileName);
        endTime = System.currentTimeMillis();
        System.out.println("the value is "+value);
        System.out.println("the time that you cost is "+(endTime - startTime));
        System.out.println();
        System.out.println("---------------------------");




    }
}
