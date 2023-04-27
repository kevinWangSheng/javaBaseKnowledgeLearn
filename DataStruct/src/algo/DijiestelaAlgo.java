package algo;

import java.util.Arrays;

/**
 * @author wang
 * @create 2023-2023-13-19:40
 */
public class DijiestelaAlgo {
    private char[] vertex;
    private int[][] martix;
    private boolean[] isVisited;
    private int [] des;
    private static final int INF =65535;
    public static void main(String[] args) {

        char[] ver = {'A','B','C','D','E','F','G'};
        int [][] matrx = new int[ver.length][ver.length];
        for(int i = 0;i<matrx.length;i++){
            for(int j = 0;j<matrx.length;j++){
                if(i!=j){
                    matrx[i][j] = INF;
                }else{
                    matrx[i][j] = 0;
                }
            }
        }

        add(0,6, 2,matrx);
        add(0,1,5,matrx);
        add(0,2,7,matrx);
        add(1,6,3, matrx);
        add(1,3,9,matrx);
        add(2,4,8,matrx);
        add(3,5,4,matrx);
        add(4,5,5,matrx);
        add(4,6,4,matrx);
        add(5,6,6, matrx);

        DijiestelaAlgo dijies = new DijiestelaAlgo(ver,matrx);
//        System.out.println(dijies.getVertexIndex('G'));
//        dijies.djiestala('G');
        dijies.flody();
        for(int i = 0;i<dijies.vertex.length;i++){
            System.out.println(Arrays.toString(dijies.martix[i]));
        }
    }

    public static void add(int i,int j,int weight,int [][]matr)
    {
        matr[i][j] = weight;
        matr[j][i] = weight;

    }

    public DijiestelaAlgo(char[] vertex,int[][]martix){
        this.vertex = vertex;
        this.martix = martix;

        isVisited = new boolean[vertex.length];
        des = new int[vertex.length];

        for(int i = 0;i<vertex.length;i++){
            des[i] = INF;
        }
//        for(int i = 0;i<martix.length;i++){
//            System.out.println(Arrays.toString(martix[i]));
//        }
    }

    public void djiestala(int index){
        isVisited[index] = true;
        int count = 1;
        des[index] = 0;
        for(int i = 0;i<vertex.length;i++){
            des[i] = martix[index][i];
        }
        while(count!=vertex.length){
            int min = INF;
            int minIndex = 0;
            boolean flag = false;
            for(int i =0;i<vertex.length;i++){
                if(!isVisited[i]&& des[i]<min){
                    min = des[i];
                    minIndex = i;
                    flag = true;
                }
            }
            if(flag) {
                isVisited[minIndex] = true;
                System.out.println("get the min index:"+vertex[minIndex]);
                count++;
            }


            for(int i = 0;i<vertex.length;i++){
                if(!isVisited[i] && martix[minIndex][i]!=INF && (martix[minIndex][i]+des[minIndex]<des[i])){
                    des[i] = martix[minIndex][i]+des[minIndex];
                }
            }

        }
    }

    public int  getVertexIndex(char elem){
        int low = 0;
        int high = vertex.length-1;
        int mid = low+(high-low)/2;
        while(low<=high){
            if(vertex[mid]>elem){
                high = mid-1;
            }
            else if(vertex[mid]<elem){
                low = mid+1;
            }else{
                return mid;
            }
            mid = low+(high-low)/2;
        }
        return -1;
    }

    public void djiestala(char elem){
        int index = getVertexIndex(elem);
        if(index!=-1){
            djiestala(index);
        }
        System.out.println(Arrays.toString(des));
    }

    public void flody(){
        for (int k = 0; k < vertex.length; k++) {
            for (int i = 0; i < vertex.length; i++) {
                for (int j = 0; j < vertex.length; j++) {
                    martix[i][j] = Math.min(martix[i][j], martix[i][k] + martix[k][j]);
                }
            }
        }
    }


}
