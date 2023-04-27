package stack.sortAlogo;

import java.util.Arrays;
import java.util.Random;

/**
 * @author wang
 * @create 2023-2023-29-12:13
 */
public class ShellSort {
    private static int[] arr = new int[20];

    public static void main(String[] args) {
        for(int i = 0;i<arr.length;i++){
            arr [i] = new Random().nextInt()*1;
        }
        System.out.println(Arrays.toString(arr));
        System.out.println("after the shell sort!!!!");
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr){
        int gap = 0;

//        dividve grouping...
        for(gap = arr.length/2;gap>0;gap/=2){
//           orderly insert sort
            for(int i = gap;i<arr.length;i++){
                int temp = arr[i];
                int j;
                for(j = i;j-gap>0&&temp<arr[j-gap];j-=gap){

                    arr[j] = arr[j-gap];
                }
                arr[j] = temp;

            }
        }
    }
}
