package io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    private static void checkArgs(String[] args) throws IllegalArgumentException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        Arrays.stream(args).forEach(line -> {
            if (!line.startsWith("-")) {
                throw new IllegalArgumentException(
                        String.format("Error: This argument '%s' does not start with a '-' character", line));
            }
            if (!line.contains("=")) {
                throw new IllegalArgumentException(
                        String.format("Error: This argument '%s' does not contain an equal sign", line));
            }
            if (line.startsWith("-=")) {
                throw new IllegalArgumentException(
                        String.format("Error: This argument '%s' does not contain a key", line));
            }
            if (line.matches("^[^=]*=$")) {
                throw new IllegalArgumentException(
                        String.format("Error: This argument '%s' does not contain a value", line));
            }
        });
    }

    public String get(String key) {
        if (!values.containsKey("-" + key)) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }
        return values.get("-" + key);
    }

    private void parse(String[] args) {
        values.putAll(Arrays.stream(args)
                .collect(Collectors.toMap(x -> x.substring(0, x.indexOf("=")),
                        x -> x.substring(x.indexOf("=") + 1))));
    }

    public static ArgsName of(String[] args) {
        checkArgs(args);
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}