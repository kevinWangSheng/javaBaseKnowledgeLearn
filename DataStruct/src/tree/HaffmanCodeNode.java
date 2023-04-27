package tree;

/**
 * @author wang
 * @create 2023-2023-03-19:56
 */
public class HaffmanCodeNode implements Comparable<HaffmanCodeNode>{
    private int count;
    private Byte data;

    private HaffmanCodeNode left;
    private HaffmanCodeNode right;

    @Override
    public String toString() {
        return "HaffmanCodeNode{" +
                "count=" + count +
                ", data=" + data +
                '}';
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
    }

    public HaffmanCodeNode getLeft() {
        return left;
    }

    public void setLeft(HaffmanCodeNode left) {
        this.left = left;
    }

    public HaffmanCodeNode getRight() {
        return right;
    }

    public void setRight(HaffmanCodeNode right) {
        this.right = right;
    }

    public HaffmanCodeNode(int count, Byte data) {
        this.count = count;
        this.data = data;
    }

    @Override
    public int compareTo(HaffmanCodeNode o) {
        return this.count - o.count;
    }
}
