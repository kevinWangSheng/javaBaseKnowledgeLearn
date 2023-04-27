package leetcode.string;

import java.util.PriorityQueue;

/**
 * @author wang
 * @create 2023-2023-25-14:58
 */
public class MaxNumber {

    public static void main(String[] args) {
//        System.out.println("123456".substring(6));
        System.out.println(maxNum(7));
    }
    public static int maxNum(int num){

        if(num<0){
            num = 0-num;
        }
        String strNum = ""+num;
        int max = 0;
        int min = 0;
        String temp = null;
        int i = 0;
        for(i = strNum.length()-1;i>=0;i--){
            if(strNum.charAt(i)<'5'){
                break;
            }
        }
        temp = strNum.substring(0,i+1);
        temp = temp+"5"+strNum.substring(i+1);
        min = Integer.parseInt(temp);

        for(i = strNum.length()-1;i>=0;i--){
            if(strNum.charAt(i)>'5'){
                break;
            }
        }
        temp = strNum.substring(0,i+1);
        temp = temp+"5"+strNum.substring(i+1);
        max = Integer.parseInt(temp);

        return (max-min);
    }

    PriorityQueue<ListNode> queue = new PriorityQueue<>((l1,l2)->{
        return l1.val - l2.val;
    });
}

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

