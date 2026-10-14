// Items are stored only in nodes, with links in both directions.
public class DLList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DLList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public Node<E> getHead() {
        return head;
    }

    public Node<E> getTail() {
        return tail;
    }

    public boolean add(E data) {
        Node<E> node = new Node<E>(data);
        node.setPrev(tail);
        if (tail == null) {
            head = node;
        } else {
            tail.setNext(node);
        }
        tail = node;
        size++;
        return true;
    }

    public E get(int index) {
        return getNode(index).get();
    }

    public E remove(int index) {
        Node<E> node = getNode(index);
        Node<E> before = node.prev();
        Node<E> after = node.next();
        if (before == null) {
            head = after;
        } else {
            before.setNext(after);
        }
        if (after == null) {
            tail = before;
        } else {
            after.setPrev(before);
        }
        node.setNext(null);
        node.setPrev(null);
        size--;
        return node.get();
    }

    private Node<E> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        // Start at whichever end is closer.
        if (index < size / 2) {
            Node<E> node = head;
            for (int i = 0; i < index; i++) {
                node = node.next();
            }
            return node;
        }
        Node<E> node = tail;
        for (int i = size - 1; i > index; i--) {
            node = node.prev();
        }
        return node;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
}
