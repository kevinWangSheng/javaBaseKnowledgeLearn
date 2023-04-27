package com.threadBaseKnowledge;

import java.util.StringJoiner;

/**
 * @author wang
 * @create 2022-2022-25-23:40
 */
public class SmallTool {
    public static void sleep(long millis){
        try{ Thread.sleep(millis);} catch(InterruptedException e){ e.printStackTrace();}
    }

    public static void printTimeAndThread(String tag){
        String result = new StringJoiner("\t|\t")
                .add(String.valueOf(System.currentTimeMillis()))
                .add(String.valueOf(Thread.currentThread().getId()))
                .add(String.valueOf(Thread.currentThread().getName()))
                .add(tag)
                .toString();
        System.out.println(result);
    }
}
