package com.threadBaseKnowledge;

import javax.swing.plaf.SliderUI;

/**
 * @author wang
 * @create 2022-2022-23-16:00
 */
public class Student extends Person{
    @Override
    public void run() {
        System.out.println("this is the student");
        try{ Thread.sleep(2000);} catch(InterruptedException e){ e.printStackTrace();}
        System.out.println("student end....");
    }
}
