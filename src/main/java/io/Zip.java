package io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static io.Search.search;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream forZip = new BufferedInputStream(new FileInputStream(source.toString()))) {
                    zip.write(forZip.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            System.out.println(source.getPath());
            try (BufferedInputStream forZip = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(forZip.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validateArgs(ArgsName inputArgs) {
        File dirForZip = new File(inputArgs.get("d"));
        if (!dirForZip.exists()) {
            throw new NoSuchElementException(String.format("Directory %s is not exist", dirForZip));
        }
        if (inputArgs.get("e").charAt(0) != '.') {
            throw new IllegalArgumentException(String.format("Incorrect file extension %s ", inputArgs.get("e")));
        }
        if (!inputArgs.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException(String.format("Wrong name for zip file:  %s", inputArgs.get("o")));
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        ArgsName argsForZip = ArgsName.of(args);
        validateArgs(argsForZip);
        List<Path> toZip = search(Path.of(argsForZip.get("d")),
                x -> !x.toFile().getName().endsWith(argsForZip.get("e")));
        zip.packFiles(toZip, new File(argsForZip.get("o")));
    }
}
