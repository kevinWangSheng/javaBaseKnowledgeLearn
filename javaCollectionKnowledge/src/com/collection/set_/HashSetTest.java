package com.collection.set_;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;


/**
 * @author wang
 * @create 2022-2022-13-13:12
 */
public class HashSetTest {
    public static void main(String[] args) {
        HashSet set = new HashSet();
//        set.add("wangwu");
//        set.add("zhangsan");
//        set.add("wangwu");

//        for(int i = 0;i<12;i++){
//            set.add(new A());
//        }

        set.add(new Employee("wangwu",1800,new MyDate(1,1,1)));

        set.add(new Employee("wangwu",1900,new MyDate(1,1,1)));

        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            Object next =  iterator.next();

            System.out.println(next);
        }
    }


}

class A{
    @Override
    public int hashCode() {
        return 100;
    }
}

class Employee{
    private String name;
    private double sal;

    private MyDate birthday;

    public Employee(String name, double sal, MyDate birthday) {
        this.name = name;
        this.sal = sal;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return birthday.compareBir(employee.birthday)  && Objects.equals(name, employee.name);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", sal=" + sal +
                ", birthday=" + birthday +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
