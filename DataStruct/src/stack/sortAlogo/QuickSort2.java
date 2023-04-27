package stack.sortAlogo;

import java.util.Arrays;
import java.util.Random;

/**
 * @author wang
 * @create 2023-2023-07-22:18
 */
public class QuickSort2 {
    public static void quickSort(int []arr,int radix,int width){

        int significantDigit = 1;
        for(int i = 0;i<width;i++){
            int[] count = new int[radix];
            int[] output = new int[arr.length];
            for(int j = 0;j<arr.length;j++){
                count[(arr[j]/significantDigit)%10]++;
            }

            for(int j = 1;j<count.length;j++){
                count[j] +=count[j-1];
            }

            for(int j = arr.length-1;j>=0;j--){
                int bucket = (arr[j]/significantDigit)%radix;
                output[--count[bucket]] = arr[j];
            }

            for(int j = 0;j<arr.length;j++){
                arr[j] = output[j];
            }
            significantDigit*=radix;

        }
    }

    public static void main(String[] args) {
        int arr[] = new int[8000000];

        Random random = new Random();
        int max = 0;
        for(int i = 0;i<arr.length;i++){
            arr[i] = random.nextInt(10000);
            if(arr[i]>max){
                max = arr[i];
            }
        }
        int len = 0;
        while(max>0){
            len++;
            max/=10;
        }

        long startTime = System.currentTimeMillis();

        quickSort(arr,10,len);
        long endTime = System.currentTimeMillis();
        System.out.println("the time that you cost is "+(endTime - startTime)+"ms");

        

//        System.out.println(Arrays.toString(arr));


    }
}
