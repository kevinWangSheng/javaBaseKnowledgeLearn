package graph;

import java.util.*;

/**
 * @author wang
 * @create 2023-2023-10-12:39
 */
public class Graph {
    List<String> vertex = new ArrayList<String>();
    int [][] edges;
    int vertexSize;


    public Graph(int vertexSize) {
        this.vertexSize = vertexSize;
        edges = new int[vertexSize][vertexSize];
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        String[] vertexs = {"A","B","C","D","E","F"};

        for(int i = 0;i<vertexs.length;i++){
            graph.addVertex(vertexs[i]);
        }

        graph.addEdges(0,4,1);
        graph.addEdges(0,3,1);
        graph.addEdges(1,4,1);
        graph.addEdges(1,2,1);
        graph.addEdges(2,3,1);
        graph.addEdges(3,4,1);
        graph.addEdges(4,5,1);

        System.out.println(Arrays.deepToString(graph.edges));

        boolean[] isvisited = new boolean[vertexs.length];
        graph.dfs(isvisited);
        System.out.println();
        graph.bfs(new boolean[6]);
    }

    public void addVertex(String elemt){
        vertex.add(elemt);
        vertexSize++;
    }

    public void addEdges(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
    }

    public int getFirstNeighbor(int index){
        for(int i = 0;i<vertex.size();i++){
            if(edges[index][i] >0){
                return i;
            }
        }
        return -1;
    }

    public int getNextNeighbor(int v1,int v2){
        for(int i = v2+1;i<vertex.size();i++){
            if(edges[v1][i]>0){
                return i;
            }
        }
        return -1;
    }

    public void dfs(boolean [] isVisited,int v1){
        int nextVer = getFirstNeighbor(v1);

        while(nextVer!=-1){
            if(!isVisited[nextVer]){
                System.out.print("->"+vertex.get(nextVer));
                isVisited[nextVer] = true;
                dfs(isVisited,nextVer);
            }
            nextVer = getNextNeighbor(v1,nextVer);
        }
    }

    public void dfs(boolean []isvisited){
        isvisited[0] = true;
        System.out.print(vertex.get(0));
        dfs(isvisited,0);
    }

    public void bfs(boolean[] isVisited){
        LinkedList<String> queue = new LinkedList<>();

        int v1;
        int v2;

        queue.add(vertex.get(0));
        System.out.print(vertex.get(0));
        isVisited[0] = true;

        while(!queue.isEmpty()){
            String elemt = queue.pop();
            v1 = vertex.indexOf(elemt);
            v2 = getFirstNeighbor(v1);
            while(v2!=-1 ){
                if(!isVisited[v2]) {
                    isVisited[v2] = true;
                    System.out.print("->" + vertex.get(v2));
                    queue.addLast(vertex.get(v2));
                }
                v2 = getNextNeighbor(v1,v2);
            }
        }
    }


}
