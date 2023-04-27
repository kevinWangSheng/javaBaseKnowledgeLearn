package com.io.fileOperate;

import java.time.LocalDate;

/**
 * @author wang
 * @create 2022-2022-22-15:15
 */
public class Employee {
    private String name;
    private double salary;

    private LocalDate hireDate;

    public Employee(String name,double salary,int year,int month,int day){
        this.name = name;
        this.salary = salary;

        this.hireDate = LocalDate.of(year,month,day);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDate=" + hireDate +
                '}';
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

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }
}
