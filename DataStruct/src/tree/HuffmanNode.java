package tree;

/**
 * @author wang
 * @create 2023-2023-03-11:17
 */
public class HuffmanNode implements Comparable<HuffmanNode> {
    private int value;
    private HuffmanNode left;
    private HuffmanNode right;

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }

    public HuffmanNode(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return this.value - o.value;
    }
}
