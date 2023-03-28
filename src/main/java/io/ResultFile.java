package io;

import java.io.FileOutputStream;
import java.util.Arrays;

public class ResultFile {
    public static int[][] multiple(int size) {
        int[][] array = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                array[i][j] = (i + 1) * (j + 1);
            }
        }
        return array;
    }
    public static void main(String[] args) {

        try (FileOutputStream out = new FileOutputStream("data/dataresult.txt")) {
            for (int[] array : multiple(5)) {
                out.write(Arrays.toString(array).getBytes());
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
