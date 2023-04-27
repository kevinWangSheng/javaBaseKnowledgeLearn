package stack.searchAlog;

import java.util.Random;

/**
 * @author wang
 * @create 2023-2023-30-13:44
 */
public class BinarySearch {
    private static int[] arr= {1,2,3,4,5,7,8,9};

    public static void main(String[] args) {
        int search = search(arr, 0, arr.length-1, 4);
        System.out.println(search);
    }
    private static int search(int arr[],int left,int right,int value){
//        int mid = (left+right)/2;
        if(left>right||value<arr[left]||value>arr[right])
        {
            return -1;
        }
        int mid = left+(right-left)*(value-arr[left])/(arr[right]-arr[left]);
        if(left<right) {
            System.out.println(1111);
            if (value > arr[mid]) {
                return search(arr, mid+1, right, value);
            } else if (value < arr[mid]) {
                return search(arr, left, mid - 1, value);
            } else {
                return mid;
            }
        }else {
            return -1;
        }
    }
}
