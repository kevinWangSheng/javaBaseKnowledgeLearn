package com.io.fileOperate;

/**
 * @author wang
 * @create 2022-2022-20-22:13
 */
public class BufferWriter_ extends Writer_{

    private Writer_ writer;

    public BufferWriter_(Writer_ writer) {
        this.writer = writer;
    }

    @Override
    public void writeData() {
        System.out.println("this is the write data");
    }

    public void writeOldFile(){
        writer.writeData();
    }

    public void writeFileMulit(){
        for(int i = 0;i<10;i++)
        {
            writeFile();
            System.out.println("write file....");
        }
    }

    public void writeString(){
        System.out.println("this is the write String");
    }
}
