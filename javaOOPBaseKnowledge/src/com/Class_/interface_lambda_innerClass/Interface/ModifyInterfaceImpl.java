package com.Class_.interface_lambda_innerClass.Interface;



import java.util.Arrays;

/**
 * @author wang
 * @create 2022-2022-19-13:56
 */
public class ModifyInterfaceImpl implements ModifyInterface,FatherInterface {
    @Override
     public void test() {

    }

    @Override
    public void play() {
        ModifyInterface.super.play();
    }

    public static void main(String[] args) {
        int a[] = {1,41,5};

        Arrays.sort(a);

        System.out.println(Arrays.toString(a));

        System.out.println(new Father() instanceof ModifyInterface);

        new Father().play();
    }
}

class Father extends  ModifyInterfaceImpl{
//    @Override
//    public void play() {
//        System.out.println("666");
//    }
}

interface FatherInterface{
    default void play(){
        System.out.println("this is the test");
    }
}
