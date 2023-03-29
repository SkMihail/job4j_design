package io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EvenNumberFile {
    private static List<Integer> numReaderFromFile(String path) {
        StringBuilder fileStuff = new StringBuilder();
        try (FileInputStream in = new FileInputStream(path)) {
            int read;
            while ((read = in.read()) != -1) {
                fileStuff.append((char) read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
         return Arrays.stream(fileStuff.toString().split(System.lineSeparator()))
                 .mapToInt(Integer::parseInt)
                 .boxed()
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
