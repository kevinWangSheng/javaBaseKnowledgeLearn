package leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author wang
 * @create 2023-2023-27-11:34
 */
public class TreeSolution {
    public static void main(String[] args) {
        Solution solution = new Solution();

//        TreeNode root = new TreeNode(1000000000,new TreeNode(1000000000,new TreeNode(294967296,new TreeNode(1000000000,new TreeNode(1000000000,new TreeNode(1000000000), null),null),null) , null),null);

//        solution.pathSum(root,0);

//        TreeNode root = new TreeNode(5,new TreeNode(4,new TreeNode(11,new TreeNode(7,null,null),new TreeNode(2)),null),
//                new TreeNode(8,new TreeNode(13,null,null),new TreeNode(4,new TreeNode(5,null,null),new TreeNode(1,null,null))));
//        solution.pathListSum(root,22);

        TreeNode root = new TreeNode(3,new TreeNode(2,null,null),new TreeNode(3,new TreeNode(1,null,null),null));
        solution.postorderTraversal(root);

    }
}
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}

class Solution {
    int count = 0;
    public int pathSum(TreeNode root, int targetSum) {
        if(root != null){
            hasPathSum(root,targetSum);
            pathSum(root.left,targetSum);
            pathSum(root.right,targetSum);
        }
        return count;
    }

    public void hasPathSum(TreeNode root, int targetSum) {
        if(root!=null){
            if(targetSum-root.val==0){
                count++;
            }
            hasPathSum(root.left,targetSum-root.val);
            hasPathSum(root.right,targetSum-root.val);
        }else{
            return;
        }
    }

    List<List<Integer>> res = new ArrayList<List<Integer>>();
    public List<List<Integer>> pathListSum(TreeNode root, int targetSum) {
        List<Integer> path = new ArrayList<Integer>();
        setPath(root,targetSum,path);
        return res;
    }

    public void setPath(TreeNode root,int targetSum,List<Integer> path){
        if(root==null){
            return;
        }
        path.add(root.val);
        targetSum-=root.val;
        if(root.left==null && root.right==null && targetSum==0){
            res.add(new ArrayList<>(path));
            // return;
        }
        setPath(root.left,targetSum,path);
        setPath(root.right,targetSum,path);
        path.remove(path.size()-1);
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        List<Integer> list = new ArrayList<Integer>();
        if(root==null){
            return list;
        }

        int size = getRootNumber(root);

        stack.push(root);
        while(stack.size()<size){
            // TreeNode node = stack.pop();
            // list.add(node.val);
            // if(node.left!=null){
            //     stack.push(node.left);
            // }
            // if(node.right!=null){
            //     stack.push(node.right);
            // }

            TreeNode node = stack.peek();
            if(node.right!=null){
                stack.push(node.right);
            }
            if(node.left!=null){
                stack.push(node.left);
            }
        }

        while(!stack.empty()){
            TreeNode node = stack.pop();
            list.add(node.val);
        }
        // Collections.reverse(list);
        return list;
    }

    public int getRootNumber(TreeNode root){
        if(root==null){
            return 0;
        }
        int num = 1;
        num+=getRootNumber(root.left)+getRootNumber(root.right);
        return num;
    }
}
