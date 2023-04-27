package leetcode.list;

/**
 * @author wang
 * @create 2023-2023-23-11:59
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        ListNode listNode = solution.removeNthFromEnd(new ListNode(1), 1);
//        System.out.println(listNode);

        ListNode listNode = new ListNode();
        listNode.val = 1;
//        listNode.next = new ListNode(2,new ListNode(3,new ListNode(4)));
//        solution.swapPairs(listNode);
        listNode.next = new ListNode(2);
        solution.isPalindrome(listNode);
    }
}
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        } else {
            ListNode newHead = head;
            ListNode dele = null;
            ListNode prev = null;
            ListNode temp = head;
            while (head != null) {
                if (n - 1 > 0) {
                    n--;
                } else {
                    prev = dele;
                    dele = temp;
                    temp = temp.next;
                    n--;
                }
                head = head.next;
            }
            if (n <= 0) {
                if (prev != null) {
                    prev.next = dele.next;
                } else {
                    dele = dele.next;
                }

            }

            return newHead;
        }
    }
    public ListNode swapPairs(ListNode head) {
        if(head==null){
            return null;
        }
        ListNode newHead = null;
        ListNode node = head;
        ListNode next = head.next;
        ListNode prev = null;
        boolean isFirst = true;

        while(node!=null && next !=null){
            if(isFirst){
                node.next = next.next;
                next.next = node;
                prev = node;
                newHead = next;
                isFirst = false;
            }else{
                node.next = next.next;
                next.next = node;
                prev.next = next;
                prev = node;
            }
            node = node.next;
            if(node.next!=null){
                next = node.next.next;
            }
        }
        return newHead;
    }

    public boolean isPalindrome(ListNode head) {
        if(head==null){
            return false;
        }
        ListNode newHead = new ListNode(head.val);
        ListNode head1 = newHead;
        ListNode temp = head.next;
        while(temp!=null){
            newHead.next = new ListNode(temp.val);
            newHead = newHead.next;
            temp = temp.next;
        }

        ListNode reverList = reverseNode(head);
        while(reverList!=null && head1!=null){
            if(reverList.val!=head1.val){
                return false;
            }
            reverList = reverList.next;
            head1 = head1.next;
        }
        return true;
    }

    public ListNode reverseNode(ListNode node){
        if(node==null || node.next == null){
            return node;
        }

        ListNode next = node.next;
        ListNode newHead  = reverseNode(next);
        next.next = node;
        node.next = null;
        return newHead;

    }
}
