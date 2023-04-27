package com.Class_.process.test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author wang
 * @create 2022-2022-18-18:10
 */
public class ArrayCopyTest {

    public static void main(String[] args) {
        int [] a ={1,23,3};
        a = (int[])goodCopy(a, 3);
        System.out.println(Arrays.toString(a));
    }

    public static Object goodCopy(Object obj,int newLength){
        Class<?> cl = obj.getClass();

        if(!cl.isArray()) return null;

        Class<?> componentType = cl.getComponentType();

        int length = Array.getLength(obj);

        Object newArray = Array.newInstance(componentType, newLength);

        System.arraycopy(obj,0,newArray,0,newLength);

        return newArray;


    }

    public static Object[] badCopy(Object[] objs,int newLength){
        Object[] newArray = new Object[newLength];

        System.arraycopy(objs,0,newArray,0,newLength);
        return newArray;



    }
}
