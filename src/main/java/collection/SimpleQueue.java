package collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    private int inSize = 0;
    private int outSize = 0;

    public T poll() {
        if (outSize != 0) {
            outSize--;
            return out.pop();
        }
        if (inSize != 0) {
            while (inSize != 1) {
              out.push(in.pop());
                inSize--;
                outSize++;
            }
            inSize--;
            return in.pop();
        }
        throw new NoSuchElementException();
    }

    public void push(T value) {
        in.push(value);
        inSize++;
    }
}

