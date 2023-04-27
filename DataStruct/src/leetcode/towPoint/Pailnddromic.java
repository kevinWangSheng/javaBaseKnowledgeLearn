package leetcode.towPoint;

/**
 * @author wang
 * @create 2023-2023-06-12:20
 */
public class Pailnddromic {
    public static void main(String[] args) {
        String str = "aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga";
        validPalindrome(str);
    }

    public static boolean validPalindrome(String s) {
        if(s==null ||s.length()==0||s.length()==0){
            return true;
        }
        int left = 0;

        int right = s.length()-1;
        boolean isDelete = false;
        char[] chars = s.toCharArray();
        while(left<right){
            if(chars[left]!=chars[right]){
                if(!isDelete&& chars[left+1]==chars[right])
                {
                    left++;
                    isDelete = true;
                }else if(!isDelete && chars[left]==chars[right-1]){
                    right--;
                    isDelete = true;
                }else {
                    return false;
                }
            }
            left++;
            right--;
        }
        return true;
    }
}
