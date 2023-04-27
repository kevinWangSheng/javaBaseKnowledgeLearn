package leetcode.sort;

import java.util.*;

/**
 * @author wang
 * @create 2023-2023-10-12:59
 */
public class KFrequency {
    public static void main(String[] args) {
//        int arr[] = {-1,-1};
        KFrequency kFrequency = new KFrequency();
////        kFrequency.topKFrequent(arr,1);
//        int num = new Integer(2);
//        char ch = (char) num;
//        int num1 = 97;
//        char ch1 = (char) num1;
//        System.out.println(ch1);
//        System.out.println((int)ch);
//        PriorityQueue<int[]> queue  = new PriorityQueue<>();
//        int[] poll = queue.poll();
        kFrequency.frequencySort1("tree");
        Character ch = '1';


    }


    public String frequencySort1(String s) {
        Map<Character,Integer> map = new HashMap();

        int maxFre = 0;
        for(char ch:s.toCharArray()){
            int frequency = map.getOrDefault(ch,0)+1;
            map.put(ch,frequency);
            maxFre = Math.max(maxFre,frequency);
        }

        // PriorityQueue<char[]> queue = new PriorityQueue<char[]>((o1,o2)->{
        //     if(o1[1]>o2[1]){
        //         return -1;
        //     }else {
        //         return 1;
        //     }
        // });

        // for(Map.Entry<Character,Integer> entry : map.entrySet()){
        //     char[] chars = new char[2];
        //     chars[0] = entry.getKey();
        //     int value = (int) entry.getValue();
        //     chars[1] = (char) value;
        //     queue.offer(chars);
        // }
        // s = "";
        // while(queue.size()>0){
        //     char []chars = queue.poll();
        //     for(int i = 0;i<chars[1];i++){
        //         s+=chars[0];
        //     }
        // }



        // s = "";
        // PriorityQueue<int[]> queue = new PriorityQueue<int[]>((o1,o2)->{
        //     if(o1[1]>o2[1]){
        //         return -1;
        //     }else{
        //         return 1;
        //     }
        // });

        // for(Map.Entry<Character,Integer> entry : map.entrySet()){
        //     int [] arr = new int[2];
        //     arr[1] = entry.getValue();
        //     arr[0] = entry.getKey();
        //     queue.offer(arr);
        // }
        // int arr[] = new int[2];
        // while(queue.size()>0){
        //     arr = queue.poll();
        //     char ch = (char)arr[0];
        //     for(int i = 0;i<arr[1];i++){
        //         s+=ch;
        //     }
        // }
        // return s;

        StringBuilder sb = new StringBuilder();
        int arr[][] = new int[maxFre+1][2];
        for(Map.Entry<Character,Integer> entry : map.entrySet()){
            int value = entry.getValue();
            int ch = entry.getKey();
            arr[value][0] = value;
            arr[value][1] = ch;
        }

        for(int i = arr.length-1;i>=0;i--){
            if(arr[i][0]!=0){
                for(int j = 0;j<arr[i][0];j++){
                    sb.append((char)arr[i][1]);
                }
            }
        }
//        StringBuilder sb = new StringBuilder();
        sb.append(new Character('1'));
        return sb.toString();
    }

    public void test(){
        List<Integer> list = new ArrayList<>();
    }

    public String frequencySort(String s) {
        Map<Character,Integer> map = new HashMap();

        for(char ch:s.toCharArray()){
            map.put(ch,map.getOrDefault(ch,0)+1);
        }

        // PriorityQueue<char[]> queue = new PriorityQueue<char[]>((o1,o2)->{
        //     if(o1[1]>o2[1]){
        //         return -1;
        //     }else {
        //         return 1;
        //     }
        // });

        // for(Map.Entry<Character,Integer> entry : map.entrySet()){
        //     char[] chars = new char[2];
        //     chars[0] = entry.getKey();
        //     int value = (int) entry.getValue();
        //     chars[1] = (char) value;
        //     queue.offer(chars);
        // }
        // s = "";
        // while(queue.size()>0){
        //     char []chars = queue.poll();
        //     for(int i = 0;i<chars[1];i++){
        //         s+=chars[0];
        //     }
        // }
        s = "";
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>((o1,o2)->{
            if(o1[1]>o2[1]){
                return -1;
            }else{
                return 0;
            }
        });

        for(Map.Entry<Character,Integer> entry : map.entrySet()){
            int [] arr = new int[2];
            arr[1] = entry.getValue();
            arr[0] = entry.getKey();
            queue.offer(arr);
        }
        int arr[] = new int[2];
        while(queue.size()>0){
            arr = queue.poll();
            char ch = (char)arr[0];
            for(int i = 0;i<arr[1];i++){
                s+=ch;
            }
        }
        return s;
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap();
        for(int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }


        PriorityQueue<Integer> queue = new PriorityQueue<Integer>((o1, o2)->{
            if(o1>o2){
                return -1;
            }else{
                return 1;
            }
        });
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            queue.offer(entry.getValue());
        }
        int res[] = new int[k];
        for(int i = 0;i<k;i++){
            res[i] = map.get(new Integer(queue.poll()));
        }
        return res;
    }

}
