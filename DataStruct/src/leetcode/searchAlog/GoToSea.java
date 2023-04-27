package leetcode.searchAlog;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wang
 * @create 2023-2023-21-15:36
 */
public class GoToSea {

    public static void main(String[] args) {
        GoToSea go = new GoToSea();
//        int[][] height = new int[][]{{0,0,0,0,0,0,0},
//                                    {0,1,1,1,1,0,0},
//                                    {0,1,0,0,1,0,0},
//                                    {1,0,1,0,1,0,0},
//                                    {0,1,0,0,1,0,0},
//                                    {0,1,0,0,1,0,0},
//                                    {0,1,1,1,1,0,0}};
//        go.pacificAtlantic(height);
        System.out.println(new int[] {1,1,1,0,1,0,0,1,0,1,0,1,0,0,1,0,1,1,0,1,1,1,1,1,0,1,1,1,0,1,1,0,0,0,0,1,1,1,0,1,0,1,0,0,0,1,1,0,0,0,1,0,0,1,0,1,1,0,1,0,1,0,0,1,1,0,0,0,1,0,0,1,1,1,1,0,0,1,0,1,0,0,1,1,0,0,0,1,0,0,0,0,0,1,1,0,1,1,1,0,0,0,1,1,0,1,1,0,0,0,1,1,1,1,0,0,1,0,1,0,0,0,0,0,0,1,1,0,1,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,1,0,1,0,1,0,0,0,0,1,0,1,1,1,0,1,0,0,0,1,1,1,1,1,1,1,0,1,0,0,1,1,0,0,0,1,1,1,1,1,0,0,0,0,1,1,0,1,1,0,1,0,1,1,0,1,1,1,0,1,1,0,0,1,0,1,0,0,0,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1,0,1,1,1,1,1,1,1,1,0,1,0,1,0,1,1,1,0,1,0,1,1,1,0,1,1,1,0,1,0,0,0,0,0,1,1,0,1,1,1,0,0,1,1,0,0,1,0,0,1,1,1,0,1,1,1,0,0,1,1,0,1,1,0,1,1,1,1,0,0,0,1,1,1,0,0,1,1,0,1,1,1,0,0,1,1,0,0,1,0,1,0,0,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,0,1,1,1,1,1,0,0,1,0,1,0,1,0,1,1,0,0,0,0,1,1,0,1,0,1,1,0,0,1,0,0,1,1,1,0,1,1,0,1,0,1,1,1,0,0,1,0,1,1,0,1,0,0,0,0,0,0,0,0,1,0,0,0,1,1,1,0,0,1,0,0,1,0,0,0,0,0,1,1,0,0,1,0,0,1,1,1,1,0,0,0,1,0,1,0,1,1,0,1,1,0,1,0,1,1,0,1,0,1,0,0,0,0,0,1,1,0,1,1,0,1,1,0,1,0,1,1,1,1,0,0,1,0,1,1,1,1,1,1,0,0,1,1}.length);
    }

    boolean pacific;
    boolean atlantic;
    boolean[][] isVisited = null;
    List<List<Integer>> result = null;
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        if(heights==null || heights.length==0 || heights[0].length==0){
            return null;
        }
        isVisited = new boolean[heights.length][heights[0].length];
        result = new ArrayList();
        for(int i = 0;i<heights.length;i++){
            for(int j = 0;j<heights[i].length;j++){
                if(!isVisited[i][j]){
                    pacific =false;
                    atlantic = false;
                    canGoToSea(heights,i,j);
                }
            }
        }
        return result;
    }

    public void canGoToSea(int[][] heights,int row,int col){
        if(isAtlantic(row,col,heights.length,heights[0].length)){
            atlantic = true;
        }
        if(isPacific(row,col,heights[0].length)){
            pacific = true;
        }
        if(isOutOfFlow(row,col,heights.length,heights[0].length)){
            return ;
        }
        isVisited[row][col] = true;
        if((row-1<0)|| (heights[row][col]>=heights[row-1][col]))
            canGoToSea(heights,row-1,col);
        if((row+1>=heights.length) ||(heights[row][col]>=heights[row+1][col]))
            canGoToSea(heights,row+1,col);
        if((col-1<0)||(heights[row][col]>=heights[row][col-1]))
            canGoToSea(heights,row,col-1);
        if((col+1>=heights[0].length)||(heights[row][col]>=heights[row][col+1]))
            canGoToSea(heights,row,col+1);
        if(atlantic&&pacific){
            ArrayList node = new ArrayList();
            node.add(row);
            node.add(col);
            result.add(node);
        }

    }

    public boolean isPacific(int row,int col,int endCol){
        if((row<0 && col<endCol) ||(col<0 && row<0)){
            return true;
        }
        return false;
    }
    public boolean isAtlantic(int row,int col,int endRow,int endCol){
        if((row>=endRow && col>0)||(row>=endRow && col>=endCol)){
            return true;
        }
        return false;
    }

    public boolean isOutOfFlow(int row,int col,int endRow,int endCol){
        if(row<0 || col<0 || row>=endRow || col>=endCol){
            return true;
        }
        return false;
    }
}
