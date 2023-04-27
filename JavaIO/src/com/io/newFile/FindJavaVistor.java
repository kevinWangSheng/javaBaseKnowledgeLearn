package com.io.newFile;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

/**
 * @author wang
 * @create 2022-2022-22-22:42
 */
public class FindJavaVistor extends SimpleFileVisitor<Path> {
    private List<Path> listFile;

    public FindJavaVistor(List<Path> listFile) {
        this.listFile = listFile;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if(file.toString().endsWith(".class")){
            listFile.add(file.getFileName());
        }

        return FileVisitResult.CONTINUE;
    }
}
