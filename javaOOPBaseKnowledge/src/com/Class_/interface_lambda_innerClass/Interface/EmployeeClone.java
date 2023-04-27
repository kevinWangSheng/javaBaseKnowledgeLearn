package com.Class_.interface_lambda_innerClass.Interface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author wang
 * @create 2022-2022-19-15:34
 */
public class EmployeeClone {
    private String name;
    private static int age;

    public EmployeeClone(String name) {
        this.name = name;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Employee employee = new Employee("wangwu",1000);
        employee.setHrieDate(2022,10,19);

        Employee copy = employee.clone();

        copy.setHrieDate(2022,11,22);

        System.out.println(copy);
        System.out.println(employee);

//        EmployeeClone employeeClone = new EmployeeClone("wangwu");
//
//        InnerClass n = employeeClone.new InnerClass();

//        InnerClass innerClass = EmployeeClone.new InnerClass();
//
//        new Timer(1, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        }).start();
//        EmployeeClone employeeClone = new EmployeeClone("wangwu");
    }

    {
//        System.out.println(wangwu);
    }

    public class InnerClass{
        private static final int wangwu = 1;

//        public static  void test(){
//            System.out.println(age);
//        }
        {
            System.out.println(name);

            class test{
                private int age;
            }
        }
        public InnerClass(){

        }
    }

    public void start(){
        InnerClass innerClass = new InnerClass();
    }
}
