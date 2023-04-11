package io;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() throws IOException {
        List<String> log = new ArrayList<>();
        List<String> answers = readPhrases();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String userQuestion = "Введите слово/фразу: ";
        System.out.println(userQuestion);
        String ln = System.lineSeparator();
        do {
            userQuestion = reader.readLine();
            log.add(userQuestion + ln);
            if (STOP.equals(userQuestion)) {
                System.out.println("Введите 'продолжить', чтобы продолжить: ");
                do {
                    userQuestion = reader.readLine();
                    log.add(userQuestion + ln);
                } while (!CONTINUE.equals(userQuestion) && !OUT.equals(userQuestion));
            }
            if (!OUT.equals(userQuestion)) {
                String answer = answers.get((int) ((answers.size()) * Math.random())) + ln;
                System.out.println(answer);
                log.add(answer);
            }
        } while (!userQuestion.equals(OUT));
        log.add(userQuestion + ln + getCurrentDateTime() + ln);
        saveLog(log);
        reader.close();
    }

    private String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentDateTime.format(formatter);
    }

    private List<String> readPhrases() {
        List<String> result = new ArrayList<>();
        try (BufferedReader bufReader = new BufferedReader(new FileReader(this.botAnswers))) {
            result = bufReader.lines()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.path, true))) {
            for (String line : log) {
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/chatLog.txt", "data/answerCorrect.txt");
        try {
            cc.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

