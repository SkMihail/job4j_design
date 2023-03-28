package io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void multipleToFile(int size) {
        try (FileOutputStream out = new FileOutputStream("data/dataresult.txt")) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    out.write(Integer.toString((i + 1) * (j + 1)).getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        multipleToFile(8);
    }
}
