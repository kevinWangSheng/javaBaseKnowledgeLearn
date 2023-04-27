package leetcode.bit;

import java.util.Arrays;

/**
 * @author wang
 * @create 2023-2023-01-12:01
 */
public class shiftTest {
    public static void main(String[] args) {
        System.out.println(-3<<32);

        int a = 10;
        int b = 20;
        a = a^b;
        b = a^b; //b = a;
        a^=b;
        System.out.println("a="+a);
        System.out.println("b="+b);

        String str[] = new String[] {"dictionary","123"};

        Arrays.sort(str,(d1, d2)->{
            if(d2.length()>d1.length()){
                return 1;
            }else if(d1.length()>d2.length()){
                return -1;
            }else{
                if(d1.charAt(0)<d2.charAt(0)){
                    return 1;
                }else{
                    return -1;
                }
            }
        });
    }
}
