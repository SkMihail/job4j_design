package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.StringJoiner;
import java.util.stream.Stream;


public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    private static Map<String, String> checkAndPrepareLine(String line) throws IllegalArgumentException {
        Map<String, String> res = Stream.of(line)
                .filter(x -> !x.startsWith("#") && !x.isEmpty())
                .peek(x -> {
                    if (!x.contains("=")) {
                        throw new IllegalArgumentException("Line contains an invalid template");
                    }
                })
                .collect(Collectors.toMap(x -> x.substring(0, x.indexOf("=")),
                        x -> x.substring(x.indexOf("=") + 1)));
        if (res.containsKey("") || res.containsValue("")) {
            throw new IllegalArgumentException("Line contains an invalid template");
        }
        return res;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines()
                    .forEach(x -> values.putAll(checkAndPrepareLine(x)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
        Config configurator = new Config("data/app.properties");
        configurator.load();
        System.out.println(configurator.values);
    }
}
