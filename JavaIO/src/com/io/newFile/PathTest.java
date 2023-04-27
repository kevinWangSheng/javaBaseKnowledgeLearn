package com.io.newFile;



import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author wang
 * @create 2022-2022-22-21:00
 */
public class PathTest {
    @Test
    public void pathTest() throws IOException {

        String path = this.getClass().getResource("").getPath().substring(1);
        System.out.println(path);

        Path basePath = Paths.get(path);

        System.out.println("basePaht:"+basePath);

        Path workPath = Paths.get("CreateFileTest.java");

//        System.out.println(workPath.resolve(workPath));

        Path resolvePath = basePath.resolve(workPath);

        System.out.println(resolvePath);

        System.out.println(Paths.get("wangwu","laoliu","mahua"));

        System.out.println("resolveSibling: "+basePath.resolveSibling("temp"));

        System.out.println("relativize:"+basePath.relativize(resolvePath));

        System.out.println("normal the path is :"+Paths.get("/home/../wanglaowu/lisi///wuliu").normalize());

        System.out.println(Paths.get(basePath+"/CreateFileTest.java").getRoot());

        byte[] bytes = Files.readAllBytes(Paths.get("d://newFile/file3.txt"));

        System.out.println(new String(bytes));

        System.out.println(basePath.getFileSystem().provider());


        Stream<Path> list = Files.walk(basePath);


        Iterator<Path> iterator = list.iterator();

        while (iterator.hasNext()) {
            Path next = iterator.next();

            System.out.println(next);
        }


    }

    @Test
    public void filesTest() throws IOException {
        BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("d://newFile/file3.txt"));

        String line = null;

        while((line=bufferedReader.readLine())!=null)
        {
            System.out.println(line);
        }
    }
    @Test
    public void walkFileTreeTest() throws IOException {
        String basePathName = this.getClass().getResource("").getPath().substring(1);
        System.out.println(basePathName);
        Path basePath = Paths.get(basePathName);
        System.out.println(basePath);

        LinkedList<Path> result = new LinkedList<>();

        Files.walkFileTree(basePath,new FindJavaVistor(result));

        System.out.println("result size "+result.size());

        for(Path p:result)
            System.out.println(p);
    }

    @Test
    public void pictureDelete() throws IOException {
        Path picPath = Paths.get("C:\\Users\\wang sheng hui\\Pictures");

        List<Path> result = new ArrayList<Path>();

        Path path = Files.walkFileTree(picPath, new DeletePictureVistor(result));

        System.out.println("the path is "+path);

        for(Path p:result)
            System.out.println(p);

    }

    @Test
    public void pictureCreateTest() throws IOException {
        Path picPath = Paths.get("C:\\Users\\wang sheng hui\\Pictures");

        Files.walkFileTree(picPath,new PictureCreateVistor());
    }
}
