package com.collection.listTest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author wang
 * @create 2022-2022-12-17:24
 */
public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();

        arrayList.add(1);
        arrayList.add("home");
        arrayList.add(true);

        System.out.println("after add list="+arrayList);

        arrayList.remove(0);
        System.out.println("after remove list="+arrayList);

        System.out.println("is contain home?"+arrayList.contains("home"));

        System.out.println("the arraylist size is "+arrayList.size());

        System.out.println("after get ..."+arrayList.get(1));

//        arrayList.clear();

        System.out.println("after clear..."+arrayList);

        ArrayList list = new ArrayList();

        list.add(1);

        arrayList.addAll(list);

        System.out.println("after add Collection.. "+arrayList);

        arrayList.add(0,"wangwu");

//        System.out.println(arrayList.addAll(0, new ArrayList()));
        arrayList.addAll(0,list);

        System.out.println("the one index is :"+arrayList.indexOf(1));

        System.out.println(arrayList);
        System.out.println("the last appear 1 is:"+arrayList.lastIndexOf(1));


        System.out.println("remove index is "+arrayList.remove(new Integer(1)));

//        System.out.println(arrayList);

//        arrayList.set(0,"zhangsan");

        List subList = arrayList.subList(0, 3);

        System.out.println(subList);

        Iterator iterator = arrayList.iterator();

        while (iterator.hasNext()) {
            Object next =  iterator.next();

            System.out.println(next);

        }

        String[] strs = new String[10];
        for(String str:strs)
            System.out.println(str);







    }
}
