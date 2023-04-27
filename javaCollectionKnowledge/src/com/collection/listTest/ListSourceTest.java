package com.collection.listTest;

import java.util.ArrayList;

/**
 * @author wang
 * @create 2022-2022-12-21:01
 */
public class ListSourceTest {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();

        for(int i = 0;i<15;i++)
            list.add("wahah"+i);

        list.add("wangwu");
        list.add("liliu");
    }
}
