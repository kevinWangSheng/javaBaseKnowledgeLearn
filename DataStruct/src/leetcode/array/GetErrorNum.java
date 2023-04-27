package leetcode.array;

import java.util.Arrays;

/**
 * @author wang
 * @create 2023-2023-26-11:59
 */
public class GetErrorNum {
    public static void main(String[] args) {
        int[] nums = new int[]{3,2,2,4};
//        findErrorNums(nums);
        System.out.println(1<<1);
//        getSum(2,3);
//        String strs[] = new String[]{"abcw","baz","foo","bar","xtfn","abcdef"};
//        System.out.println(Integer.bitCount(3));
//        maxProduct(strs);

        GetErrorNum getErrorNum = new GetErrorNum();
        getErrorNum.judgeSquareSum(2147483600);
    }
    public static int[] findErrorNums(int[] nums) {
        int res[] = new int[2];
        Arrays.sort(res);

        for(int i = 0;i<nums.length;i++){
            if(i+1!=nums[i]){
                res[0] = nums[i];
                res[1] = i+1;
                return res;
            }
        }



        return res;
    }

    public static int maxProduct(String[] words) {
        Arrays.sort(words, (a, b) -> {
            return Integer.compare(b.length(), a.length());
        });
        int max = 0;
        boolean noCon = true;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                for (int k = 0; k < words[j].length(); k++) {
                    if (words[i].indexOf(words[j].charAt(k)) != 0) {
                        noCon = false;
                        break;
                    }

                }
                if (noCon) {
                    max = max < (words[i].length() * words[j].length()) ? words[i].length() * words[j].length() : max;
                }
                noCon = true;
            }
        }
        return max;
    }

    public static int getSum(int a, int b) {
        int n ;
        int res;
        n = a&b;
        res = a|b ^ n;
        while(n!=0){
            n <<=1;
            int temp = n;
            n&=res;
            res^=temp;
        }

        return res;
    }


    public boolean judgeSquareSum(int c) {
        int right = (int)Math.sqrt(c);
        int left = 0;
        while(left<=right){
            long value = left*left+right*right;
            if(value<0){
                right--;
            }
            else if(value<c){
                left++;
            }else if(value>c){
                right--;
            }else{
                return true;
            }
        }
        return false;
    }
}


