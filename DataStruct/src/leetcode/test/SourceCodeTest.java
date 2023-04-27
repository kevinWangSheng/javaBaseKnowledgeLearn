package leetcode.test;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author wang
 * @create 2023-2023-14-15:29
 */
public class SourceCodeTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(0,1);

        HashMap<Node,Integer> map = new HashMap();
        for(int i = 0;i<13;i++) {
            map.put(new Node(),i+1000);
        }
    }

    static class Node{
        private int value;

        @Override
        public boolean equals(Object o) {
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }
}
