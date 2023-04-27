package stack.searchAlog;

import java.util.Arrays;

/**
 * @author wang
 * @create 2023-2023-30-16:33
 */
public class FeibonaqiAlgo {
    private static int[] arr;

    public static void main(String[] args) {
        arr = new int[]{1,3,5,7,8,12,13};

        int resIndex = fibSearch(arr, 10);

        System.out.println(resIndex);
    }

    private static int[] fib(){
        int [] fib= new int[arr.length];
        fib[0] = fib[1] = 1;
        for(int i = 2;i<fib.length;i++){
            fib[i] = fib[i-1]+fib[i-2];
        }
        return fib;
    }

    private static int fibSearch(int[] arr,int key){
        int[] fib = fib();
        int low = 0;
        int high = arr.length-1;
        int mid = 0;
        int k = 0;
        while(fib[k]<arr.length){
            k++;
        }
        int orgLen = arr.length;
        int[] temp = Arrays.copyOf(arr, fib[k]);

        for(int i = orgLen;i<arr.length;i++){
            arr[i] = arr[i-1];
        }

        while(low<=high){
            mid = low+fib[k-1]-1;
            if(temp[mid]>key){
                high = mid-1;
                k--;
            }
            else if(temp[mid]<key){
                low = mid+1;
                k-=2;
            }else{
                if(mid<=high)
                {
                    return mid+1;
                }else{
                    return high+1;
                }
            }
        }
        return -1;


    }
}
