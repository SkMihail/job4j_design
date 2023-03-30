package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> res = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            res = reader.lines()
                    .filter(str -> {
                        String[] words = str.split(" ");
                        return words[words.length - 2].equals("404");
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log.txt");
        System.out.println(log);
        System.out.println(log.size());
    }
}
