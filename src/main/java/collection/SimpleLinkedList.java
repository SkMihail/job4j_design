package collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size = 0;
    private int modCount = 0;

    private Node<E> head;


    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;
        } else {
            Node<E> l = head;
            while (l.next != null) {
                l = l.next;
            }
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> x = head;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x.item;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<>() {
            private Node<E> x = head;

            private final int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                return  x != null;
            }

            @Override
            public E next() {
                E item = x.item;
                x = x.next;
                return item;
            }
        };
    }

    private static class Node<E> {

        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        SimpleLinkedList<Integer> li = new SimpleLinkedList<>();
        li.add(5);
        li.add(2);
        li.add(6);
        li.add(7);
        li.add(8);
        System.out.println(li.get(4));
    }
}
