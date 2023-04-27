package com.BitSet_;

import java.util.BitSet;

/**
 * @author wang
 * @create 2022-2022-15-17:08
 */
public class BitSetTest {
    public static void main(String[] args) {
        int n = 2000000;
        int count = 0;
        int i  = 2;
        BitSet bitSet = new BitSet(n+1);
        long start = System.currentTimeMillis();
        for(int j = 0;j<bitSet.size();j++)
            bitSet.set(j);
        while(i<=Math.sqrt(n))
        {
            if(bitSet.get(i))
            {
                int k = 2*i;
                while(k<=n){
                    bitSet.clear(k);
                    k+=i;
                }
                count++;
            }
            i++;
        }


        while(i<=n)
        {
            if(bitSet.get(i))
                count++;
            i++;
        }

        long cost = System.currentTimeMillis() - start;
        System.out.println("the cost time is "+cost);
        System.out.println("prime is "+count);
    }
}
