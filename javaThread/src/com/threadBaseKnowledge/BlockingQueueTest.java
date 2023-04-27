package com.threadBaseKnowledge;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Array;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wang
 * @create 2022-2022-24-23:07
 */
public class BlockingQueueTest {
    private final static int FILE_QUEUE_SIZE = 10;
    private final static int QUEUE_SIZE = 100;
    private final static Path DUMMY = Path.of("1");
    private static BlockingQueue<Path> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        String directory = "d://newFile/";
        String keyWord = "n";
        new Thread(() -> {
            try {
                enuramtion(Path.of(directory));
                queue.put(DUMMY);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }, "t1").start();

        for (int i = 0; i < QUEUE_SIZE; i++) {
            new Thread(() -> {

                boolean flag = false;
                while (!flag) {

                    Path file = null;
                    try {
                        file = queue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (file == DUMMY) {
                        try {
                                queue.put(file);
                            flag = true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            serachtFile(file, keyWord);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


            countDownLatch.countDown();
        }, "t2").start();
    }


        countDownLatch.await();
        System.out.println("the size is  "+queue.size());
        System.out.println(null=="");

    }

    private static void enuramtion(Path directory ) throws IOException, InterruptedException {
        Stream<Path> list = Files.list(directory);

        for(Path child:list.collect(Collectors.toList()))
        {
            if(!Files.isDirectory(child))
                queue.put(child);
            else {
//                try{ Thread.sleep(2000);} catch(InterruptedException e){ e.printStackTrace();}
                enuramtion(child);
            }
        }

    }


    private static void serachtFile(Path path,String keyword) throws IOException {
        try(Scanner in = new Scanner(path, StandardCharsets.UTF_8))
        {
            int lineNum = 0;

            while(in.hasNext()){
                lineNum++;
                String line = in.nextLine();

                if(line.contains(keyword)){
                    System.out.printf("%s:%d:%s%n",path,lineNum,line);
                }
            }

        }
    }
}
