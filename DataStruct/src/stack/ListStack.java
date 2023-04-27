package stack;

import java.util.Random;

/**
 * @author wang
 * @create 2023-2023-26-10:37
 */
public class ListStack {
    private ListNode stack;
    private int maxSize;
    private int count = 0;
    public ListStack(int maxSize){
        this.maxSize = maxSize;
        stack = new ListNode();
    }

    public void init(){
        for(int i = 0;i<maxSize;i++){
            ListNode node = new ListNode();
            node.setValue(i);
            node.setNode(stack.getNode());
            stack.setNode(node);
            count++;

        }
    }

    public boolean isEmpty(){
        return count == 0;
    }
    public boolean isFull(){
        return count==maxSize;
    }

    public int pop(){
        if(isEmpty()){
            System.out.println("is empty");
            return -1;
        }
        ListNode temp = null;
        temp = stack.getNode();
        stack.setNode(temp.getNode());
        count--;
        return temp.getValue();
    }
    public void add(){
        if(isFull()){
            System.out.println("is full");
            return;
        }
        int value = new Random().nextInt(10);
        ListNode node = new ListNode();
        node.setValue(value);
        node.setNode(stack.getNode());
        stack.setNode(node);
        count++;
    }

    public ListNode getStack() {
        return stack;
    }

    public void setStack(ListNode stack) {
        this.stack = stack;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void add(int ch){
        if(isFull()){
            System.out.println("is full");
            return;
        }
        ListNode node = new ListNode();
        node.setValue(ch);
        node.setNode(stack.getNode());
        stack.setNode(node);
        count++;
    }



    public void listNode(){
        while(stack.getNode()!=null){
            int value = pop();
            System.out.printf("%d\t",value);
        }
    }

    public static void main(String[] args) {
        ListStack stack = new ListStack(10);

        stack.init();
        stack.add();
        System.out.println(stack.pop());
        stack.listNode();
        stack.pop();
    }

    public int peek() {
        return stack.getNode().getValue();
    }
}
