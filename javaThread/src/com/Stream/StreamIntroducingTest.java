package com.Stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wang
 * @create 2022-2022-29-12:08
 */
public class StreamIntroducingTest {
    @Test
    public void streamAsStreamTest(){
        List<String> list = Arrays.asList("1", "qwe", "123123", "", "", "wadwef");

        list.stream().sorted((o1,o2)->{
            return -(o1.length()-o2.length());
        }).filter(s->{
            return !s.isEmpty();
        }).forEach(s->{
            System.out.println(s);
        });


        List<String> collect = list.stream().sorted((o1, o2) -> {
            return -(o1.length() - o2.length());
        }).filter(s -> {
            return !s.isEmpty();
        }).collect(Collectors.toList());

        collect.forEach(s->{
            System.out.println(s);
        });

        String joingString = list.stream().sorted((o1, o2) -> {
            return -(o1.length() - o2.length());
        }).filter(s -> {
            return !s.isEmpty();
        }).collect(Collectors.joining(","));

        System.out.println(joingString);
    }

    @Test
    public void streamNumberTest(){
        List<Integer> integerList = Arrays.asList(1,1, 2, 3, 634, 4, 2342, 12, 34543);

        List<Integer> collect = integerList.stream().map(number -> {
            return number * number;
        }).distinct().collect(Collectors.toList());

        collect.forEach(s->{
            System.out.println(s);
        });

        System.out.println("****************************");
        IntSummaryStatistics intSummaryStatistics = integerList.stream().mapToInt(s -> s).summaryStatistics();

        System.out.println(intSummaryStatistics);


        Random random = new Random();
        random.ints().limit(1).forEach((num)-> System.out.println(num));




    }

    @Test
    public void parellelTest(){
        List<String> list = Arrays.asList("wanglaowu", "lisi", "nima", "grandFather");

        List<String> collect = list.parallelStream().sorted(((o1, o2) -> {
            return o2.length() - o1.length();
        })).collect(Collectors.toList());

        collect.forEach(s->{
            System.out.println(s);
        });
    }

    @Test
    public void parellelAndNoParallelTest(){
        List<Integer> integerList = buildIntLists();

        long startTime = System.currentTimeMillis();


        integerList.stream().forEach(s->{
            try{ Thread.sleep(1);} catch(InterruptedException e){ e.printStackTrace();}
        });
        long endTime = System.currentTimeMillis();

        System.out.println("the first  time that you cost is "+(endTime - startTime)+"ms");

        startTime = System.currentTimeMillis();

        integerList.parallelStream().forEach(s->{
            try{ Thread.sleep(1);} catch(InterruptedException e){ e.printStackTrace();}
        });
        endTime = System.currentTimeMillis();

        System.out.println("the last cost time is "+(endTime-startTime)+"ms");

    }

    public static List<Integer> buildIntLists() {
        long startLinked = System.currentTimeMillis();
        List<Integer> linkedList =new LinkedList();
        for (int a = 1;a <=10000 ;a++) {
            linkedList.add(a);
        }
        System.out.println("创建LinkedList : " + (System.currentTimeMillis() - startLinked) + "ms");
        return Collections.unmodifiableList(linkedList);
    }

    @Test
    public void reduceTest(){
        List<Integer> integerList = Arrays.asList(1, 423, 23, 1231, 4353);

        Optional<Integer> total = null;
        Integer reduce = integerList.stream()
                .map(s -> {
                    return s + s;
                })
                .reduce(1, (sum, value) -> {

                    return sum + value;
                });

        System.out.println(reduce);


        System.out.println(total);
    }

}
