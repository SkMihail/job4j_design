package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int rowPoint = 0;
    private int columnPoint = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (rowPoint < data.length && columnPoint == data[rowPoint].length) {
            rowPoint++;
            columnPoint = 0;
        }
        return rowPoint < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Array is already read");
        }
        return data[rowPoint][columnPoint++];
    }
}
