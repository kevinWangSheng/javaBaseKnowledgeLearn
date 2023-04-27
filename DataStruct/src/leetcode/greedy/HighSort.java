package leetcode.greedy;

import java.util.Arrays;

/**
 * @author wang
 * @create 2023-2023-13-0:21
 */
public class HighSort {
    public static void main(String[] args) {
        HighSort highSort = new HighSort();
        int [][] people = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        highSort.reconstructQueue(people);
    }
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people,(o1, o2)->{
            if(o1[1]>o2[1]){
                return 1;
            }else if(o1[1]<o2[1]){
                return -1;
            }else{
                if(o1[0]>o2[0]){
                    return 1;
                }else{
                    return -1;
                }
            }
        });
        int [][] queue = new int[people.length][2];
        for(int i = 0;i<queue.length;i++){
            queue[i][0] = -1;
        }
        int end = 0;
        for(int i = 0;i<people.length;i++){
            int count = 0;
            int start = 0;
            while(count<=people[i][1] && queue[start][0]!=-1){
                if(queue[start][0]>=people[i][0]){
                    count++;
                }
                start++;
            }
            if(count>people[i][1]){
                start--;
            }
            shift(queue, start, end);
            queue[start] = people[i];
            end++;
        }
        return queue;
    }

    public void shift(int [][] arr,int start,int end){
        for(int i = end;i>start;i--){
            arr[i] = arr[i-1];
        }
    }
}
