package tree;

/**
 * @author wang
 * @create 2023-2023-31-16:11
 */
public class ThreadTreeNode {
    private int value;
    private ThreadTreeNode left;
    private ThreadTreeNode right;
    private ThreadTreeNode pre;
    private ThreadTreeNode parent;

    public ThreadTreeNode getParent() {
        return parent;
    }

    public void setParent(ThreadTreeNode parent) {
        this.parent = parent;
    }

    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    @Override
    public String toString() {
        return "ThreadTreeNode{" +
                "value=" + value +
                ", pre=" + pre +
                ", leftType=" + leftType +
                ", rightType=" + rightType +
                ", parent="+ parent+
                '}';
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ThreadTreeNode getLeft() {
        return left;
    }

    public void setLeft(ThreadTreeNode left) {
        this.left = left;
    }

    public ThreadTreeNode getRight() {
        return right;
    }

    public void setRight(ThreadTreeNode right) {
        this.right = right;
    }

    public ThreadTreeNode getPre() {
        return pre;
    }

    public void setPre(ThreadTreeNode pre) {
        this.pre = pre;
    }

    public ThreadTreeNode(int value) {
        this.value = value;
    }

}
