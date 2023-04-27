package leetcode.searchAlog;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author wang
 * @create 2023-2023-21-19:06
 */
public class MaxIland {
    public static void main(String[] args) {
//        int[][] height = new int[][]{{1,0,0,1,1},
//                                     {1,0,0,1,0},
//                                     {1,1,1,1,1},
//                                     {1,1,1,0,1},
//                                     {0,0,0,1,0}};
        int height[][] = {{1,0,0},{0,1,0},{1,1,0}};
        MaxIland maxIland = new MaxIland();
        maxIland.largestIsland(height);



    }
    int maxSquare = 0;
    int maxChangeCount = 0;
    int[][] moveIndexs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    public int largestIsland(int[][] grid) {
        if(grid==null || grid.length==0 || grid[0].length ==0){
            return 0;
        }
        boolean isChange = false;
        for(int i = 0;i<grid.length;i++){
            for(int j = 0;j<grid[i].length;j++){
                if(grid[i][j]==1){
                    isChange = true;
//                    maxSquare = Math.max(maxSquare,dfs(grid,i,j,0)+maxChangeCount);
                    maxSquare = Math.max(maxSquare,bfs(grid,i,j)+maxChangeCount);
                     modifyReback(grid);
                }
            }
        }
        if(!isChange){
            return 1;
        }
        return maxSquare;
    }


    public int bfs(int[][] grid,int row,int col){
        if(row<0 || row>=grid.length || col<0 || col>=grid[row].length || grid[row][col]==0 || grid[row][col]==2){
            return 0;
        }
        Queue<int[]> queue = new LinkedList<>();
        Queue<int[]> connectionNode = new LinkedList<>();
        queue.add(new int[]{row,col});
        int count = 1;
        int n = grid.length;
        int m = grid[0].length;
        grid[row][col] = 2;
        while(!queue.isEmpty()){
            int[] index = queue.poll();
            row = index[0];
            col = index[1];

            for(int[] indexs:moveIndexs){
                int nextRow = row+indexs[0];
                int nextCol = col+indexs[1];
                if(!isOutOfFlow(nextRow,nextCol,n,m) && grid[nextRow][nextCol]==1){
                    queue.add(new int[]{nextRow,nextCol});
                    grid[nextRow][nextCol] = 2;
                    count++;
                }else if(!isOutOfFlow(nextRow,nextCol,n,m)&& grid[nextRow][nextCol]==0){
                    connectionNode.add(new int[]{nextRow,nextCol});
                }
            }
        }
        int changeCount = 0;
        while(!connectionNode.isEmpty()){
            int[] index = connectionNode.poll();
            int changeRow = index[0];
            int changeCol = index[1];
            grid[changeRow][changeCol] = 1;
            changeCount = Math.max(changeCount,searchConnection(grid,changeRow,changeCol));
            grid[changeRow][changeCol] = 0;
            modifyReback(grid);
        }
        return  count+changeCount;

    }

    public int searchConnection(int[][] grid,int row,int col){
        if(row<0 || row>=grid.length || col<0 || col>=grid[row].length || grid[row][col]==0 || grid[row][col]==2 || grid[row][col]==3){
            return 0;
        }
        int count = 1;
        grid[row][col] = 3;
        count+=searchConnection(grid,row-1, col);
        count+=searchConnection(grid,row+1,col);
        count+=searchConnection(grid,row,col-1);
        count+=searchConnection(grid,row,col+1);

        return count;
    }

    public int dfs(int[][] grid,int row,int col,int change){
        if(row<0 || row>=grid.length || col<0 || col>=grid[row].length || grid[row][col]==0 || grid[row][col]==2){
            return 0;
        }
        int count  = 1;
        grid[row][col] = 0;

        // if(change==0){
        //     grid[row][col] =2;
        // }else{
        //     grid[row][col] = 3;
        // }

        count+=dfs(grid,row-1,col,change);


        count+=dfs(grid,row+1,col,change);


        count+=dfs(grid,row,col-1,change);


        count+=dfs(grid,row,col+1,change);

        if(!isOutOfFlow(row-1,col,grid.length,grid[row].length) && grid[row-1][col]==0 && change==0){
            grid[row-1][col] = 1;
            maxChangeCount = Math.max(maxChangeCount,dfs(grid,row-1,col,1));
            grid[row-1][col] = 0;
        }

        if(!isOutOfFlow(row+1,col,grid.length,grid[row].length) && grid[row+1][col]==0 && change==0){
            grid[row+1][col] =1;
            maxChangeCount = Math.max(maxChangeCount,dfs(grid,row+1,col,1));
            grid[row+1][col] =0;
        }

        if(!isOutOfFlow(row,col-1,grid.length,grid[row].length) && grid[row][col-1]==0 && change==0){
            grid[row][col-1] =1;
            maxChangeCount = Math.max(maxChangeCount,dfs(grid,row,col-1,1));
            grid[row][col-1] =0;

        }
        if(!isOutOfFlow(row,col+1,grid.length,grid[row].length) && grid[row][col+1]==0 && change==0){
            grid[row][col+1] =1;
            maxChangeCount = Math.max(maxChangeCount,dfs(grid,row,col+1,1));
            grid[row][col+1] =0;

        }
        if(change==1){
            grid[row][col]=1;
        }
        return count;

    }

    public void modifyReback(int[][] grid){
        for(int i = 0;i<grid.length;i++){
            for(int j = 0;j<grid[i].length;j++){
                if(grid[i][j]==3){
                    grid[i][j] = 1;
                }
            }
        }
    }

    public boolean isOutOfFlow(int row,int col,int endRow,int endCol){
        if(row<0 || col<0 || row>=endRow || col>=endCol){
            return true;
        }
        return false;
    }
}
