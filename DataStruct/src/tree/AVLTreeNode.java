package tree;

/**
 * @author wang
 * @create 2023-2023-09-10:33
 */
public class AVLTreeNode {
    private int value;
    private AVLTreeNode left;
    private AVLTreeNode right;

    private AVLTreeNode parent;

    @Override
    public String toString() {
        return "AVLTreeNode{" +
                "value=" + value +
                '}';
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public AVLTreeNode getLeft() {
        return left;
    }

    public void setLeft(AVLTreeNode left) {
        this.left = left;
    }

    public AVLTreeNode getRight() {
        return right;
    }

    public void setRight(AVLTreeNode right) {
        this.right = right;
    }

    public AVLTreeNode getParent() {
        return parent;
    }

    public void setParent(AVLTreeNode parent) {
        this.parent = parent;
    }

    public AVLTreeNode(int value) {
        this.value = value;
    }

    public void add(int value){

        if(this.value<value){
            if(this.right!=null) {
                this.right.add(value);
            }else if(this.right==null){
                AVLTreeNode newNode = new AVLTreeNode(value);
                this.right = newNode;
                newNode.parent = this;

//                balanceAvl(this.parent);

            }


        }
        else if(this.value>value){
            if(this.left!=null) {
                this.left.add(value);
            }else if(this.left==null){
                AVLTreeNode newNode = new AVLTreeNode(value);
                this.left = newNode;
                newNode.parent = this;
//                balanceAvl(this.parent);

            }
        }else{
            System.out.println("have the same");
        }
    }

    public int getTreeHeight(AVLTreeNode node){
        if(node == null){
            return 0;
        }

        int leftHeight = getTreeHeight(node.left)+1;

        int rightHeight = getTreeHeight(node.right)+1;

        return Math.max(leftHeight,rightHeight);

    }

    public void leftRotate(){
        AVLTreeNode nodeRight = this.right;
        AVLTreeNode parent = this.parent;
        this.right = nodeRight.left;
        if(nodeRight.left!=null){
            nodeRight.left.parent = this;
        }
        nodeRight.left = this;
        nodeRight.parent = parent;
        this.parent = nodeRight;
        if(parent!=null && parent.left == this){
            parent.left = nodeRight;
        }else if(parent!=null && parent.right == this){
            parent.right = nodeRight;
        }
    }

    public void rightRoate(){
        AVLTreeNode parent = this.parent;
        AVLTreeNode nodeLeft = this.left;
        this.left = nodeLeft.right;
        if(nodeLeft.right!=null){
            nodeLeft.right.parent = this;
        }
        nodeLeft.right = this;

        nodeLeft.parent = parent;
        this.parent = nodeLeft;

        if(parent!=null && parent.left == this){
            parent.left = nodeLeft;
        }else if(parent!=null && parent.right == this){
            parent.right = nodeLeft;
        }
    }

    public void rightLeftRoate(){
        AVLTreeNode rightNode = this.right;
        AVLTreeNode rightNodeLeft = rightNode.left;
        rightNode.rightRoate();
        rightNodeLeft.parent.leftRotate();
    }

    public void leftRightRoate(){
        AVLTreeNode leftNode = this.left;
        AVLTreeNode leftNodeRight = leftNode.right;
        leftNode.leftRotate();
        leftNodeRight.parent.rightRoate();

    }

    public void balanceAvl(AVLTreeNode node){
        if(node == null){
            return;
        }
        int leftHeight = node.getTreeHeight(node.getLeft());
        int rightHeight = node.getTreeHeight(node.getRight());
        if(leftHeight-rightHeight>=2){
            AVLTreeNode left = node.left;
            if(getTreeHeight(left.left)>getTreeHeight(left.right)){
                node.rightRoate();
            }
            else{
                node.leftRightRoate();
            }
        }
        if(rightHeight-leftHeight>=2){
            AVLTreeNode right = node.right;
            if(getTreeHeight(right.right)>getTreeHeight(right.left)){
                node.leftRotate();
            }else{
                node.rightLeftRoate();
            }
        }
        if(node.parent!=null){
            balanceAvl(node.parent);
        }
    }

    public void postOrder(AVLTreeNode node){
        if(node == null){
            return;
        }

        postOrder(node.getLeft());
        System.out.println(node);
        postOrder(node.getRight());

    }

    public void preBalance(AVLTreeNode node){
        if(node == null){
            return;
        }
        preBalance(node.getLeft());
        preBalance(node.getRight());
        balanceAvl(node);
    }
}
