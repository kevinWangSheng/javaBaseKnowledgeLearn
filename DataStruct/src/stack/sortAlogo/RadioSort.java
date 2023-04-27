package stack.sortAlogo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * @author wang
 * @create 2023-2023-30-12:19
 */
public class RadioSort {
    private static int[] arr;
    private static int[][] bucket;

    public static void main(String[] args) {
        arr = new int[80000000];
        bucket = new int[10][arr.length];
        int max = 0;
        Random random = new Random();
        for(int i = 0;i<arr.length;i++){
            arr[i] = random.nextInt(1000);
            if(max<arr[i])
            {
                max = arr[i];
            }
        }
        String maxNumStr = ""+max;
        int maxLength = maxNumStr.length();

//        System.out.println(Arrays.toString(arr));
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(date));
        radioSort(arr,bucket,maxLength);
//        System.out.println(Arrays.toString(arr));
        System.out.println(format.format(new Date()));


    }

    public static void radioSort(int [] arr,int [][]bucket,int maxLength){
        int arrIndex;
        for(int i = 0;i<maxLength;i++){
            int buckIndex[] = new int[10];
            for(int j = 0;j<arr.length;j++) {
                int bucketNum = (int) (arr[j] / Math.pow(10, i) % 10);
                bucket[bucketNum][buckIndex[bucketNum]++] = arr[j];
            }
            arrIndex = 0;
            for(int k = 0;k<bucket.length;k++){
                for(int l = 0;l<buckIndex[k];l++){
                    arr[arrIndex++] = bucket[k][l];
                }
                buckIndex[k] = 0;
            }
        }
    }
}
