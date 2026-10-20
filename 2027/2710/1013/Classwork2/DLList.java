import java.util.Iterator;
import java.util.NoSuchElementException;

// A doubly linked list used to store the hash set's elements.
public class DLList<E> implements Iterable<E> {
    private static class Node<E> {
        private E value;
        private Node<E> previous;
        private Node<E> next;

        private Node(E value) {
            this.value = value;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    // Adds an item to the end of the list.
    public void add(E value) {
        Node<E> node = new Node<E>(value);
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            node.previous = tail;
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        Node<E> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.previous;
            }
        }
        return current.value;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = current.value;
                current = current.next;
                return value;
            }
        };
    }
}
