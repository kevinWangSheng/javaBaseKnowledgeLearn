package com.Class_.Program;

import com.Class_.process.test.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author wang
 * @create 2022-2022-16-14:59
 */
public class DisplayCalendarTest {
    private String name;

    public DisplayCalendarTest(String name){

    }


    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {

        LocalDate date = LocalDate.now();

        int month = date.getMonthValue();

        int today = date.getDayOfMonth();

        date = date.minusDays(today-1);


        DayOfWeek value = date.getDayOfWeek();

        for(int i = 1;i<value.getValue();i++)
            System.out.print("     ");
        while(date.getMonthValue()==month)
        {
            System.out.printf("%3d",date.getDayOfMonth());
            if(date.getDayOfMonth()==today)
            {
                System.out.print("* ");
            }
            else
                System.out.print("  ");
            date=date.plusDays(1);
            if(date.getDayOfWeek().getValue()==1)
                System.out.println();


        }
        if(date.getDayOfWeek().getValue()!=1)
            System.out.println();
        DisplayCalendarTest calendarTest  = new DisplayCalendarTest("wangwu");

        calendarTest.setName(calendarTest.getName()+" lao liu");

        System.out.println(calendarTest.getName());


    }
}

