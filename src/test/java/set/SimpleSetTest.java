package set;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleSetTest {

    @Test
    void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
    }

    @Test
    void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }
    @Test
    void whenUseIteratorTrue() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(null)).isTrue();
        Iterator<Integer> iterator = set.iterator();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    void whenUseNextIterator() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(null)).isTrue();
        Iterator<Integer> iterator = set.iterator();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isNull();
    }

    @Test
    void whenUseNextNextIterator() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(null)).isTrue();
        assertThat(set.add(3)).isTrue();
        assertThat(set.add(2)).isTrue();
        Iterator<Integer> iterator = set.iterator();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isNull();
        assertThat(iterator.next()).isEqualTo(3);
    }

    @Test
    void whenUseHasNextIteratorAndFalse() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(null)).isTrue();
        assertThat(set.add(3)).isTrue();
        Iterator<Integer> iterator = set.iterator();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isNull();
        assertThat(iterator.next()).isEqualTo(3);
        assertThat(iterator.hasNext()).isFalse();
    }
}