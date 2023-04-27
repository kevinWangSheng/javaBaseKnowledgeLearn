package com.io.newFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author wang
 * @create 2022-2022-22-23:18
 */
public class PictureCreateVistor extends SimpleFileVisitor<Path> {

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        int width = 224;
        int hight = 300;

        String filePath = ((Path) file).toFile().getPath();

        File newFile = new File(filePath  + "3.jpg");

        if(!newFile.exists())
        {
            newFile.createNewFile();
        }


        return FileVisitResult.CONTINUE;
    }
}
