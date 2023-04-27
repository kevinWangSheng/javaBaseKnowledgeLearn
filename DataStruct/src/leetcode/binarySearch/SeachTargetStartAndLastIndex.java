package leetcode.binarySearch;

/**
 * @author wang
 * @create 2023-2023-16-11:59
 */
public class SeachTargetStartAndLastIndex {

    public static void main(String[] args) {
        int arr[] = new int[]{5,7,7,8,8,10};
        SeachTargetStartAndLastIndex seachTargetStartAndLastIndex = new SeachTargetStartAndLastIndex();
        seachTargetStartAndLastIndex.searchRange(arr,8);
    }
    public int[] searchRange(int[] nums, int target) {
        // if(nums == null || nums.length==0){
        //     return new int[]{-1,-1};
        // }
        // if(nums.length==1 && nums[0]==target){
        //     return new int[]{0,0};
        // }
        // int left = 0;
        // int right =nums.length-1;
        // while(left<right){
        //     int mid = left+(right-left)/2;
        //     if(nums[mid]==target){
        //         int first = left;
        //         for(int i = 1;i<=mid-left;i++){
        //             if(nums[mid-i]!=target){
        //                 first = mid-i+1;
        //                 break;
        //             }
        //         }
        //         int last = right;
        //         for(int i = mid+1;i<=right;i++){
        //             if(nums[i]!=target){
        //                 last = i-1;
        //                 break;
        //             }
        //         }
        //         return new int[]{first,last};
        //     }else if(nums[mid]>target){
        //         right = mid-1;
        //     }else{
        //         left = mid+1;
        //     }
        // }
        // if(right>=0 && nums[right]==target){
        //     return new int[]{right,right};
        // }
        // return new int[]{-1,-1};
        int left = getIndex(nums,target,true);
        int right = getIndex(nums,target,false)-1;
        if(left<nums.length && right>=0 && nums[left]==target && nums[right]==target){
            return new int[]{left,right};
        }else{
            return new int[]{-1,-1};
        }

    }

    public int getIndex(int[] arr,int target,boolean lower){
        int left = 0;
        int right = arr.length -1;
        int ans = arr.length;
        while(left<=right){
            int mid = left+(right-left)/2;
            if(arr[mid]>target && (lower && arr[mid]==target)){
                ans = mid;
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return ans;
    }
}
