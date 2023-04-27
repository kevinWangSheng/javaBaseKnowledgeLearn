package stack;

/**
 * @author wang
 * @create 2023-2023-26-10:38
 */
public class ListNode {
    private int value;
    private ListNode node;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ListNode getNode() {
        return node;
    }

    public void setNode(ListNode node) {
        this.node = node;
    }

    public ListNode(ListNode node) {
        this.node = node;
    }

    public ListNode() {
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "node=" + node +
                '}';
    }
}
