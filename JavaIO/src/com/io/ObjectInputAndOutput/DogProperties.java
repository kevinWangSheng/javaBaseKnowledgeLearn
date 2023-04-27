package com.io.ObjectInputAndOutput;


import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Properties;

/**
 * @author wang
 * @create 2022-2022-21-14:28
 */
public class DogProperties {
    @Test
    public void dog() throws IOException {
        Properties properties = new Properties();

        properties.load(new FileInputStream("src//properties/dog.properties"));

        String name = properties.getProperty("name");

        int age = Integer.parseInt(properties.getProperty("age"));

        String color = properties.getProperty("color");

        Dog dog = new Dog(name, age, color);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("d://newFile/dog.dat"));

        oos.writeObject(dog);

        oos.close();
        System.out.println("successful");


    }

    @Test
    public void read() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d://newFile/dog.dat"));

        Dog dog = (Dog) ois.readObject();

        System.out.println(dog);
    }
}
