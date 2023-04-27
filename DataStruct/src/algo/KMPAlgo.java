package algo;

/**
 * @author wang
 * @create 2023-2023-11-20:47
 */
public class KMPAlgo {

    public static void main(String[] args) {
        KMPAlgo kmpAlgo = new KMPAlgo();

        String str1= "abcfaaf wadbqdasd abcd abcde aefaef";
        String str2 = "abcde";

        System.out.println(kmpAlgo.kmp(str1,str2));
    }
    public static int[] getNext(String str){
        int k = -1;
        int[] next = new int[str.length()];

        int j = 0;
        next[0] = -1;

        while(j<str.length()-1){
            if(k==-1 || str.charAt(j)==str.charAt(k)){
                next[++j] = ++k;
            }else{
                k = next[k];
            }
        }
        return next;
    }

    public int kmp(String str1,String str2){
        int[] next = getNext(str2);
        int i = 0;
        int j = 0;
       while(i<str1.length() && j<str2.length()){
           if(j==-1 || str1.charAt(i) == str2.charAt(j)){
               j++;
               i++;
           }
           else{
               j = next[j];
           }
       }

       if(j == str2.length()){
           return i-j;
       }else {
           return -1;
       }
    }
}
