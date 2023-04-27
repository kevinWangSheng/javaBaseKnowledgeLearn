package algo;

/**
 * @author wang
 * @create 2023-2023-11-13:36
 */
public class DynamicPlanning {
    public static void main(String[] args) {
        int []w = {1,4,3};
        int []val ={500,3000,2500};
        int weight = 4;

        int[][] v= new int[w.length+1][weight+1];
        int[][] path = new int[w.length+1][weight+1];

        for(int i = 1;i<v.length;i++){
            for(int j = 1;j<v[i].length;j++){
                if(w[i-1]>j){
                    v[i][j] = v[i-1][j];
                }else{
                    if(v[i-1][j]>=(val[i-1]+v[i-1][j-w[i-1]])){
                        v[i][j] = v[i-1][j];
                    }else{
                        v[i][j] = val[i-1]+v[i-1][j-w[i-1]];
                        path[i][j] =1;
                    }
                }
            }
        }

        for(int i = 0;i<v.length;i++)
        {
            for(int j = 0;j<v[i].length;j++){
                System.out.printf("%d ",v[i][j]);
            }
            System.out.println();
        }

        int i = path.length-1;
        int j = path[0].length-1;

        while(i>0&&j>0){
            if(path[i][j]==1){
                System.out.print("put the "+i+" productor to it");
                j-=w[i-1];
                System.out.println();
            }
            i--;
        }
    }
}
