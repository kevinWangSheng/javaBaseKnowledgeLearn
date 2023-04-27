package com.Class_.interface_lambda_innerClass.Interface;

import com.Class_.interface_lambda_innerClass.dynamicProxy.DynamicInterface;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author wang
 * @create 2022-2022-19-14:09
 */
public class Employee implements Cloneable, DynamicInterface {
    private String name;
    private double salary;
    private Date hrieDate;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
        hrieDate = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

//    @Override
//    public int compareTo(Employee o) {
//        return Double.compare(salary,o.salary);
//    }

    @Override
    protected Employee clone() throws CloneNotSupportedException {
        Employee clone = (Employee) super.clone();

        clone.hrieDate = (Date) hrieDate.clone();

        return clone;
    }

    public void setHrieDate(int year,int month,int day){
        Date newDate = new GregorianCalendar(year, month - 1, day).getTime();

        hrieDate.setTime(newDate.getTime());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hrieDate=" + hrieDate +
                '}';
    }

    @Override
    public void play() {
        System.out.println("this is the play proxy");
    }
}


