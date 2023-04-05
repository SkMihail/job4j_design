package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    private static void validateInput(String... args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Введено неверное количество аргументов");
        }
        if (!Paths.get(args[0]).toFile().exists()) {
            throw new IllegalArgumentException(String.format("Некорректно указан путь: %s", args[0]));
        }
        if (!args[1].matches("\\.[a-zA-Z]*")) {
            throw new IllegalArgumentException(String.format("Некорректно указано раширение: %s ", args[1]));
        }
    }
    public static void main(String[] args) throws IOException {
        validateInput(args);
        Path start = Paths.get(args[0]);
        System.out.println(start);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
