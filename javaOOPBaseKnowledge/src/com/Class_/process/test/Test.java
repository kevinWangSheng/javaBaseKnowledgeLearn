package com.Class_.process.test;

import java.util.ArrayList;

/**
 * @author wang
 * @create 2022-2022-17-17:20
 */
public class Test {
    protected String wa;
    private Test test;

    public void f(double d){
        System.out.println("double");
        System.out.println(d);
    }

//    public void f(int i){
//        System.out.println("int");
//        System.out.println(i);
//    }

    public Test(){
        this.test = this;
        System.out.println(this==this.test);
    }

    public static void main(String[] args) throws IllegalAccessException {
//        Test  t = new Test();
//        t.f(1);
//
//        father f= new child();
//
//        if(f instanceof child){
//            System.out.println("wahha");
//            child c = (child) f;
//
//        }
//        child [] c = new child[10];

        ArrayList list = new ArrayList();

        for(int i = 1;i<=5;i++)
            list.add(i*i);

        System.out.println(new ObjectAnalyzer().toString(list));

//        System.out.println(new ObjectAnalyzer());


    }





}

class father{
    protected int a = 1;
}

class child extends father{
    {
        System.out.println(a);
    }

}
abstract class A{

}
