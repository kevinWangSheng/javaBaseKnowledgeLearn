package com.Class_.interface_lambda_innerClass.Interface;

/**
 * @author wang
 * @create 2022-2022-19-14:54
 */
public class PriorityClass extends Name implements NameInterface{
    public static void main(String[] args) {
        PriorityClass p = new PriorityClass();
        p.play();
    }
}

class Name{
    public void play(){
        System.out.println("this is the class");
    }
}

interface NameInterface{
    void play();
//    {
//        System.out.println("this is the interface");
//    }

}
