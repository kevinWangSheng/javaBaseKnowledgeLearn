package com.threadBaseKnowledge;

/**
 * @author wang
 * @create 2022-2022-23-14:17
 */
public class CalCPUNum {
    public static void main(String[] args) {
        var runtime = Runtime.getRuntime();

        int i = runtime.availableProcessors();

        System.out.println(i);


    }
}
