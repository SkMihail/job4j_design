package map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        int index = key != null ? indexFor(hash(key.hashCode())) : 0;
        boolean res = false;
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            res = true;
            count++;
            modCount++;
        }
        return res;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & capacity - 1;
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] temp = table;
        table = new MapEntry[capacity];
        count = 0;
        for (MapEntry<K, V> kvMapEntry : temp) {
            if (kvMapEntry != null) {
                put(kvMapEntry.key, kvMapEntry.value);
            }
        }
    }

    @Override
    public V get(K key) {
        V result = null;
        if (key == null) {
            result = table[0] != null && table[0].key == null ? table[0].value : null;
        } else {
            int hashKey = hash(key.hashCode());
            int index = indexFor(hashKey);
            if (table[index] != null) {
                K keyFromTable = table[index].key;
                int hashKeyFromTable = keyFromTable != null ? hash(keyFromTable.hashCode()) : 0;
                if (hashKeyFromTable == hashKey && key.equals(keyFromTable)) {
                    result = table[index].value;
                }
            }
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean res;
        if (key != null) {
            int hashKey = hash(key.hashCode());
            int index = indexFor(hashKey);
            res = table[index] != null;
            table[index] = null;
        } else {
            res = table[0] != null;
            table[0] = null;
        }
        count--;
        modCount++;
        return res;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int point = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (point < table.length && table[point] == null) {
                    point++;
                }
                return point < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        StringJoiner joiner = new StringJoiner(", ", "{", "}");
        for (MapEntry<K, V>  mapEntry : table) {
            if (mapEntry != null) {
                result.setLength(0);
                result.append(mapEntry.key).append(" = ").append(mapEntry.value);
                joiner.add(result);
            }
        }
        return joiner.toString();
    }

    public static void main(String[] args) {
        SimpleMap<Integer, String> myMap = new SimpleMap<>();
        myMap.put(null, "0000");
        myMap.put(16, "16**********");
        myMap.put(1, "1*");
        myMap.put(4, "4****");
        System.out.println(myMap);
    }
}
