package com.io.ObjectInputAndOutput;

import java.io.Serializable;

/**
 * @author wang
 * @create 2022-2022-21-11:53
 */
public class Dog implements Serializable {
    private String name;

    private int age;

    private static String nation;

    private transient String color;

    private Master master;

    private static final long serialVersionUID=1l;

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nation="+nation+
                ", color="+ color +
                ", master="+ master +
                '}';
    }

    public Dog(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
        nation="china";
        color="white";
        this.master = new Master();
    }
}
