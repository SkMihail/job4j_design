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
        while (rowPoint < data.length && data[rowPoint].length == 0) {
            rowPoint++;
        }
        return rowPoint < data.length && columnPoint < data[rowPoint].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Array is already read");
        }
        int rsl = data[rowPoint][columnPoint++];
        if (columnPoint == data[rowPoint].length) {
            rowPoint++;
            columnPoint = 0;
        }
        return rsl;
    }
}
