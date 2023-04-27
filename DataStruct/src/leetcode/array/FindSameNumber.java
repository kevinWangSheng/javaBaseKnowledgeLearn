package leetcode.array;

/**
 * @author wang
 * @create 2023-2023-27-10:50
 */
public class FindSameNumber {
    public static void main(String[] args) {
        int nums[] = new int[]{2,3,6,11,1,4,7,8,9,10,6,6};
        findDuplicate(nums);
    }
    public static int findDuplicate(int[] nums) {
        // 不符合规矩，你改变了数组
        // Arrays.sort(nums);
        // for(int i = 0;i<nums.length-1;i++){
        //     if(nums[i]==nums[i+1]){
        //         return nums[i];
        //     }
        // }
        // return 0;

        // boolean[] isVisited = new boolean[nums.length];
        // for(int num:nums){
        //     if(isVisited[num]){
        //         return num;
        //     }
        //     isVisited[num] = true;
        // }

        int slow = 0;
        int fast = 0;
        do{
            fast = nums[nums[fast]];
            slow = nums[slow];
        }while(fast!=slow);

        fast = 0;
        while(fast!=slow){
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;

    }
}
