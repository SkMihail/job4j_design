package io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private static Map<Path, FileProperty> result = new HashMap<>();

    public void printDuplicates() {
        Map<FileProperty, List<Path>> duplicates = result.entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue,
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())));

        duplicates.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .forEach(entry ->
                        System.out.println(entry.getKey()
                                + " повторяется в директориях: " + "\n" +  entry.getValue()));
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        result.put(file, new FileProperty(attrs.size(), file.toFile().getName()));
        return super.visitFile(file, attrs);
    }
}
