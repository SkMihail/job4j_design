package kiss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

class MaxMinTest {
    List<Integer> list;
    MaxMin tester;
    @BeforeEach
    public void setUp() {
        list = List.of(10, 2, 45, 24, 7);
        tester = new MaxMin();

    }

    @Test
    public void whenMin() {
        int expected = 2;
        int result = tester.min(list, Integer::compareTo);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenMax() {
        int expected = 45;
        int result = tester.max(list, Integer::compareTo);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenListEmpty() {
        list = new ArrayList<>();
        assertThatThrownBy(() -> tester.max(list, Integer::compareTo))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Переданная коллекция не содержит данных");
    }

    @Test
    void whenListNull() {
        list = null;
        assertThatThrownBy(() -> tester.max(list, Integer::compareTo))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Переменная value ссылается на null");
    }

    @Test
    void whenListHasOneElement() {
        list = List.of(10);
        assertThatThrownBy(() -> tester.max(list, Integer::compareTo))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Переданная коллекция содержит один элемент: 10");
    }

}