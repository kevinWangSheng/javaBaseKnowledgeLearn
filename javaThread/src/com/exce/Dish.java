package com.exce;

import com.threadBaseKnowledge.SmallTool;

/**
 * @author wang
 * @create 2022-2022-27-12:45
 */
public class Dish {

    public void make(String name,long productTime){
        SmallTool.printTimeAndThread("produce the "+name);
        try{ Thread.sleep(productTime);} catch(InterruptedException e){ e.printStackTrace();}
    }
}
