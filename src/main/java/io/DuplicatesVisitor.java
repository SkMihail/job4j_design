package io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private static Map<FileProperty, List<Path>> result = new HashMap<>();

    public void printDuplicates() {
        result.entrySet().stream()
                .filter(x -> x.getValue().size() > 1)
                .forEach(entry ->
                        System.out.println(entry.getKey()
                                + " повторяется в директориях: " + "\n" + entry.getValue()));
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.toFile().getName());
        result.computeIfAbsent(fileProperty, k -> new ArrayList<>()).add(file);
        return super.visitFile(file, attrs);
    }
}
