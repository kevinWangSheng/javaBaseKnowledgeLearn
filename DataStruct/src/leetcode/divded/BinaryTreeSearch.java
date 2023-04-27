package leetcode.divded;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wang
 * @create 2023-2023-18-23:32
 */
public class BinaryTreeSearch {
    public static void main(String[] args) {
        BinaryTreeSearch binaryTreeSearch = new BinaryTreeSearch();
        binaryTreeSearch.generateTrees(3);
    }
    public List<TreeNode> generateTrees(int n) {
        return generateTrees(1,n);

    }

    public List<TreeNode> generateTrees(int start,int end){
        List<TreeNode> list = new ArrayList();
        if(start>end){
            return null;
        }else if(start==end){
            list.add(new TreeNode(start));
            return list;
        }
        int i = start;
        while(i<=end){
            List<TreeNode> leftList = generateTrees(start,i-1);
            List<TreeNode> rightList = generateTrees(i+1,end);

            if(leftList==null&& rightList!=null){
                for(TreeNode rightNode:rightList){
                    TreeNode node = new TreeNode(i);
                    node.right = rightNode;
                    list.add(node);
                }
            }else if(rightList==null && leftList!=null){
                for(TreeNode leftNode : leftList){
                    TreeNode node = new TreeNode(i);
                    node.left = leftNode;
                    list.add(node);
                }
            }else if(leftList!=null && rightList!=null){
                for(TreeNode leftNode : leftList){
                    for(TreeNode rightNode : rightList){
                        TreeNode node = new TreeNode(i);
                        node.left = leftNode;
                        node.right = rightNode;
                        list.add(node);
                    }
                }
            }else{
                TreeNode node = new TreeNode(i);
                list.add(node);
            }
            i++;
        }
        return list;


    }
}

  class TreeNode {
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
