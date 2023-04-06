package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class DuplicatesFinder {
    public static void printDup(String dir) throws IOException {
        Path file = Path.of(dir);
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(file, visitor);
        Map<FileProperty, List<Path>> duplicates = visitor.getResult().entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue,
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())));

        duplicates.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .forEach(entry ->
                        System.out.println(entry.getKey()
                                + " повторяется в директориях: " + "\n" +  entry.getValue()));
        System.out.printf("Всего повторяющихся файлов: %d", duplicates.size());
    }

    public static void main(String[] args) throws IOException {
        printDup("c:\\projects");
    }
}
