package stack.labyrnith;

/**
 * @author wang
 * @create 2023-2023-27-15:43
 */
public class MazeAlgo {
    public static void main(String[] args) {
        int[][] maze = new int[10][20];

//        set the right and the left side to 1
        for(int i = 0;i<maze.length;i++){
            maze[i][0] = 1;
            maze[i][19] = 1;
        }
//        set the up side to the 1
        for(int i = 0;i<maze[1].length;i++){
            maze[0][i] = 1;
            maze[9][i] = 1;
        }

        maze[3][1] = 1;
        maze[3][2] = 1;
        maze[2][2] = 1;
//        maze[2][1] = 1;

        maze[7][17] = 1;
        maze[8][17] = 1;

        maze[8][18] = 4;
        maze[7][16] = 1;
        maze[7][15] = 1;
        for(int i = 0;i<5;i++){
            maze[i+4][16] = 1;
        }

        for(int i = 0;i<maze.length;i++)
        {
            for(int j = 0;j<maze[i].length;j++){
                System.out.printf("%d ",maze[i][j]);
            }
            System.out.println();
        }
        System.out.println("after the go out maze!");
        goMaze(maze);
        for(int i = 0;i<maze.length;i++)
        {
            for(int j = 0;j<maze[i].length;j++){
                if(maze[i][j]==1){
                    System.out.printf("* ");
                }else {
                    System.out.printf("%d ", maze[i][j]);
                }
            }
            System.out.println();
        }

    }

    /**
     *
     * @param maze
     * @param low
     * @param col
     * @return
     */
    public static boolean goOutMaze(int maze[][],int low,int col){
        if(maze[low][col] == 4){
            System.out.println("get the exit");
            return true;
        }else if(maze[low][col]==0){
            maze[low][col]=2;
            if(goOutMaze(maze,low+1,col)){
                return true;
            }else if(goOutMaze(maze,low,col+1)){
                return true;
            }else if(goOutMaze(maze,low-1,col)){
                return true;
            }else if(goOutMaze(maze,low,col-1)){
                return true;
            }else{
                maze[low][col]=3;
                return false;
            }
        }else{
            return false;
        }
    }

    public static void goMaze(int maze[][]){
        goOutMaze(maze,1,1);
    }
}
