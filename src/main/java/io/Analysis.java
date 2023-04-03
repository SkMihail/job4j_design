package io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        boolean isAvailable = true;
        String temp;
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            while (reader.ready()) {
                temp = reader.readLine();
                int index = temp.indexOf(" ") + 1;
                if (isAvailable && temp.startsWith("400") | temp.startsWith("500")) {
                    writer.write(temp.substring(index) + ";");
                    isAvailable = false;
                }
                if (!isAvailable && temp.startsWith("200") | temp.startsWith("300")) {
                    writer.write(temp.substring(index)  + ";" + System.lineSeparator());
                    isAvailable = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}