package com.Class_.interface_lambda_innerClass.Interface;

import java.util.Arrays;

/**
 * @author wang
 * @create 2022-2022-19-14:08
 */
public class EmployeeTest {
    public static void main(String[] args) {
        Employee [] employees = new Employee[3];

        employees[0] = new Employee("wangwu",1000);
        employees[1] = new Employee("lisi",2000);
        employees[2] = new Employee("zhangsan",500);

        Arrays.sort(employees,(employee1,employee2)->{
            return Double.compare(employee1.getSalary(),employee2.getSalary());
        });

        System.out.println(Arrays.toString(employees));
    }
}


