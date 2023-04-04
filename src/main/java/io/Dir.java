package io;

import java.io.File;
import java.util.Arrays;

public class Dir {
    public static void main(String[] args) {
        File projects = new File("c:\\projects\\job4j_design");
        if (!projects.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", projects.getAbsoluteFile()));
        }
        if (!projects.isDirectory()) {
            throw new IllegalArgumentException(String.format("Is not a directory %s", projects.getAbsolutePath()));
        }
        System.out.printf("Total space on disk: %,d KB %n", projects.getTotalSpace() / 1024);
        String delimiter = System.lineSeparator();
        System.out.printf("Директория %s содержит следующие файлы и папки: %s", projects, delimiter);
        File[] directoryFiles = projects.listFiles();
        Arrays.sort(directoryFiles, (o1, o2) -> {
            if (o1.isDirectory() && o2.isFile()) {
                return -1;
            } else if (o1.isFile() && o2.isDirectory()) {
                return 1;
            } else {
                return o1.compareTo(o2);
            }
        });
        for (File subFiles : directoryFiles) {
            if (subFiles.isDirectory()) {
                System.out.printf("\tдиректория %s%s", subFiles.getName(), delimiter);
            } else {
                System.out.printf("\tФайл \t%-15s \tразмер %,10d KB%s", subFiles.getName(), subFiles.length(), delimiter);
            }

        }
    }
}
