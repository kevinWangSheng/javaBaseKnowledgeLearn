package stack.eightQueue;

/**
 * @author wang
 * @create 2023-2023-27-16:55
 */
public class EightQueueProblem {
    int max = 8;
    private int [] queen = new int[max];
    private int[] tempQueue = new int[max];
    private static int count = 0;
    private static int invokeCount = 0;

    public boolean checkQueen(int n){
        for(int i = 0;i<n;i++){
            invokeCount++;
            if(queen[i] == tempQueue[n] || Math.abs(n-i)==Math.abs(tempQueue[n]-queen[i])){
                return false;
            }
        }
        return true;

    }

    public void check(int n){
        if(n==max){
            for(int i = 0;i<max;i++){
                System.out.printf("%d ",queen[i]);
            }
            System.out.println();
            count++;
            return;
        }
        // froeach the row n's queen's col
        for(int i = 0;i<max;i++){
            tempQueue[n] = i;
            if(checkQueen(n)){
                queen[n] = i;
                check(n+1);
            }
        }
    }

    public static void main(String[] args) {
        EightQueueProblem queenProblem = new EightQueueProblem();

        queenProblem.check(0);
        System.out.println("the total's count is"+count);
        System.out.println("the invoke count is:"+invokeCount);
    }

}
