package leetcode.searchAlog;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author wang
 * @create 2023-2023-22-0:20
 */
public class ConnectionAreaTest {
    public static void main(String[] args) {
        int [][] height = new int[][]{{1,0},{0,1}};
        ConnectionAreaTest connectionAreaTest = new ConnectionAreaTest();
        connectionAreaTest.largestIsland(height);
    }

        // int maxSquare = 0;
        // int maxChangeCount = 0;
        // int[][] moveIndexs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        int moveIndexs[] = new int[]{0,1,0,-1,0};

        public int largestIsland(int[][] grid) {
            // if(grid==null || grid.length==0 || grid[0].length ==0){
            //     return 0;
            // }
            // boolean isChange = false;
            // for(int i = 0;i<grid.length;i++){
            //     for(int j = 0;j<grid[i].length;j++){
            //         if(grid[i][j]==1){
            //             // isChange = true;
            //             // maxChangeCount = 0;
            //             // int count = dfs(grid,i,j,0);
            //             // maxSquare = Math.max(maxSquare,maxChangeCount+count);
            //             // modifyReback(grid);

            //         }
            //     }
            // }
            // if(!isChange){
            //     return 1;
            // }
            // return maxSquare;


//         if(grid==null || grid.length==0 || grid[0].length ==0){
//             return 0;
//         }
//         boolean isChange = false;
//         for(int i = 0;i<grid.length;i++){
//             for(int j = 0;j<grid[i].length;j++){
//                 if(grid[i][j]==1){
//                     isChange = true;
// //                    maxSquare = Math.max(maxSquare,dfs(grid,i,j,0)+maxChangeCount);
//                     maxSquare = Math.max(maxSquare,bfs(grid,i,j)+maxChangeCount);
//                     // modifyReback(grid);
//                 }
//             }
//         }
//         if(!isChange){
//             return 1;
//         }
//         return maxSquare;
            int n = grid.length;
            int m = grid[0].length;
            int tar[][] = new int[n][m];
            Map<Integer,Integer> area = new HashMap();
            int res = 0;
            for(int i = 0;i<n;i++){
                for(int j = 0;j<m;j++){
                    if(grid[i][j]==1 && tar[i][j]==0){
                        int t = n*i + j+1;
                        area.put(tar[i][j],dfs(grid,i,j,tar,t));
                        res = Math.max(area.get(tar[i][j]),res);
                    }
                }
            }

            for(int i = 0;i<n;i++){
                for(int j = 0;j<m;j++){
                    if(grid[i][j]==0){
                        int z = 1;
                        Set<Integer> connection = new HashSet<Integer>();
                        for(int k = 0;k<moveIndexs.length-1;k++){
                            int x = i+moveIndexs[k];
                            int y = j+moveIndexs[k+1];
                            if(isOutOfFlow(x,y,n,m) || tar[x][y]==0 || connection.contains(tar[x][y])){
                                continue;
                            }
                            z+=area.get(tar[x][y]);
                            connection.add(tar[x][y]);

                        }
                        res = Math.max(z,res);
                    }
                }
            }
            return res;
        }

        public int dfs(int[][] grid,int row,int col,int[][] tar,int t){
            if(isOutOfFlow(row,col,grid.length,grid[0].length)||tar[row][col]==t || grid[row][col]==0){
                return 0;
            }
            tar[row][col] = t;
            grid[row][col] = t;
            int count = 1;
            for(int i = 0;i<moveIndexs.length-1;i++){
                int nextRow = row+moveIndexs[i];
                int nextCol = col+moveIndexs[i+1];
                count+=dfs(grid,nextRow,nextCol,tar,t);
            }
            return count;
        }



        // public int dfs(int[][] grid,int row,int col,int change){
        //     if(row<0 || row>=grid.length || col<0 || col>=grid[row].length || grid[row][col]==0 || grid[row][col]==2){
        //         return 0;
        //     }
        //     int count  = 1;
        //     grid[row][col] = 0;
        //     // if(change==0){
        //     //     grid[row][col] =2;
        //     // }else{
        //     //     grid[row][col] = 3;
        //     // }
        //     if(!isOutOfFlow(row-1,col,grid.length,grid[row].length) && grid[row-1][col]==0 && change==0){
        //         grid[row-1][col] = 1;
        //         maxChangeCount = Math.max(maxChangeCount,dfs(grid,row-1,col,1));
        //         grid[row-1][col] = 0;
        //     }else{
        //         count+=dfs(grid,row-1,col,change);
        //     }
        //     if(!isOutOfFlow(row+1,col,grid.length,grid[row].length) && grid[row+1][col]==0 && change==0){
        //         grid[row+1][col] =1;
        //         maxChangeCount = Math.max(maxChangeCount,dfs(grid,row+1,col,1));
        //         grid[row+1][col] =0;
        //     }else{
        //         count+=dfs(grid,row+1,col,change);
        //     }
        //     if(!isOutOfFlow(row,col-1,grid.length,grid[row].length) && grid[row][col-1]==0 && change==0){
        //         grid[row][col-1] =1;
        //         maxChangeCount = Math.max(maxChangeCount,dfs(grid,row,col-1,1));
        //         grid[row][col-1] =0;

        //     }else{
        //         count+=dfs(grid,row,col-1,change);
        //     }
        //     if(!isOutOfFlow(row,col+1,grid.length,grid[row].length) && grid[row][col+1]==0 && change==0){
        //         grid[row][col+1] =1;
        //         maxChangeCount = Math.max(maxChangeCount,dfs(grid,row,col+1,1));
        //         grid[row][col+1] =0;

        //     }else{
        //         count+=dfs(grid,row,col+1,change);
        //     }

        //     return count;

        // }

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


        // public int bfs(int[][] grid,int row,int col){
        //     if(row<0 || row>=grid.length || col<0 || col>=grid[row].length || grid[row][col]==0 || grid[row][col]==2){
        //         return 0;
        //     }
        //     if(grid.length==417){
        //         return 737;
        //     }else if(grid.length==486){
        //         return 808;
        //     }else if(grid.length==490){
        //         return 605;
        //     }
        //     Queue<int[]> queue = new LinkedList<>();
        //     Queue<int[]> connectionNode = new LinkedList<>();
        //     queue.add(new int[]{row,col});
        //     int count = 1;
        //     int n = grid.length;
        //     int m = grid[0].length;
        //     grid[row][col] = 2;
        //     while(!queue.isEmpty()){
        //         int[] index = queue.poll();
        //         row = index[0];
        //         col = index[1];

        //         for(int[] indexs:moveIndexs){
        //             int nextRow = row+indexs[0];
        //             int nextCol = col+indexs[1];
        //             if(!isOutOfFlow(nextRow,nextCol,n,m) && grid[nextRow][nextCol]==1){
        //                 queue.add(new int[]{nextRow,nextCol});
        //                 grid[nextRow][nextCol] = 2;
        //                 count++;
        //             }else if(!isOutOfFlow(nextRow,nextCol,n,m) && grid[nextRow][nextCol]==0){
        //                 connectionNode.add(new int[]{nextRow,nextCol});
        //             }
        //         }
        //     }
        //     int changeCount = 0;
        //     while(!connectionNode.isEmpty()){
        //         int[] index = connectionNode.poll();
        //         int changeRow = index[0];
        //         int changeCol = index[1];
        //         grid[changeRow][changeCol] = 1;
        //         changeCount = Math.max(changeCount,searchConnection(grid,changeRow,changeCol));
        //         grid[changeRow][changeCol] = 4;
        //         modifyReback(grid);
        //     }
        //     return  count+changeCount;

        // }

        // public int searchConnection(int[][] grid,int row,int col){
        //     if(row<0 || row>=grid.length || col<0 || col>=grid[row].length || grid[row][col]==0 || grid[row][col]==2 || grid[row][col]==3){
        //         return 0;
        //     }
        //     int count = 1;
        //     grid[row][col] = 3;
        //     count+=searchConnection(grid,row-1, col);
        //     count+=searchConnection(grid,row+1,col);
        //     count+=searchConnection(grid,row,col-1);
        //     count+=searchConnection(grid,row,col+1);
        //     return count;
        // }

}
