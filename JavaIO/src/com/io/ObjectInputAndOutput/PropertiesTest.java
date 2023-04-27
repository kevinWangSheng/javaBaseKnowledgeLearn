package com.io.ObjectInputAndOutput;


import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Properties;

/**
 * @author wang
 * @create 2022-2022-21-13:55
 */
public class PropertiesTest {
    @Test
    public void propertiesReadTest() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src//properties/mysql.properties"));

        String name = properties.getProperty("name");
        String root = properties.getProperty("root");

        System.out.println("name = "+name);

        System.out.println("root ="+root);
    }

    @Test
    public void saveProperties() throws IOException {
        Properties properties = new Properties();

        properties.setProperty("name","lisi");
        properties.setProperty("value","王五");
        properties.setProperty("age","18");

        properties.store(new FileOutputStream("src//properties/mysql2.properties"),null);

        double i = 10.0;
        int i1 = 20;
        int i2 = (int) (i/10);
    }


}
