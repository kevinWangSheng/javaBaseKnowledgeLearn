package com.collection.set_;

/**
 * @author wang
 * @create 2022-2022-13-14:05
 */
public class MyDate {
    private int month;
    private int year;

    @Override
    public String toString() {
        return "MyDate{" +
                "month=" + month +
                ", year=" + year +
                ", day=" + day +
                '}';
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public MyDate() {
    }

    public MyDate(int month, int year, int day) {
        this.month = month;
        this.year = year;
        this.day = day;
    }

    private int day;

    public boolean compareBir(MyDate myDate){
        if(year==myDate.getYear()&& month==myDate.getMonth()&& day==myDate.getDay())
            return true;

        return false;
    }
}
