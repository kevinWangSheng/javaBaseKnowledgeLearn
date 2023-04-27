package leetcode.graph;

import java.util.Stack;

/**
 * @author wang
 * @create 2023-2023-22-13:51
 */
public class BitpartiteGraph {
    public static void main(String[] args) {
        int [][] graph = new int[4][4];
        graph[0][1] = 1;
        graph[0][3] = 1;
        graph[1][0] = 1;
        graph[1][2] = 1;
        graph[2][1] = 1;
        graph[2][3] = 1;
        graph[3][0] = 1;
        graph[3][2] = 1;

        isBipartite(graph);
    }

    public static boolean isBipartite(int[][] graph) {
        if(graph == null){
            return true;
        }
        int color[] = new int[graph.length];
        boolean [] isVisit = new boolean[graph.length];
        boolean changeColor = true;
        Stack<Integer> stack = new Stack();
        stack.push(0);
        isVisit[0] = true;
        color[0] = 2;
        while(!stack.empty()){
            int v = stack.pop();
            for(int i = 0;i<graph[v].length;i++){
                if(graph[v][i]!=0){
                    if(!isVisit[i]){
                        isVisit[i] = true;
                        stack.push(i);
                    }
                    // 判断该节点的颜色是否被设置
                    if(color[i]==0){
                        // 一共设置两种颜色，相邻节点至今需要设置不同颜色
                        if(changeColor){
                            color[i] = 1;
                        }else{
                            color[i] = 2;
                        }
                        // 如果已经设置了该节点的颜色，那么就判断该节点的颜色和他相邻节点的颜色是否一致，如果一致直接返回false
                    }else if(color[i] == color[v]){
                        return false;
                    }
                }
            }
            changeColor = !changeColor;
        }
        return true;
    }
}
