package Algorithm.DeadLock.test;

import Algorithm.DeadLock.BankAlgorithm;
import Algorithm.DeadLock.BankerAlgoirthmImpl;
import org.junit.Test;

/**
 * @author wang
 * @create 2022-2022-15-21:20
 */
public class BankAlgorithmTest {
    @Test
    public void bankAlgorithm(){
        BankAlgorithm bankAlgorithm = new BankerAlgoirthmImpl();

        bankAlgorithm.init();

        boolean isSafe = bankAlgorithm.allOfIsSafe();

        System.out.println(isSafe);
    }
}
