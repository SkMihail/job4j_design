package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

public class CSVReader {

    private static ArgsName validate(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        if (!new File(argsName.get("path")).exists()) {
            throw new IllegalArgumentException(String.format("Некорректно указан источник: %s", argsName.get("path")));
        }
        if (!new File(argsName.get("out")).exists()) {
            throw new IllegalArgumentException(String.format("Некорректно указан путь назначения: %s", argsName.get("out")));
        }
        argsName.get("delimiter");
        argsName.get("filter");
        return argsName;
    }

    public static void handle(ArgsName argsName) throws Exception {
        try (Scanner scanner = new Scanner(new FileInputStream(argsName.get("path")));
             PrintStream print = new PrintStream(new FileOutputStream(argsName.get("out")))) {
            String delimiter = argsName.get("delimiter");
            String[] filterArray = argsName.get("filter").split(",");

            List<String> resultCSV = new ArrayList<>();
            StringBuilder lineCSV = new StringBuilder();
            for (String parameter : filterArray) {
                lineCSV.append(parameter).append(delimiter);
            }
            resultCSV.add(lineCSV.substring(0, lineCSV.length() - delimiter.length()));

            String[] header = null;
            while (scanner.hasNext()) {
                String[] nextCSVLine = scanner.nextLine().split(delimiter);
                if (header == null) {
                    header = nextCSVLine;
                } else {
                    lineCSV.setLength(0);
                    for (String parameter : filterArray) {
                        lineCSV.append(nextCSVLine[getIndexParam(parameter, header)]).append(delimiter);
                    }
                    resultCSV.add(lineCSV.substring(0, lineCSV.length() - delimiter.length()));
                }
            }
            for (String line : resultCSV) {
                print.println(line);
            }
        }
    }

    private static int getIndexParam(String parameter, String[] header) throws Exception {
        for (int i = 0; i < header.length; i++) {
            if (parameter.equals(header[i])) {
                return i;
            }
        }
        throw new Exception("Header not found.");
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException(String.format("Must be 4 arguments, but was:  %d", args.length));
        }
        ArgsName argsName = validate(args);
        handle(argsName);
    }
}
