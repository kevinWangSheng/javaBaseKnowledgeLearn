package stack.pat;

import java.util.Scanner;

/**
 * @author wang
 * @create 2023-2023-30-18:37
 */
public class MatrixSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int x = sc.nextInt();

        int arr[][] = new int[n][n];

        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                arr[i][j] = sc.nextInt();
            }
        }
        int count = 0;
        int tempK = 0;
        for(int i = 0;i<n;i++){
            if((i+1)%2==0) {
                if(count%2==0) {
                    tempK = 1;
                }else {
                    tempK = k;
                }
                for (int j = n - 1; j - tempK >= 0; j--) {
                    arr[j][i] = arr[j - tempK][i];
                }
                for(int j = 0;j<tempK;j++){
                    arr[j][i] = x;
                }
                count++;
            }
        }

        for(int i = 0;i<n;i++)
        {
            for(int j = 0;j<n;j++){
                System.out.printf("%d ",arr[i][j]);
            }
            System.out.println();
        }

        for(int i = 0;i<n;i++){
            int sum = 0;
            for(int j = 0;j<n;j++){
                sum+=arr[i][j];
            }
            if(i!=n-1) {
                System.out.printf("%d ", sum);
            }else{
                System.out.printf("%d",sum);
            }
        }
    }
}
