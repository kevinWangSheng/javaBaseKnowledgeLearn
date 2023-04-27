package leetcode.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wang
 * @create 2023-2023-24-14:05
 */
public class GraphyTest {
    public static void main(String[] args) {
        int [][] edges = new int[][]{{1,2}, {2,3}, {3,4}, {1,4}, {1,5}};
        Solution solution = new Solution();
        solution.findRedundantConnection1(edges);
    }
}


class Solution {
    boolean [] isVisited;
    int []edge = new int[2];
    int prev;
    List<List<Integer>> list;
    boolean vaild;
    boolean change = false;
    public int[] findRedundantConnection(int[][] edges) {
        list = new ArrayList();
        int max = 0;
        vaild = true;
        for(int first[] :edges){
            for(int num:first){
                if(max<num){
                    max = num;
                }
            }
        }
        isVisited = new boolean[max];
        for(int i = 0;i<max;i++){
            list.add(new ArrayList());
        }

//          set the vertex and edge
        for(int[] info:edges){
            list.get(info[0]-1).add(info[1]-1);
            list.get(info[1]-1).add(info[0]-1);
        }

        for(int i = 0;i<max;i++){
            if(!isVisited[i]){
                dfs(i);
            }
        }
        return edge;

    }

    public int[] findRedundantConnection1(int[][] edges) {
        list = new ArrayList();
        int max = 0;
        vaild = true;
        for(int first[] :edges){
            for(int num:first){
                if(max<num){
                    max = num;
                }
            }
        }
        isVisited = new boolean[max];
        for(int []info : edges){
            if(!isVisited[info[0]-1] && !isVisited[info[1]-1]){
                edge[0] = info[0];
                edge[1] = info[1];
            }else{
                isVisited[info[0]-1] = true;
                isVisited[info[1]-1] = true;
            }
        }
        return edge;

    }

    public void dfs(int v){
        isVisited[v] = true;
        for(int u:list.get(v)){
            if(!isVisited[u]){
                list.get(u).remove(new Integer(v));
                dfs(u-1);
                if(!vaild){
                    if(!change){
                        edge[0] = v+1;
                        edge[1] = u+1;
                        change = true;
                    }
                    return ;
                }
            }else if(isVisited[u]){
                vaild = false;
                return;
            }
        }
    }
}
