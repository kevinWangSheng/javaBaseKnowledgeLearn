package leetcode.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author wang
 * @create 2023-2023-10-11:21
 */
public class HeapSort {
    public static void main(String[] args) {
        int arr[] = new int[]{2,43,23,53,121,432,4,35,21,21,43,43,43,43,44,44,534};
        int arr1[] = new int[]{2,1,34,24,5,23,4,5,234,5,43,5,435,64,434,5,2,1,1,1};
        HeapSort heapSort = new HeapSort();
        heapSort.createHeap(arr1);
        System.out.println(Arrays.toString(arr1));

        PriorityQueue<Integer> queue = new PriorityQueue<Integer>((o1, o2)->{
            if(o1>o2){
                return -1;
            }else{
                return 1;
            }
        });
    }


    public void heapfiy(int [] arr,int n,int start){
        int lagest = start;
        int left = start*2+1;
        int right = start*2+2;

        if(left<n && arr[left]>arr[lagest]){
            lagest = left;
        }
        if(right<n &&  arr[lagest]<arr[right]){
            lagest = right;
        }
        if(lagest!=start){
            int temp = arr[lagest];
            arr[lagest] = arr[start];
            arr[start] = temp;

            heapfiy(arr,n,lagest);
        }
    }

    public void createHeap(int arr[]){
        int n = arr.length;

        for(int i = n/2-1;i>=0;i--){
            heapfiy(arr,n,i);
        }

        for(int i = n-1;i>=0;i--){
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapfiy(arr,i,0);
        }
    }


}
