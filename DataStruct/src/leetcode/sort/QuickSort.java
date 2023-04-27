package leetcode.sort;

import java.util.Arrays;

/**
 * @author wang
 * @create 2023-2023-09-22:32
 */
public class QuickSort {
    public static void main(String[] args) {
        int arr[] = new int[]{2,43,23,53,121,432,4,35,21,21,43,43,43,43,44,44,534};
        int arr1[] = new int[]{2,1};
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(arr1,0,arr1.length-1);
        System.out.println(Arrays.toString(arr1));
    }

    public void eraseOverlapIntervals(int[][] intervals){
        
    }

    public void quickSort(int [] arr,int left,int right){
        if(left<right) {
            int l = left;
            int r = right;
            int index = left;
            int privot = arr[left];
            while (l < r) {
                while (l < r && arr[r] >= privot) {
                    r--;
                }
                if (l++ < r) {
                    arr[index] = arr[r];
                    index = r;
                }
                while (l < r && arr[l] <= privot) {
                    l++;
                }
                if (l < r--) {
                    arr[index] = arr[l];
                    index = l;
                }
            }
            arr[index] = privot;
            quickSort(arr, left, index - 1);
            quickSort(arr, index + 1, right);
        }
        int num = 1;
        switch (num){
            case 1: break;
        }
    }
}
