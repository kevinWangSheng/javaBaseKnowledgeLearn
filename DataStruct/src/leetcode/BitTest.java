package leetcode;

/**
 * @author wang
 * @create 2023-2023-30-13:23
 */
public class BitTest {
    public static void main(String[] args) {
        int x = 100;
        int y = 1000;
        System.out.println(x^y^10^x^y^y^10);
        System.out.println(Integer.toBinaryString(3));
        System.out.println(Integer.toBinaryString(-3).length());


        System.out.println(Integer.parseUnsignedInt("00000000000001111100011", 2));
        BitTest bitTest = new BitTest();
        int n = Integer.parseUnsignedInt("11111111111111111111111111111101", 2);
        System.out.println(bitTest.reverseBits(n));

    }

    public int reverseBits(int n) {
        String strNum = Integer.toBinaryString(n);
        if(strNum.length()<32){
            for(int i = strNum.length();i<32;i++){
                strNum = "0"+strNum;
            }
        }

        StringBuilder strBuilder = new StringBuilder(strNum);
        String str = strBuilder.reverse().toString();
        System.out.println(str);
        int res = Integer.parseUnsignedInt(strBuilder.toString(), 2);
        return res;
    }
}
