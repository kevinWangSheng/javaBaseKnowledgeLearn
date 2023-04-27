package tree;

import java.util.Random;

/**
 * @author wang
 * @create 2023-2023-31-16:12
 */
public class ThreadTree {
    private ThreadTreeNode threadTree;
    private ThreadTreeNode prv;

    public ThreadTree(ThreadTreeNode threadTree) {
        this.threadTree = threadTree;
    }

    public static void main(String[] args) {
        ThreadTree tree = new ThreadTree(new ThreadTreeNode(50));
        Random random = new Random();
        for(int i = 0;i<10;i++){
           tree.threadTree =  tree.add(tree.threadTree,random.nextInt(100));
        }

//        tree.preOrgPrint(tree.threadTree);
//        tree.threadPreTree(tree.threadTree);
//        System.out.println("---------------------------");
//        tree.prePrint(tree.threadTree);
//        tree.prePrintTree(tree.threadTree);

//        tree.threadTree(tree.threadTree);

//        tree.preThreadTree(tree.threadTree);
        if(tree.threadTree.getRight()==null){
            tree.threadTree.setRightType(1);
        }

        tree.postOrgPrint(tree.threadTree);
        tree.threadPostTree(tree.threadTree);
        System.out.println("--------------------------------");
        tree.threadPostPrint(tree.threadTree);
//        tree.threadPost(tree.threadTree);

    }

    public ThreadTreeNode add(ThreadTreeNode node,int value){
        if(node==null){
            node = new ThreadTreeNode(value);
            return node;
        }else if(value<node.getValue()){
            ThreadTreeNode left = add(node.getLeft(), value);
            node.setLeft(left);
            if(left !=null) {
                left.setParent(node);
            }
        }else if(value>node.getValue()){
            ThreadTreeNode right = add(node.getRight(), value);
            node.setRight(right);
            if(right!=null){
                right.setParent(node);
            }
        }
        return node;
    }

    public void prePrintTree(ThreadTreeNode node){
        if(node == null){
            return ;
        }
        prePrintTree(node.getLeft());
        System.out.println(node);
        prePrintTree(node.getRight());
    }

    public void threadTree(ThreadTreeNode node){
        if(node == null){
            return ;
        }
        threadTree(node.getLeft());

        if(node.getLeft()==null){
            node.setLeft(prv);
            node.setLeftType(1);

        }
        if(prv!=null && prv.getRight()==null){
            prv.setRight(node);
            prv.setRightType(1);
        }
        node.setPre(prv);
        prv = node;

        threadTree(node.getRight());

    }

    public void preThreadTree(ThreadTreeNode node){
        while(node!=null){
            while(node.getLeft()!=null && node.getLeftType()==0){
                node = node.getLeft();
            }
            System.out.println(node);
            while(node.getRight()!=null && node.getRightType()==1){
                node = node.getRight();
                System.out.println(node);
            }

            node = node.getRight();

        }
    }

    public void threadPreTree(ThreadTreeNode node){
        if(node == null)
        {
            return ;
        }
        if(node.getLeft() == null){
            node.setLeft(prv);
            node.setLeftType(1);
        }
        if(prv!=null && prv.getRight()==null)
        {
            prv.setRight(node);
            prv.setRightType(1);
        }
        prv = node;

        if(node.getLeft()!=null && node.getLeftType() ==0){
            threadPreTree(node.getLeft());
        }
        if(node.getRightType()!=1 && node.getRight()!=null) {
            threadPreTree(node.getRight());
        }
    }

    public void prePrint(ThreadTreeNode node){
        if(node==null){
            return;
        }
        while(node!=null) {

            while(node.getLeft()!=null && node.getLeftType()==0){
                System.out.println(node);
                node = node.getLeft();
            }
            while(node.getRight()!=null && node.getRightType()==1){
                System.out.println(node);
                node = node.getRight();
            }
            node = node.getRight();

        }

    }

    public void preOrgPrint(ThreadTreeNode node){
        if(node == null)
        {
            return ;
        }
        System.out.println(node);
        preOrgPrint(node.getLeft());
        preOrgPrint(node.getRight());
    }

    public void threadPostTree(ThreadTreeNode node){
        if(node == null)
        {
            return ;
        }


        if(node.getLeft()!=null && node.getLeftType() ==0){
            threadPostTree(node.getLeft());
        }
        if(node.getRightType()!=1 && node.getRight()!=null) {
            threadPostTree(node.getRight());
        }

        if(node.getLeft() == null){
            node.setLeft(prv);
            node.setLeftType(1);
        }
        if(prv!=null && prv.getRight()==null)
        {
            prv.setRight(node);
            prv.setRightType(1);
        }
        prv = node;
    }

    public void threadPostPrint(ThreadTreeNode root){
        ThreadTreeNode node =root;
        ThreadTreeNode prv = null;
        if(node == null)
        {
            return;
        }
        while(node!=null){
            while(node.getLeft()!=prv && node.getLeftType()==0 ){
                node = node.getLeft();
            }
            while(node.getRight()!=null &&node.getRightType() == 1){
                System.out.println(node);
                prv = node;
                node = node.getRight();
            }

            if((node == root && node.getRight()==null&& node.getRightType() == 0)|| (node==root && prv.getRight()==prv)){
                System.out.println(node);
                return;
            }
            while(node != null && node.getRight()==prv ){
                System.out.println(node);
                prv = node;
                node = node.getParent();
            }
            if(node!=null && node.getRightType()==0) {
                node = node.getRight();
            }
        }
    }

    public void threadPost(ThreadTreeNode node){
        ThreadTreeNode root = node;
        while(root!=null && root.getLeftType()==0){
            root = root.getLeft();
        }

        ThreadTreeNode prv = null;
        while(root !=null){
            if(root.getRightType()==1){
                System.out.println(root);
                prv = root;
                root = root.getRight();
            }else{
                if(root.getRight() == prv){
                    System.out.println(root);
                    if(root == node){
                        return ;
                    }
                    prv = root;
                    root = root.getParent();
                }else{
                    root = root.getRight();
                    while(root!=null && root.getLeftType()==0){
                        root = root.getLeft();
                    }
                }
            }
        }
    }


    public void postOrgPrint(ThreadTreeNode node){
        if(node == null)
        {
            return ;
        }

        postOrgPrint(node.getLeft());
        postOrgPrint(node.getRight());
        System.out.println(node);
    }

}
