package leetcode.string;

/**
 * @author wang
 * @create 2023-2023-20-11:08
 */
public class CycicShift {
    public static void main(String[] args) {
        String s1 ="abcd123";
        int k = 3;

        String substring = s1.substring(s1.length() - k);
        System.out.println(substring);
        String pre = s1.substring(0, s1.length() - k);

        pre = new StringBuffer(pre).reverse().toString();
        substring = new StringBuffer(substring).reverse().toString();

        s1 = new StringBuffer(substring+pre).reverse().toString();
        System.out.println(s1);

        char[] charS1 = s1.toCharArray();
        int length = charS1.length;
        for(int i = 0;i<k;i++){
            char temp = charS1[length-i-1];
            int j = 0;
            for(j = length-i-1;j>=k;j-=k){
                charS1[j] = charS1[j-k];
            }
            charS1[j] = temp;
        }
        System.out.println(new String(charS1));
    }
}
