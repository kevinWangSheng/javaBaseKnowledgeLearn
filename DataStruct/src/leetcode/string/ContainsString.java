package leetcode.string;

import algo.KMPAlgo;

/**
 * @author wang
 * @create 2023-2023-20-10:32
 */
public class ContainsString {
    public static void main(String[] args) {
        String s1 = "AABCD";
        s1+=s1;
        String s2 = "CDAA";
        System.out.println(s1);
        KMPAlgo kmpAlgo = new KMPAlgo();

        int kmp = kmpAlgo.kmp(s1, s2);
        if(kmp!=-1){
            System.out.println("true");
        }else{
            System.out.println(false);
        }
    }
}
