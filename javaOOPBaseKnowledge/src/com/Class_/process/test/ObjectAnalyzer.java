package com.Class_.process.test;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * @author wang
 * @create 2022-2022-18-17:18
 */
public class ObjectAnalyzer {
    private ArrayList<Object> list;

    public ObjectAnalyzer(){
        list = new ArrayList<>();
    }

//    使用递归的方式对一个对象中的所有类型，字段进行遍历输出其所对应的类型是什么，以及值，然后拼接成一段字符串返回
    public String toString(Object obj) throws IllegalAccessException {
        if(list.contains(obj)) return " ";
        if(obj==null) return "null";

        list.add(obj);

        Class<?> cl = obj.getClass();

        if(cl ==String.class) return (String)obj;

        if(cl.isArray())
        {
            String r = cl.getComponentType()+"[";
            for(int i = 0;i< Array.getLength(obj);i++)
            {
                if(i>0) r+=",";
                Object value = Array.get(obj, i);
                if(cl.getComponentType().isPrimitive()) r+=value;
                else r+=toString(value);
            }
            return r+"]";
        }

        String r = cl.getName();
        do{
            r+="[";
            Field[] fields = cl.getDeclaredFields();

            AccessibleObject.setAccessible(fields ,true);

            for(Field f:fields){
                if(!Modifier.isStatic(f.getModifiers())) {
                    if (r.endsWith("]")) r += ",";
                    r+=f.getName()+"=";
                    Object var = f.get(obj);
                    if(f.getType().isPrimitive()) r+=var;
                    else r+=toString(var);
                }



            }
            r+="]";
            cl = cl.getSuperclass();

        }while(cl!=null);

        return r;
    }
}
