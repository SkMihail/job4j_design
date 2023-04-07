package kiss;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiPredicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return compareVal(value, (a, b) -> comparator.compare(a, b) > 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return compareVal(value, (a, b) -> comparator.compare(a, b) < 0);
    }

    private  <T> T compareVal(List<T> value, BiPredicate<T, T> predicate) {
        checkValue(value);
        T val = value.get(0);
        for (int i = 1; i < value.size(); i++) {
            T current = value.get(i);
            if (predicate.test(current, val)) {
                val = current;
            }
        }
        return val;
    }

    private <T> void checkValue(List<T> value) {
        if (value == null) {
            throw new NoSuchElementException("Переменная value ссылается на null");
        }
        if (value.isEmpty()) {
            throw new IllegalArgumentException("Переданная коллекция не содержит данных");
        }
        if (value.size() == 1) {
            throw new IllegalArgumentException(
                    String.format("Переданная коллекция содержит один элемент: %s", value.get(0)));
        }
    }
}
