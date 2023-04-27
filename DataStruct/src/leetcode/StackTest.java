package leetcode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author wang
 * @create 2023-2023-13-11:15
 */
public class StackTest {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(2147483646);
        minStack.push(2147483646);
        minStack.push(2147483647);
        minStack.pop();
        minStack.pop();
        minStack.getMin();
        minStack.pop();
        minStack.push(2147483647);
        minStack.top();
        minStack.getMin();
        minStack.push(-2147483648);
        System.out.println(minStack.top());
        minStack.getMin();
    }
}

class MinStack {
    ArrayList<Integer> list;
    Stack<Integer> stack;
    public MinStack() {
        stack = new Stack();
        list = new ArrayList();
    }

    public void push(int val) {
        // insertInMinArray(val);
        stack.push(val);
        list.add(val);
    }

    // public void insertInMinArray(int val){
    //     int temp = val;
    //     int t1 = 0;
    //     int i = 0;
    //     for(i = 0;minArray[i]!=-500;i++){
    //         if(minArray[i]>temp){
    //             temp = minArray[i];
    //             minArray[i] = val;
    //             int j;
    //             for(j = i+1;minArray[j]!=-500;j++){
    //                 t1 = minArray[j];
    //                 minArray[j] = temp;
    //                 temp = t1;
    //             }
    //             minArray[j] = temp;
    //             return;
    //         }
    //     }
    //     minArray[i] = temp;

    // }

    public void pop() {
        int val = stack.pop();
        int index = list.indexOf(val);
        list.remove(index);
    }

    // public void delMinArray(int val){
    //     for(int i = 0;minArray[i]!=-500;i++){
    //         if(minArray[i]==val){
    //             for(int j = i;minArray[j]!=-500;j++){
    //                 minArray[j] = minArray[j+1];
    //             }
    //             break;
    //         }
    //     }
    // }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        list.sort((a,b)->{
            return a>b?1:-1;
        });
        return list.get(0);
    }
}
