package tree;

/**
 * @author wang
 * @create 2023-2023-31-14:24
 */
public class ArrayTree {
    static int arr[];
    public static void main(String[] args) {
         arr = new int[] {1,2,3,4,5,6,7};

        // pre order
        ArrayTree tree = new ArrayTree();
        tree.preOrder(0);

    }

    public  void preOrder(int index){
        if(arr == null||arr.length==0)
        {
            return;
        }
        System.out.println(arr[index]);
        int left = 2*index+1;
        if(left<arr.length){
            preOrder(left);
        }
        if(left+1<arr.length){
            preOrder(left+1);
        }
    }
}
