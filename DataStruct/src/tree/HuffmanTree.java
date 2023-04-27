package tree;

import java.util.*;

/**
 * @author wang
 * @create 2023-2023-03-11:17
 */
public class HuffmanTree {
    private HuffmanNode root;

    private int[] arr;

    public static void main(String[] args) {
        HuffmanTree huffmanTree = new HuffmanTree();

        huffmanTree.arr = new int[10];

        Random random = new Random();

        for(int i = 0;i<huffmanTree.arr.length;i++){
            huffmanTree.arr[i] = random.nextInt(100);
        }
        System.out.println(Arrays.toString(huffmanTree.arr));
        HuffmanNode root = huffmanTree.createHuffmanTree(huffmanTree.arr);

        huffmanTree.preOrder(root);

    }



    public HuffmanNode createHuffmanTree(int arr[]){
        List<HuffmanNode> huffmanTree = new ArrayList<>();

        for(int value: arr){
            huffmanTree.add(new HuffmanNode(value));
        }

        Collections.sort(huffmanTree);


        while(huffmanTree.size()>1){
            HuffmanNode leftNode = huffmanTree.get(0);
            HuffmanNode rightNode = huffmanTree.get(1);

            HuffmanNode node = new HuffmanNode(leftNode.getValue()+rightNode.getValue());

            node.setLeft(leftNode);
            node.setRight(rightNode);

            huffmanTree.add(node);

            huffmanTree.remove(leftNode);
            huffmanTree.remove(rightNode);

            Collections.sort(huffmanTree);
        }

        return huffmanTree.get(0);
    }

    public void preOrder(HuffmanNode root){
        if(root == null){
            return;
        }
        System.out.println(root);
        if(root.getLeft()!=null){
            preOrder(root.getLeft());
        }
        if(root.getRight()!=null){
            preOrder(root.getRight());
        }
    }
}
