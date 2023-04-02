package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.StringJoiner;


public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    private static  void checkLine(String line) throws IllegalArgumentException {
        if (!line.contains("=") || line.startsWith("=") || line.matches("^[^=]*=$")) {
            throw new IllegalArgumentException("Line contains an invalid template");
        }
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            values.putAll(reader.lines()
                    .filter(x -> !x.startsWith("#") && !x.isEmpty())
                    .peek(Config::checkLine)
                    .collect(Collectors.toMap(x -> x.substring(0, x.indexOf("=")),
                            x -> x.substring(x.indexOf("=") + 1))));
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
