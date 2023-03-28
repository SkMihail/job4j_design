package question;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        HashSet<User> addedOrChanged = new HashSet<>(current);
        addedOrChanged.removeAll(previous);
        HashMap<Integer, User> addedOrChangedMap = new HashMap<>();
        for (User user : addedOrChanged) {
            addedOrChangedMap.put(user.id(), user);
        }
        int changed = 0;
        int add = addedOrChanged.size();
        HashSet<User> deletedOrChanged = new HashSet<>(previous);
        deletedOrChanged.removeAll(current);
        for (User userDelOrCh : deletedOrChanged) {
            if (addedOrChangedMap.containsKey(userDelOrCh.id())) {
                changed++;
                add--;
            }
        }
        int del = deletedOrChanged.size() - changed;
        return new Info(add, changed, del);
    }
}
