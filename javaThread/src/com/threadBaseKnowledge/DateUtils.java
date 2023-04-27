package com.threadBaseKnowledge;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wang
 * @create 2022-2022-24-21:52
 */
public class DateUtils {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String format(Date date){

        return dateFormat.format(date);

    }

    public synchronized static Date parse(String date) throws ParseException {
        return dateFormat.parse(date);
    }
}
