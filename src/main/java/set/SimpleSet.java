package set;

import collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean res = false;
        if (!contains(value)) {
            set.add(value);
            res = true;
        }
        return res;
    }

    @Override
    public boolean contains(T value) {
        boolean res = false;
        for (T x : set) {
            if (value == null || value.equals(x)) {
                res = true;
                break;
            }
        }
        return res;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Set.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Set.super.spliterator();
    }
}