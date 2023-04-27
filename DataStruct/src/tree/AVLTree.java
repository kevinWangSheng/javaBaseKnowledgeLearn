package tree;

import java.util.Random;

/**
 * @author wang
 * @create 2023-2023-09-10:46
 */
public class AVLTree {
    public static void main(String[] args) {
        Random random = new Random();
        AVLTreeNode node = new AVLTreeNode(random.nextInt(1000));
        AVLTreeNode root = node;
        for(int i = 0;i<6;i++){
            while(root.getParent()!=null){
                root = root.getParent();
            }
            root.add(random.nextInt(1000));
        }
        node.preBalance(node);
        while(root.getParent()!=null){
            root = root.getParent();
        }




        root.postOrder(root);

        System.out.println(root.getTreeHeight(root));
    }
}
