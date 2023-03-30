package io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

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
        return fileStuff.toString().lines()
                .map(Integer::parseInt)
                .toList();
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
