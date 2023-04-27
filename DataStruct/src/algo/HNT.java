package algo;

/**
 * @author wang
 * @create 2023-2023-10-17:12
 */
public class HNT {
    public static void main(String[] args) {
        hnt(5,'A','B','C');
    }
    public static void hnt(int num,char a,char b,char c){
        if(num==1){
            System.out.println("let the "+num+ " from the "+a +" to the "+c);
        }else{
            hnt(num-1,a,c,b);
            System.out.println("let the "+(num-1)+" from the "+a +" to the "+c );
//            System.out.println("let the other from");
            hnt(num-1,b,a,c);
        }
    }
}
