package collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RevertLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public boolean revert() {
        boolean success = head != null && head.next != null;
        if (success) {
            Node<T> prev = null;
            while (head != null) {
                Node<T> after = head.next;
                head.next = prev;
                prev = head;
                head = after;
            }
            head = prev;
        }
        return success;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        RevertLinked<Integer> li = new RevertLinked<>();
        li.add(1);
        li.add(2);
        li.add(3);
       Iterator<Integer> it = li.iterator();
        System.out.println(it.next());
        li.revert();
        it = li.iterator();
        System.out.println(it.next());


    }
}