package stack.sortAlogo;

import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * @author wang
 * @create 2023-2023-30-10:44
 */
public class MegerSort {
    private static int arr[];

    public static void main(String[] args) {
        arr = new int[10000000];
        Random random = new Random();

        for(int i = 0;i<arr.length;i++)
        {
            arr[i] = random.nextInt(100);
        }
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd--HH-mm--ss");
        String timeStart = format.format(date);
        System.out.println(timeStart);
//        System.out.println(Arrays.toString(arr));
        int[] temp = new int[arr.length];
        megerSort(arr,0,arr.length-1, temp);
        date = new Date();
        System.out.println(format.format(date));
//        System.out.println(Arrays.toString(arr));
    }


    public static void megerSort(int arr[],int left,int right,int temp[]){
        if(left<right){
            int mid = (left+right)/2;
            megerSort(arr,left,mid,temp);
            megerSort(arr,mid+1,right,temp);
            meger(arr,left,right,temp);
        }
    }

    private static void meger(int[] arr, int left, int right, int[] temp) {
        int mid = (left+right)/2;
        int l = left;
        int r = mid+1;
        int tempIndex = 0;
//        sort the min number and move it to other array
        while(l<=mid&&r<=right){
            if(arr[l]<arr[r]){
                temp[tempIndex++] = arr[l++];
            }else{
                temp[tempIndex++] = arr[r++];
            }
        }
        while(l<=mid){
            temp[tempIndex++] = arr[l++];
        }
        while(r<=right){
            temp[tempIndex++] = arr[r++];
        }

//        set the order array to the arr,and the arr is the orderly
        tempIndex= 0;
        for(int i = left;i<=right;i++,tempIndex++){
            arr[i] = temp[tempIndex];
        }
    }
}
