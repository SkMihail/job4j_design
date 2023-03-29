package io;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EvenNumberFile {
    private static List<Integer> numReaderFromFile(String path) {
        StringBuilder fileStuff = new StringBuilder();
        try (FileInputStream in = new FileInputStream(path)) {
            int read;
            while ((read = in.read()) != -1) {
                fileStuff.append((char) read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         return Stream.of(fileStuff.toString())
                .map(x -> x.split(System.lineSeparator()))
                .flatMap(Arrays::stream)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static void printEvenOrOdd(List<Integer> numList) {
        String res;
        for (Integer number : numList) {
            res = number % 2 == 0
                    ? number + " even"
                    : number + " odd";
            System.out.println(res);
        }
    }

    public static void main(String[] args) {
        printEvenOrOdd(numReaderFromFile("data/even.txt"));
    }
}
