package leetcode.searchAlog;

/**
 * @author wang
 * @create 2023-2023-19-16:35
 */
public class SqrtNFindNumber {

    public static void main(String[] args) {
        SqrtNFindNumber sqrtNFindNumber = new SqrtNFindNumber();
        sqrtNFindNumber.numSquares(12);
    }

    public int numSquares(int n) {
        double extract = Math.sqrt(n);
        int extract1 = (int)((int)n/extract);
        if (extract1==extract){
            return 1;
        }

        int count = 1;
        int sqrt[] = new int[extract1];
        for(int i = 0;i<sqrt.length;i++){
            sqrt[i] = (i+1)*(i+1);

        }
        return 1;

    }
}
