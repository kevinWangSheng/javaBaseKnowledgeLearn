package leetcode.backTracking;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author wang
 * @create 2023-2023-27-1:04
 */
public class TargetSort {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();

        queue.add(2);
        queue.add(3);
        queue.add(2);

        queue.stream().forEach(System.out::println);
    }
}
