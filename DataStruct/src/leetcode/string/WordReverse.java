package leetcode.string;

import java.util.Arrays;

/**
 * @author wang
 * @create 2023-2023-20-11:36
 */
public class WordReverse {
    public static void main(String[] args) {
        String s = "I am a student";

        String[] strs = s.split(" ");

        System.out.println(Arrays.toString(strs));

        for(int i = 0;i<strs.length;i++){
           strs[i] =  new StringBuffer(strs[i]).reverse().toString();
        }
        s = "";
        for(int i = 0;i<strs.length;i++){
            if(i== strs.length-1){
               s+=strs[i];
            }else{
                s+=strs[i]+" ";
            }
        }

        s =   new StringBuffer(s).reverse().toString();
        System.out.println(s);
    }
}
