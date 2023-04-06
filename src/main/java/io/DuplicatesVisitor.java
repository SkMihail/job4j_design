package io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private static Map<Path, FileProperty> result = new HashMap<>();

    public Map<Path, FileProperty> getResult() {
        return result;
    }


    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        result.put(file, new FileProperty(attrs.size(), file.toFile().getName()));
        return super.visitFile(file, attrs);
    }
}
