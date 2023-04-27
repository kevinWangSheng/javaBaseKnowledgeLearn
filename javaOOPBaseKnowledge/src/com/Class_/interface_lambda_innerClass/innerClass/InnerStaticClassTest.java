package com.Class_.interface_lambda_innerClass.innerClass;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wang
 * @create 2022-2022-19-21:18
 */
public class InnerStaticClassTest {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();

        for(int i = 10;i<100;i++)
        {
            list.add(i*Math.random());
        }

        ArrayAlg.Pair pair = ArrayAlg.Pair.getPair(list);

        System.out.println("the min is "+pair.getFirst());
        System.out.println("the max is "+pair.getSecond());

    }


}

class ArrayAlg{
    public static class Pair{
        private double first;
        private double second;

        public double getFirst() {
            return first;
        }

        public void setFirst(double first) {
            this.first = first;
        }

        public double getSecond() {
            return second;
        }

        public void setSecond(double second) {
            this.second = second;
        }

        public Pair(double first, double second) {
            this.first = first;
            this.second = second;
        }

        public static Pair getPair(List list){
            double min = Double.MAX_EXPONENT;
            double max = 0;
            for(int i = 0;i<list.size();i++){
                if(min>(double)list.get(i)) min = (double)list.get(i);
                if(max<(double)list.get(i)) max = (double) list.get(i);
            }
            return new Pair(min,max);
        }
    }
}
