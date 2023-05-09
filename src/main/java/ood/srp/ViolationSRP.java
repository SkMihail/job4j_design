package ood.srp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import org.apache.log4j.Logger;
import question.User;

/** Данный код демонстрирует нарушение первого принципа SOLID
 * - Single Responsibility Principle
 * Можно выделить нарушения:
 * -- God Object, т.к. класс реализует большое количество задач (работа с пользователями,
 * данными из файла, логированием, физическими параметрами системы, выводом на консоль)
 * -- Feature Envy т.к. метод printUserDetails() обращается только к методам класса User,
 * и логичнее должен быть расположен в классе User.
 * -- метод getRandomWord демонстрирует реализацию метода который выполняет несколько задач:
 * читает данные из файла и возвращает случайное слово.
 */
public class ViolationSRP {
    private List<String> data = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    private static final Logger LOGGER = Logger.getLogger(ViolationSRP.class.getName());

    public void logData() {
        LOGGER.info("Data printed: " + data);
    }

    public void fillData() throws IOException {
        data.add(getRandomWord(Path.of("C:\\projects\\job4j_design\\data\\answerCorrect.txt"),
                new User(5, "Petr")));
    }

    public String getRandomWord(Path path, User user) throws IOException {
        if (!users.contains(user)) {
            throw new IllegalArgumentException();
        }
        List<String> words = Files.readAllLines(path);
        Random random = new Random();
        int randomIndex = random.nextInt(words.size());
        return words.get(randomIndex);
    }

    public void printData() {
        data.forEach(System.out::println);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public static double getCpuTemperature() {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        return osBean.getCpuLoad();
    }

    public void printUserDetails(User user) {
        String userDetails = "User ID: " + user.id() + ", Name: " + user.name();
        System.out.println(userDetails);
    }
}



