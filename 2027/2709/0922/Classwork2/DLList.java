// Doubly linked list. head is the first node and tail is the last one.
public class DLList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DLList() {
        head = new Node<E>(null);
        tail = new Node<E>(null);
        head.setNext(tail);
		head.setPrev(null);
		tail.setNext(null);
		tail.setPrev(head);
        size = 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index " + index + ", size " + size);
        }
    }

    // walks from the closer end to get to the node at index
    private Node<E> getNode(int index) {
        checkIndex(index);
        Node<E> cur;
        if (index < size / 2) {
            cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next();
            }
        } else {
            cur = tail;
            for (int i = size - 1; i > index; i--) {
                cur = cur.prev();
            }
        }
        return cur;
    }

    // takes a node out of the chain and gives back its data
    private E unlink(Node<E> node) {
        if (node.prev() == null) {
            head = node.next();
        } else {
            node.prev().setNext(node.next());
        }
        if (node.next() == null) {
            tail = node.prev();
        } else {
            node.next().setPrev(node.prev());
        }
        size--;
        return node.get();
    }

    public boolean add(E element) {
        Node<E> newNode = new Node<E>(element);
        Node<E> before = tail.prev();
        Node<E> after = tail;
        before.setNext(newNode);
        newNode.setNext(after);
        newNode.setPrev(before);
        after.setPrev(newNode);

        size++;
        return true;
    }

    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index " + index + ", size " + size);
        }

        Node<E> current;
        if (index <= size / 2) {
            // Search from the front
            current = head.next();          // first real node (index 0)
            for (int j = 0; j < index; j++) {
                current = current.next();
            }
        } else {
            // Search from the back
            current = tail.prev();          // last real node (index size-1)
            for (int j = size - 1; j > index; j--) {
                current = current.prev();
            }
        }

        Node<E> newNode = new Node<E>(element);
        Node<E> before = current.prev();
        before.setNext(newNode);
        newNode.setPrev(before);
        newNode.setNext(current);
        current.setPrev(newNode);

        size++;
    }

    public E get(int index) {
        return getNode(index).get();
    }

    public E set(int index, E element) {
        Node<E> node = getNode(index);
        E old = node.get();
        node.setData(element);
        return old;
    }

    public boolean contains(Object obj) {
        Node<E> cur = head;
        while (cur != null) {
            if (cur.get().equals(obj)) {
                return true;
            }
            cur = cur.next();
        }
        return false;
    }

    public E remove(int index) {
        return unlink(getNode(index));
    }

    public boolean remove(Object obj) {
        Node<E> cur = head;
        while (cur != null) {
            if (cur.get().equals(obj)) {
                unlink(cur);
                return true;
            }
            cur = cur.next();
        }
        return false;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    // every element already ends with a new line, so they stack up one per line
    public String toString() {
        String result = "";
        Node<E> cur = head;
        while (cur != null) {
            result += cur.get();
            cur = cur.next();
        }
        return result;
    }
}
