package leetcode.searchAlog;

import java.util.ArrayList;

/**
 * @author wang
 * @create 2023-2023-21-13:13
 */
public class UpdateGraphX {
    public static void main(String[] args) {
        char[][] borad = new char[][]{  {'X','O','X','O','X','O','O','O','X','O'},
                                        {'X','O','O','X','X','X','O','O','O','X'},
                                        {'O','O','O','O','O','O','O','O','X','X'},
                                        {'O','O','O','O','O','O','X','O','O','X'},
                                        {'O','O','X','X','O','X','X','O','O','O'},
                                        {'X','O','O','X','X','X','O','X','X','O'},
                                        {'X','O','X','O','O','X','X','O','X','O'},
                                        {'X','X','O','X','X','O','X','O','O','X'},
                                        {'O','O','O','O','X','O','X','O','X','O'},
                                        {'X','X','O','X','X','X','X','O','O','O'}};
        UpdateGraphX updateGraphX = new UpdateGraphX();
        updateGraphX.solve(borad);
    }

    int[] rowCal = new int[]{0,1,-1,0};
    int[] colCal = new int[]{1,0,0,-1};
    boolean [][] isVisited = null;
    boolean isBoard = false;
    public void solve(char[][] board) {
        if(board==null || board.length==0 || board[0].length==0){
            return;
        }
        isVisited = new boolean[board.length][board[0].length];
        for(int i = 0;i<board.length;i++){
            for(int j = 0;j<board[i].length;j++){
                if(!isVisited[i][j] && board[i][j]=='O'){
                    isBoard = false;
                    retainBoard(board,i,j);
                }
            }
        }

    }

    public boolean retainBoard(char[][] board,int row,int col){

        if(isBoard(row,col,board.length,board[0].length)){
            isBoard = true;
        }

        isVisited[row][col] = true;
        for(int i = 0;i<rowCal.length;i++){
            int rowNext = row+rowCal[i];
            int colNext = col+colCal[i];
            if(!isOutOfFlow(rowNext,colNext,board.length,board[0].length) && !isVisited[rowNext][colNext] && board[rowNext][colNext]=='O'){
                if(!retainBoard(board,rowNext,colNext)){
                    board[row][col] = 'X';
                }
            }
        }
        return isBoard;
    }

    public boolean isBoard(int row,int col,int rowEnd,int colEnd){
        if(row==0 || col==0 || row == rowEnd-1 || col == colEnd-1){
            return true;
        }else{
            return false;
        }
    }

    public boolean isOutOfFlow(int row,int col,int rowEnd,int colEnd){
        if(row<0 || col<0 || row > rowEnd-1 || col > colEnd-1){
            return true;
        }else{
            return false;
        }
    }
}
