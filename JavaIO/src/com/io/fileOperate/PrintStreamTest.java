package com.io.fileOperate;


import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * @author wang
 * @create 2022-2022-22-14:59
 */
public class PrintStreamTest {
    @Test
    public void printTest() throws FileNotFoundException {
        PrintStream ps = new PrintStream("d://newFile/print1.txt");
        ps.println(5);

        ps.println("wanglaowu");

        ps.println("老六");

        ps.close();
    }

    @Test
    public void printWriteTest() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("d://newFile/printWrite.txt");

        pw.write("wanglaowu");

        pw.println("666");

        pw.write(pw.getClass().getName()+"|"+"wahaha\n");



        pw.write("老六");
        pw.close();

    }
}
