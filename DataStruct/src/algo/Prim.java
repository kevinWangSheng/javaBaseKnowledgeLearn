package algo;

import java.util.ArrayList;

/**
 * @author wang
 * @create 2023-2023-12-19:14
 */
public class Prim {
    private int vertexNum;
    private int[][] weights;
    private ArrayList<String> vertex;
    boolean[] isVisited;

    public static void main(String[] args) {
        Prim prim = new Prim(5);
        prim.vertex.add("A");
        prim.vertex.add("B");
        prim.vertex.add("C");
        prim.vertex.add("D");
        prim.vertex.add("E");
        prim.initWeight();

        prim.add(0,3,1);
        prim.add(0,1,5);
        prim.add(0,4,3);
        prim.add(1,4,2);
        prim.add(2,1,4);
        prim.add(3,2,5);
        prim.add(3,4,8);


        prim.prim();

    }

    public Prim(int vertexNum) {
        this.vertexNum = vertexNum;
        weights = new int[vertexNum][vertexNum];
        vertex = new ArrayList<>(vertexNum);
        isVisited = new boolean[vertexNum];
    }

    public void add(int v1,int v2,int weight){
        this.weights[v1][v2] = weight;
    }
    public void initWeight(){
        for(int i = 0;i<weights.length;i++){
            for(int j = 0;j<weights[i].length;j++){
                weights[i][j] = -1;
            }
        }
    }

    public void prim(){
        int v1 = -1;
        int v2 = -1;
        int minWeight = -1;
        int count = 1;
        for(int k = 0;k<vertexNum;k++) {
            boolean flag = false;
            minWeight = -1;
            for (int i = 0; i < vertexNum; i++) {
                for (int j = 0; j < vertexNum; j++) {
                    if (!isVisited[j] && weights[i][j] > 0 && (minWeight > weights[i][j] || minWeight == -1)) {
                        v1 = i;
                        v2 = j;
                        minWeight = weights[i][j];
                        flag = true;
                    }
//                    System.out.println(count++);
                }
            }
            if(minWeight!=-1){
                isVisited[v2] = true;
                System.out.println("from the "+vertex.get(v1)+" to the "+vertex.get(v2)+" and the weight is:"+weights[v1][v2]);
            }
            if(!flag){
                break;
            }
        }
    }
}
