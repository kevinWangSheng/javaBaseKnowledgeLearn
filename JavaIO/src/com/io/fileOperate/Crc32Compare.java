package com.io.fileOperate;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;

/**
 * @author wang
 * @create 2022-2022-23-9:46
 */
public class Crc32Compare {
    public static long checkInputStream(Path fileName) throws IOException {
        InputStream in = Files.newInputStream(fileName);

        CRC32 crc32 = new CRC32();

        int len;

        while((len=in.read())!=-1){
            crc32.update(len);
        }

        return crc32.getValue();

    }

    public static long checkBufferedInputStream(Path fileName) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(fileName));

        byte[] buff= new byte[1024];

        CRC32 crc32 = new CRC32();
        int len;

        while((len=bis.read(buff))>0){
           crc32.update(buff);
        }
        return crc32.getValue();

    }

    public static long checkMappFile(Path fileName) throws IOException {
        try(FileChannel channel = FileChannel.open(fileName))
        {
             int length = (int) channel.size();
            MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0, length);
            CRC32 crc32 = new CRC32();

            for(int i = 0;i<length;i++){
                int b = map.get(i);
                crc32.update(b);
            }

            return crc32.getValue();

        }

    }


    public static long checkAccessRandomFile(Path fileName) throws IOException {
        try(RandomAccessFile accessFile =  new RandomAccessFile(fileName.toFile(),"r")){
            CRC32 crc32 = new CRC32();
            int length = (int) accessFile.length();

            for(int i = 0;i<length;i++){
                accessFile.seek(i);

                int c = accessFile.readByte();

                crc32.update(c);
            }

            return crc32.getValue();
        }
    }
}
