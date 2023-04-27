package com.io.newFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

/**
 * @author wang
 * @create 2022-2022-22-22:50
 */
public class DeletePictureVistor extends SimpleFileVisitor<Path> {

    private List<Path> list;

    public DeletePictureVistor(List<Path> list) {
        this.list = list;
    }

    @Override
    public FileVisitResult visitFile(Path file,BasicFileAttributes attrs) throws IOException {
        String filePath = file.toFile().getAbsolutePath();

        if(filePath.contains(".jpg")){
            Files.deleteIfExists(file);
            list.add(file);
        }
//        Files.delete(Paths.get("wanglaowu"));


        return FileVisitResult.CONTINUE;

    }
}
