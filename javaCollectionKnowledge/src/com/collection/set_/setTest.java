package com.collection.set_;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author wang
 * @create 2022-2022-12-22:07
 */
public class setTest {
    public static void main(String[] args) {
        Set set = new HashSet();

        set.add("wangwu");
        set.add(null);
        set.add("laoliu");
        set.add("zhangsan");
        set.add("lisi");
        set.add("wangwu");

        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            Object next =  iterator.next();
            System.out.println(next);
        }

        for(Object obj:set)
            System.out.println(obj);


    }
}
