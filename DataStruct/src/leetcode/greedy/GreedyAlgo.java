package leetcode.greedy;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author wang
 * @create 2023-2023-12-21:46
 */
public class GreedyAlgo {
    public static void main(String[] args) {
        GreedyAlgo greedyAlgo = new GreedyAlgo();
        int [][] points = {{-2147483646,-2147483645},{2147483646,2147483647}};
//        greedyAlgo.findMinArrowShots(points);

        int [][] arr ={{1,2},{2,3}};
        int temp[] = arr[1];
        arr[1] = arr[0];
        arr[0] = temp;
        System.out.println(Arrays.toString(arr[1]));
        System.out.println(Arrays.toString(arr[0]));

        ArrayList<Integer> list = new ArrayList();
        list.add(0,1);
        list.add(0,2);
        System.out.println(list.toString());
    }

    public int findMinArrowShots(int[][] points) {
        int count = 0;
        Arrays.sort(points,(o1, o2)->{
            return o1[1]-o2[1];
        });
        int prev = points[0][1];
        for(int i = 1;i<points.length;i++){
            while(i<points.length && prev>=points[i][0] ){
                i++;
            }
            count++;
            if(i<points.length){
                prev = points[i][1];
            }
        }
        return count;
    }
}
