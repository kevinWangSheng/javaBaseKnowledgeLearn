package stack.sortAlogo;

import java.util.Arrays;
import java.util.Random;

/**
 * @author wang
 * @create 2023-2023-29-14:01
 */
public class QuickSort {
    private static int[] arr = new int[80000000];

    public static void main(String[] args) {
        int [] array = {3,5,12,4,2,7,23,13,2,3,2,1,2,3,75};
        Random random = new Random();
        for(int i = 0;i<arr.length;i++){
           random.nextInt(800000);
        }

        long startTime = System.currentTimeMillis();
//        quick(arr,0,arr.length-1);
        quickHan(arr,0,arr.length-1);
//        quickHan(array,0,array.length-1);
        long endTime = System.currentTimeMillis();
        System.out.println("the time that you cost is "+(endTime - startTime));

//        System.out.println(Arrays.toString(array));
    }

    public static void quick(int [] arr,int left,int right){
        int l = left;
        int r = right;
        int number = arr[left];
        if(l<r) {
            while (l < r) {
                while (l < right && arr[l] <= number) {
                    l++;
                }
                while (r > left && arr[r] >= number) {
                    r--;
                }
                if (r > l) {
                    int temp = arr[l];
                    arr[l] = arr[r];
                    arr[r] = temp;
                }

            }
            int temp = arr[left];
            arr[left] = arr[r];
            arr[r] = temp;

            quick(arr,left,r-1);
            quick(arr,r+1,right);
        }
    }

    public static void quickHan(int [] arr,int left,int right){
        int l = left;
        int r = right;
        int privote = arr[(left+right)/2];
        int temp = 0;
        while(l<r){
            while(arr[l]<privote){
                l++;
            }
            while(arr[r]>privote){
                r--;
            }
            if(l>r){
                break;
            }
            temp = arr[r];
            arr[r] = arr[l];
            arr[l] = temp;
            if(arr[l]==privote)
            {
                l++;
            }
            if(arr[r]==privote)
            {
                r--;
            }
        }
        if(r>left){
            quickHan(arr,left,r);
        }
        if(l<right){
            quickHan(arr,l,right);
        }
    }
}


