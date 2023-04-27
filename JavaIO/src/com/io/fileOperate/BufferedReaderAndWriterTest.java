package com.io.fileOperate;


import org.junit.jupiter.api.Test;

import java.io.Writer;

/**
 * @author wang
 * @create 2022-2022-20-22:25
 */
public class BufferedReaderAndWriterTest {
    @Test
    public void write(){
        BufferWriter_ bufferWriter_ = new BufferWriter_(new Writer_() {
            @Override
            public void writeData() {
                System.out.println("wahha");
            }
        });

        bufferWriter_.writeFile();

        bufferWriter_.writeFileMulit();

//        bufferWriter_.writeFile();
        bufferWriter_.writeString();

        bufferWriter_.writeData();

        bufferWriter_.writeOldFile();

    }

    @Test
    public void read(){
        BufferReader_ reader_ = new BufferReader_(
                new Reader_() {
                    @Override
                    public void readData() {
                        System.out.println("this is the read old data");
                    }
                }
        );

        reader_.readFile();

        reader_.readData();

        reader_.readFileMulit();

        reader_.readString();

        reader_.readOldData();



    }
}
