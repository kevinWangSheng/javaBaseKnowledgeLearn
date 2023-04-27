package com.io.ObjectInputAndOutput;


import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * @author wang
 * @create 2022-2022-21-13:38
 */
public class PrintStreamTest {
    @Test
    public void printTest() throws FileNotFoundException {
        PrintStream out = System.out;

        System.setOut(new PrintStream("d://newFile/f1.txt"));

        System.out.println("wahaha");
        System.out.println("牛批");


    }

}
