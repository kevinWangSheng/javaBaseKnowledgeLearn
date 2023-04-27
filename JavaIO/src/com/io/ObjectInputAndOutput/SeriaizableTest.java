package com.io.ObjectInputAndOutput;


import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author wang
 * @create 2022-2022-21-11:53
 */
public class SeriaizableTest {
    @Test
    public void objectOutputStream() throws IOException {
        String filePath = "d://newFile//data1.dat";

        ObjectOutputStream obs = new ObjectOutputStream(new FileOutputStream(filePath));

        obs.writeInt(100);

        obs.writeBoolean(true);

        obs.writeBytes("wanglaowu");

        Dog dog = new Dog("旺财",5);

        obs.writeObject(dog);


        obs.close();

    }


    @Test
    public void objectInputStream() throws IOException, ClassNotFoundException {
        String filePath = "d://newFile//data1.dat";

//        ObjectOutputStream obs = new ObjectOutputStream(new FileOutputStream(filePath));
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));

        System.out.println(ois.readInt());

        System.out.println(ois.readBoolean());

        byte buff[] = new byte[100];
        ois.read(buff);
        System.out.println(new String(buff));

        Dog dog = (Dog) ois.readObject();

        System.out.println(dog);
        System.out.println(dog.getAge());
//        byte buff[] = new byte[100];
//        int len;
//        while((len=ois.read(buff))>0){
//            System.out.println(new String(buff));
//        }

        ois.close();


    }
}

