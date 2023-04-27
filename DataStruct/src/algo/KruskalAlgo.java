package algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wang
 * @create 2023-2023-12-21:56
 */
public class KruskalAlgo {
    private int verNum;
    private char[] vertexs;
    private int[][] matrix;
    private  int edgeNum;

    public int getEdgeNum() {
        return edgeNum;
    }

    public void setEdgeNum(int edgeNum) {
        this.edgeNum = edgeNum;
    }
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //克鲁斯卡尔算法的邻接矩阵
        int matrix[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {   0,  12, INF, INF, INF,  16,  14},
                /*B*/ {  12,   0,  10, INF, INF,   7, INF},
                /*C*/ { INF,  10,   0,   3,   5,   6, INF},
                /*D*/ { INF, INF,   3,   0,   4, INF, INF},
                /*E*/ { INF, INF,   5,   4,   0,   2,   8},
                /*F*/ {  16,   7,   6, INF,   2,   0,   9},
                /*G*/ {  14, INF, INF, INF,   8,   9,   0}};

        KruskalAlgo kruskalAlgo = new KruskalAlgo(vertexs,matrix);

        Edge[] edges = kruskalAlgo.getEdges();
        System.out.println(Arrays.toString(edges));
        System.out.println(edges.length);
        kruskalAlgo.sortEdge(edges);

        System.out.println(Arrays.toString(edges));

        kruskalAlgo.kruskal();
    }

    public KruskalAlgo(char[] vertexs, int[][] matrix) {
        verNum = vertexs.length;
        this.vertexs = new char[verNum];
        this.matrix = new int[verNum][verNum];

        this.vertexs = Arrays.copyOf(vertexs,vertexs.length);
        for(int i = 0; i< verNum; i++){
            this.matrix[i] = Arrays.copyOf(matrix[i],matrix[i].length);
        }

        for(int i = 0;i<matrix.length;i++){
            for(int j = 0;j<matrix[i].length;j++){
                if(matrix[i][j]!=0 && matrix[i][j]!=INF){
                    edgeNum++;
                }
            }
        }
    }

    public void sortEdge(Edge[] edges){
        sortEdge(edges,0,edges.length-1);
    }

    public void kruskal(){
        Edge[] edges = getEdges();
        int index = 0;
        int [] edgesNum = new int[edgeNum];
        Edge[] rest = new Edge[edgeNum];

        sortEdge(edges);

        for(int i = 0;i<edges.length;i++){
            int positionStart = getEdgePosition(edges[i].getStart());
            int positionEnd = getEdgePosition(edges[i].getEnd());

            int endStart = getEnd(edgesNum, positionStart);
            int endEnd = getEnd(edgesNum, positionEnd);
            if(endEnd != endStart){
                edgesNum[endStart] = endEnd;
                rest[index++] = edges[i];
            }
        }
        for(int i = 0;i<rest.length;i++){
            if(rest[i]!=null){
                System.out.println(rest[i]);
            }
        }

        List<Integer> list = new ArrayList<>();
        list.sort((a,b)->{
            return -1;
        });
    }

    private void sortEdge(Edge[] edges, int low, int high) {
        if(edges == null ||edges.length==0){
            return;
        }
        if(low>=high){
            return;
        }
        int mid = low+(high-low)/2;
        Edge privot = edges[mid];

        int i = low;
        int j = high;
        while(i<=j){
            while(edges[i].getWeight()<privot.getWeight()){
                i++;
            }
            while(edges[j].getWeight()>privot.getWeight()){
                j--;
            }
            if(i<=j){
                Edge temp = edges[i];
                edges[i] = edges[j];
                edges[j] = temp;
                i++;
                j--;
            }
        }
        if(i<high){
            sortEdge(edges,i,high);
        }
        if(j>low){
            sortEdge(edges,low,j);
        }
    }

    public int getEdgePosition(char vertex){
        for(int i = 0;i<vertexs.length;i++){
            if(vertex == vertexs[i]){
                return i;
            }
        }
        return -1;
    }

    public Edge[] getEdges(){
        int index = 0;

        Edge[] edges = new Edge[edgeNum];

        for(int i = 0;i<matrix.length;i++){
            for(int j = 0;j<matrix.length;j++){
                if(matrix[i][j]!=INF && matrix[i][j]!=0){
                    edges[index++] = new Edge(vertexs[i],vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    public int getEnd(int [] ends,int i){
        while(ends[i]!=0){
            i = ends[i];
        }
        return i;
    }
}


class Edge{
    private char start;
    private char end;
    private int weight;

    @Override
    public String toString() {
        return "Edge<" +
                  start +
                "," + end +
                ">= weight=" + weight +
                '}';
    }

    public char getStart() {
        return start;
    }

    public void setStart(char start) {
        this.start = start;
    }

    public char getEnd() {
        return end;
    }

    public void setEnd(char end) {
        this.end = end;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Edge(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}