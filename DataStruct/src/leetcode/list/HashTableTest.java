package leetcode.list;

import java.util.*;

/**
 * @author wang
 * @create 2023-2023-19-12:17
 */
public class HashTableTest {
    public static void main(String[] args) {
        HashTableTest hashTableTest = new HashTableTest();
//        int nums[] = new int[]{0,3,1,3,3,3,0,1,0,2,0,3,1,3,-3,2,0,3,1,2,2,-3,2,2,3,3};
//        hashTableTest.findLHS(nums);

        int nums[] = new int[]{9,1,-3,2,4,8,3,-1,6,-2,-4,7};
        hashTableTest.longestConsecutiveByHashSet(nums);
    }

    public int findLHS(int[] nums) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i = 0;i<nums.length;i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }

        int res = 0;
        Map.Entry<Integer,Integer> prev = null;
        for(Map.Entry<Integer,Integer> entry: map.entrySet()){
            if(prev!=null && (Math.abs(entry.getKey()- prev.getKey())==1)){
                if(res<entry.getValue()+prev.getValue()){
                    res = entry.getValue()+prev.getValue();
                }
            }
            prev = entry;
        }
        return res;
    }

    public int longestConsecutive(int[] nums) {
        if(nums==null || nums.length == 0){
            return 0;
        }
        Arrays.sort(nums);
        int res = 0;
        int max = 0;
        int temp = nums[0];
        for(int i = 1;i<nums.length;i++){
            if(nums[i] - temp == 1){
                res++;
                temp = nums[i];
            }else if(temp != nums[i]){
                if(max<res){
                    max = res;
                }
                res = 0;
            }else{
                temp = nums[i];
            }
        }

        return Math.max(max,res)+1;
    }

    public int longestConsecutiveByHashSet(int [] nums){
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0;i<nums.length;i++){
            set.add(nums[i]);
        }
        int max = 0;
        int res = 0;
        for(int num:set){
            if(set.contains(num+1)){
                res++;
            }else{
                if(max<res){
                    max = res;
                }
                res = 0;
            }
        }
        return Math.max(max,res)+1;
    }
}
