package com.allStack.knowledge;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static  org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author wang
 * @create 2023-2023-18-21:42
 */
public class InputStreamTest {
    private InputStream is;
    private final static String CONTENT = "hello,i am your father!";

    @BeforeEach
    public void setUp(){
        this.is = InputStreamTest.class.getResourceAsStream("/input.txt");
    }

    @Test
    public void testReadAllByte() throws IOException {
        String content = new String(is.readAllBytes());
        assertEquals(CONTENT,content);
    }

    @Test
    public void testRead() throws IOException {
        byte[] buffer = new byte[5];
        is.read(buffer,0,buffer.length);
        String content = new String(buffer);
        assertEquals("hello",content);
    }

    @Test
    public void testByteArrayOutputStream() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        is.transferTo(baos);
        assertEquals(CONTENT,baos.toString());
    }
}
