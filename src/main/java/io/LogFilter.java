package io;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> res = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            res = reader.lines()
                    .filter(str -> {
                        String[] words = str.split(" ");
                        return words.length >= 2 && "404".equals(words[words.length - 2]);
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter writer = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
        ))) {
            for (String line : log) {
                writer.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log.txt");
        System.out.println(log);
        System.out.println(log.size());
        save(log, "data/404.txt");
    }
}
