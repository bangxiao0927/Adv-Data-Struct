/** A small doubly linked list used when displaying the keys in a hash set. */
public class DLList<E> {
    private static class Node<E> {
        private E value;
        private Node<E> previous;
        private Node<E> next;

        Node(E value) {
            this.value = value;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    /** Adds a value at the end of the list. */
    public void add(E value) {
        Node<E> node = new Node<E>(value);
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.previous = tail;
            tail = node;
        }
        size++;
    }

    /** Returns the value at an index. */
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

    /** Returns the number of values in this list. */
    public int size() {
        return size;
    }
}
