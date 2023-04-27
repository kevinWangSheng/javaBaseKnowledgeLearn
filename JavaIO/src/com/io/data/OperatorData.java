package com.io.data;

import java.io.*;
import java.util.Arrays;
import java.util.Locale;

/**
 * @author wang
 * @create 2023-2023-24-11:26
 */
public class OperatorData {
    public static void main(String[] args) throws IOException {
        int[] arr= new int[]{1,2,3,4};
        String intStr = Arrays.stream(arr)
                .mapToObj(String::valueOf)
                .reduce((x, y) -> {
                    return x + " " + y;
                }).get();

        File file = new File("JavaIO/src/properties/data.data");

        if (!file.exists()){
            file.createNewFile();
        }


        FileOutputStream os = new FileOutputStream("JavaIO/src/properties/data.data");
        os.write(intStr.getBytes());

        FileInputStream is = new FileInputStream(new File("JavaIO/src/properties/data.data"));
        FileReader reader = new FileReader("JavaIO/src/properties/data.data");

        byte[] buffer = new byte[1024];

        is.read(buffer);

        String newData = new String(buffer);

        System.out.println(newData);

        String[] s = newData.split(" ");
        int array[] = new int[arr.length];

        for(int i = 0;i<s.length;i++)
        {
            array[i] = Integer.parseInt(s[i].trim());

        }

        for(int value: array){
            System.out.printf("%d\t",value);
        }

    }
}
