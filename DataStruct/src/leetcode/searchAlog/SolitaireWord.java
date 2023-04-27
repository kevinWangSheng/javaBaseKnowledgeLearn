package leetcode.searchAlog;

import java.util.*;

/**
 * @author wang
 * @create 2023-2023-20-12:32
 */
public class SolitaireWord {
    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        String words[] = new String[]{"hot","dot","dog","lot","log","cog"};
                List<String> wordList = Arrays.asList(words);
        SolitaireWord solitaireWord = new SolitaireWord();
        solitaireWord.ladderLength(beginWord,endWord,wordList);

    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if(!wordList.contains(endWord)){
            return 0;
        }
        if(endWord.equals(beginWord)){
            return 1;
        }
        List<Integer>[] graphWord =  buildGraph(wordList);

        int count = 0;

        boolean isVisited[] = new boolean[wordList.size()];
        Queue<Integer> queue = new LinkedList();
        for(int i = 0;i<wordList.size();i++){
            if(canTranslate(beginWord,wordList.get(i))){
                queue.add(i);
                isVisited[i] = true;
            }
        }
        count++;
        while(queue.size()>0){
            int size = queue.size();
            count++;
            while(size-->0){
                Integer vertex = queue.poll();
                if(wordList.get(vertex).equals(endWord)){
                    return count;
                }
                for(int vertexIndex:graphWord[vertex]){
                    if(!isVisited[vertexIndex]){
                        queue.add(vertexIndex);
                        isVisited[vertexIndex] = true;
                    }
                }
            }
        }
        return 0;
    }

    public List<Integer>[] buildGraph(List<String> wordList){
        if(wordList == null){
            return null;
        }

        int N = wordList.size() ;
        List<Integer>[] indexList = new ArrayList[N];
        for(int i = 0;i<N;i++){
            indexList[i] = new ArrayList();
            for(int j = 0;j<N;j++){
                if(canTranslate(wordList.get(i),wordList.get(j))){
                    indexList[i].add(j);
                }
            }
        }
        return indexList;
    }
    public boolean canTranslate(String beginWord,String objWord){
        int count = 0;
        for(int i = 0;i<beginWord.length();i++){
            if(beginWord.charAt(i)!=objWord.charAt(i)){
                count++;
                if(count>1){
                    return false;
                }
            }
        }
        return true;
    }
}
