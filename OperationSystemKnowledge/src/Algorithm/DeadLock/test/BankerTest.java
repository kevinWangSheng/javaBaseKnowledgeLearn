package Algorithm.DeadLock.test;

import Algorithm.DeadLock.impl.BankerAlgorImpl;
import org.junit.Test;

import java.util.Scanner;

/**
 * @author wang
 * @create 2022-2022-16-15:09
 */
public class BankerTest {
    @Test
    public void bankerTest(){
        BankerAlgorImpl bankerAlgor = new BankerAlgorImpl();

        bankerAlgor.initData();

        System.out.println("input the request thread index");
        Scanner scanner = new Scanner(System.in);
        int indexThread=scanner.nextInt();

        System.out.println("input the request resource");
        for(int i = 0;i<BankerAlgorImpl.RES_NUMBER;i++){

        }
    }
}
