package leetcode.towPoint;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wang
 * @create 2023-2023-07-11:19
 */
public class ContainsString {
    public static void main(String[] args) {
        String s = "wordgoodgoodgoodbestword";
        String [] dictionary = new String[]{"word","good","best","good"};
        List<String> strList = new ArrayList<>();
        for(String str:dictionary){
            strList.add(str);
        }
        findLongestWord(s,strList);
    }

    public static String findLongestWord(String s, List<String> dictionary) {
        String res = "";
        for(String str:dictionary){
            int sIndex = 0;
            int strIndex = 0;
            while(sIndex<s.length() && strIndex<str.length()){
                if(s.charAt(sIndex) == str.charAt(strIndex)){
                    strIndex++;
                    sIndex++;
                }else{
                    sIndex++;
                }
            }
            if(strIndex>=str.length() && str.length()>=res.length()){
                if(str.length()>res.length()){
                    res = str;
                }else{
                    for(int i = 0;i<str.length();i++){
                        if(str.charAt(i)<res.charAt(i)){
                            res = str;
                            break;
                        }else if(str.charAt(i)>res.charAt(i)){
                            break;
                        }
                    }
                }
            }
        }
        return res;
    }
}
