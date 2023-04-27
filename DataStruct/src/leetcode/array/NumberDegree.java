package leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wang
 * @create 2023-2023-28-10:20
 */
public class NumberDegree {
    public static void main(String[] args) {
        int nums[] = new int[]{1,2,2,3,1,4,2};
        findShortestSubArray(nums);
    }

    public static int findShortestSubArray(int[] nums) {
        Map<Integer,Integer> numMap = new HashMap<>();
        Map<Integer,Integer> lastIndex = new HashMap<>();
        Map<Integer,Integer> fristIndex = new HashMap<>();


        for(int i = 0;i<nums.length;i++){
            numMap.put(nums[i],numMap.getOrDefault(nums[i],0)+1);
            fristIndex.put(nums[i],fristIndex.getOrDefault(nums[i],i));
            lastIndex.put(nums[i],i);
        }
        List<Integer> list = new ArrayList();
        int maxDegree = 0;
        int minLength = Integer.MAX_VALUE;
        for(Map.Entry<Integer,Integer> entry : numMap.entrySet()){
            if(maxDegree<entry.getValue()){
                list.clear();
                list.add(entry.getKey());
            }else if(maxDegree==entry.getValue()){
                list.add(entry.getKey());
            }
        }

        for(int num:list){
            int len = lastIndex.get(num) - fristIndex.get(num);
            if(len<minLength){
                minLength = len;
            }
        }
        // 因为你在这个过程中使用的是下标相减，所以跟长度会相差1，所以必须还要加上1才能得到最后的结果
        return minLength+1;



    }
}
