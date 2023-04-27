package com.threadBaseKnowledge;

import java.text.ParseException;

/**
 * @author wang
 * @create 2022-2022-24-21:52
 */
public class SimpleDateFormatTest {
    public static void main(String[] args) {
        for(int i = 0;i<3;i++)
            new SimpleDateFormatThread().start();
    }

    private static class SimpleDateFormatThread extends Thread{
        @Override
        public void run() {
            try {
                this.join(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                DateUtils.parse("2013-05-24 06:02:20");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
