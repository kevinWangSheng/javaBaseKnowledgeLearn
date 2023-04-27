package leetcode.backTracking;

/**
 * @author wang
 * @create 2023-2023-26-0:06
 */
public class SearchWord {
    public static void main(String[] args) {
        SearchWord searchWord = new SearchWord();
        char[][] boards = {{'a','b'},{'c','d'}};
        String word = "acdb";
        searchWord.exist(boards,word);

    }
    int [] moveIndex = new int[]{0,-1,0,1,0};
    int n = 0;
    int rowLength,colLength;
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length==0){
            return false;
        }
        if(word==null || word.length()==0){
            return true;
        }
        rowLength = board.length;
        colLength = board[0].length;
        n = rowLength*colLength;

        for(int i = 0;i<board.length;i++){
            for(int j = 0;j<board[i].length;j++){
                if(board[i][j]==word.charAt(0)){
                    boolean[][] isVisited = new boolean[board.length][board[0].length];
                    if(dfs(board,word,"",i,j,isVisited)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean dfs(char[][] board,String word,String target,int row,int col,boolean[][] isVisited){
        if((target+board[row][col]).equals(word)){
            return true;
        }else if(target.length() >= word.length() || target.length()>=n){
            return false;
        }
        isVisited[row][col] = true;
        boolean [][] newVisited = new boolean[rowLength][colLength];
        copyBoolean(isVisited,newVisited);
        for(int i = 0;i<4;i++){
            int nextRow = row+moveIndex[i];
            int nextCol = col+moveIndex[i+1];
            if(!isOutOfFlow(nextRow,nextCol,board.length,board[0].length) && !isVisited[nextRow][nextCol] &&dfs(board,word,target+board[row][col],nextRow,nextCol,newVisited)){
                return true;
            }
        }
        return false;
    }

    public boolean isOutOfFlow(int row,int col,int endRow,int endCol){
        if(row<0 || col<0 || row>=endRow || col>=endCol){
            return true;
        }
        return false;
    }

    public void copyBoolean(boolean[][] isVisited,boolean[][] targetVisited){
        for(int i = 0;i<isVisited.length;i++){
            for(int j = 0;j<isVisited[i].length;j++){
                targetVisited[i][j] = isVisited[i][j];
            }
        }
    }
}
