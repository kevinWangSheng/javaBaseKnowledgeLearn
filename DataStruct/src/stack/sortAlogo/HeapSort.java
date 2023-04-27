package stack.sortAlogo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wang
 * @create 2023-2023-02-13:07
 */
public class HeapSort {
    private int arr[];

    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        heapSort.arr = new int[]{4,6,8,5,9};
//        heapSort.adjustHeap(heapSort.arr, 1,heapSort.arr.length);
//        heapSort.heapSort(heapSort.arr);
//        heapSort.createMaxHeap(heapSort.arr);
        heapSort.heapSortByUp(heapSort.arr);
        System.out.println(Arrays.toString(heapSort.arr));
    }


    public void adjustHeap(int[] arr,int i,int length){
        int temp = arr[i];
        for(int k = i*2+1;k<length;k=k*2+1){
            if(k+1<length && arr[k]<arr[k+1])
            {
                k++;
            }if(arr[k]>arr[i]){
                arr[i] = arr[k];
                i = k;
            }else{
                break;
            }
        }
        arr[i] = temp;

    }

    public void heapSort(int arr[]){
        for(int i= arr.length/2-1;i>=0;i--){
            adjustHeap(arr,i,arr.length);
        }

        int temp = 0;
        for(int i = arr.length-1;i>0;i--){
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,i);
        }
    }

    public void createHeapByInsert(int arr[],int length){
        int i = length;
        int temp = 0;
        while(i>0 && arr[i]>arr[(i-1)/2]){
            temp = arr[i];
            arr[i] = arr[(i-1)/2];
            arr[(i-1)/2] = temp;

            i = (i-1)/2;
        }

        List<Integer> list = new ArrayList<>();

    }

    public void createMaxHeap(int arr[],int length){
        for(int i = 0;i<length;i++){
            createHeapByInsert(arr,i);
        }
    }

    public void heapSortByUp(int arr[]){
        createMaxHeap(arr,arr.length);
        int temp = 0;
        for(int i = arr.length-1;i>0;i--)
        {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            createMaxHeap(arr,i);
        }
    }
}
