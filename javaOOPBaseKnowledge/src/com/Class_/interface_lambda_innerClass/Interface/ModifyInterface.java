package com.Class_.interface_lambda_innerClass.Interface;

/**
 * @author wang
 * @create 2022-2022-19-13:56
 */
public interface ModifyInterface {
    public void test();

    default void play(){
        System.out.println("wahaha");
    }
}
