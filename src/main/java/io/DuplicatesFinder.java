package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void printDup(Path file) throws IOException {
        Files.walkFileTree(file, new DuplicatesVisitor());
    }

    public static void main(String[] args) throws IOException {
        printDup(Path.of("C:\\projects"));
    }
}
