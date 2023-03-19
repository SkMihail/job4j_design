package map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar birthday = Calendar.getInstance();
        User user1 = new User("Alex", 2, birthday);
        int hashCode1 = user1.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int bucket1 = hash1 & 15;
        System.out.printf("Объект user1 имеет hashcode = %d, hash = %d, будет помещен в bucket = %d. %s",
                hashCode1, hash1, bucket1, System.lineSeparator());
        User user2 = new User("Alex", 2, birthday);
        int hashCode2 = user2.hashCode();
        int hash2 = hashCode2 ^ (hashCode2 >>> 16);
        int bucket2 = hash2 & 15;
        System.out.printf("Объект user2 имеет hashcode = %d, hash = %d, будет помещен в bucket = %d. %s",
                hashCode2, hash2, bucket2, System.lineSeparator());
        Map<User, Object> userMap = new HashMap<>(16);
        userMap.put(user1, new Object());
        userMap.put(user2, new Object());
        System.out.println(userMap);
    }
}
