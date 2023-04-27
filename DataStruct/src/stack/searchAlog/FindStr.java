package stack.searchAlog;

import java.util.Scanner;

/**
 * @author wang
 * @create 2023-2023-30-18:29
 */
public class FindStr {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        for(int i = 0;i<number;i++){
            String str = input.next();
            if(judge(str)){
                System.out.println("Y");
            }else{
                System.out.println("N");
            }
        }
    }

    public static boolean judge(String str){
        if(str==null||str.length()==0)
        {
            return false;
        }
        char ch = ' ';
        for(int i = 0;i<str.length()-1;i++){
            ch = str.charAt(i);
            // 大写
            if(ch>=65 && ch<=90){
                if(!(str.charAt(i+1)==(ch-1) || (str.charAt(i+1)==ch+32))){
                    return false;
                }
            }else if(ch>=97 && ch<=122){
                if(!((str.charAt(i+1)==(ch-32))|| (str.charAt(i+1)==ch+1))){
                    return false;
                }
            }
        }
        return true;
    }
}
