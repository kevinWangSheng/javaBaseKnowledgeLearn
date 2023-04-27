package com.io.fileOperate;

/**
 * @author wang
 * @create 2022-2022-20-22:09
 */
public class BufferReader_ extends Reader_{
    private Reader_ reader;

    public BufferReader_(Reader_ reader) {
        this.reader = reader;
    }

    @Override
    public void readData() {
        System.out.println("this is the readData");
    }

    public void readString(){
        System.out.println("this is the read String");
    }

    public void readFileMulit(){
        for(int i = 0;i<10;i++){
            readFile();
            System.out.println("read file");
        }
    }


    public void readOldData(){
        reader.readData();
    }
}
