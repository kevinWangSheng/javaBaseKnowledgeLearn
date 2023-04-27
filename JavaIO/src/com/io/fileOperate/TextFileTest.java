package com.io.fileOperate;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * @author wang
 * @create 2022-2022-22-15:14
 */
public class TextFileTest {

    public static void main(String[] args) throws IOException {
        Employee []staff = new Employee[3];

        staff[0] = new Employee("wangwu",2000,2022,10,19);
        staff[1] = new Employee("lisi",3000,2022,8,22);
        staff[2] = new Employee("zhangsan",4000,2022,1,24);

        var out = new PrintWriter("d://newFile/employee.dat", StandardCharsets.UTF_8);

        wirteData(out,staff);

        var in = new Scanner(new FileInputStream("d://newFile/employee.dat"));

        Employee[] employees = readData(in);

        for(Employee e:employees)
            System.out.println(e);


//        System.out.println(Charset.defaultCharset());
    }

    private static void wirteData(PrintWriter out, Employee[] employees){

        int length = employees.length;

        out.println(length);

        for(int i = 0;i<length;i++){
            writeEmployee(out,employees[i]);
        }
        out.close();
    }

    private static void writeEmployee(PrintWriter out,Employee employee) {
        out.write(employee.getName()+"|"+employee.getSalary()+"|"+employee.getHireDate());
        out.println();
    }

    private static Employee[] readData(Scanner in){
        int length = in.nextInt();

        in.nextLine();

        Employee[] employees = new Employee[length];
        for(int i = 0;i<length;i++){
            employees[i] = readEmployee(in);
        }
        in.close();
        return employees;


    }

    private static Employee readEmployee(Scanner in) {
        String line = in.nextLine();
        String[] split = line.split("\\|");

        String name = split[0];

        double salary = Double.parseDouble(split[1]);

        LocalDate hireDay = LocalDate.parse(split[2]);

        int year = hireDay.getYear();

        int month = hireDay.getMonthValue();

        int day = hireDay.getDayOfMonth();

        return new Employee(name,salary,year,month,day);


    }
}


