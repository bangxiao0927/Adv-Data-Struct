// Doubly linked list. head is the first node and tail is the last one.
public class DLList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DLList() {
        head = null;
        tail = null;
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

    public boolean add(E element) {
        Node<E> newNode = new Node<E>(element);
        if (tail == null) {
            head = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
        }
        tail = newNode;
        size++;
        return true;
    }

    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index " + index + ", size " + size);
        }
        if (index == size) {
            add(element);
            return;
        }
        Node<E> cur = getNode(index);
        Node<E> newNode = new Node<E>(element);
        newNode.setNext(cur);
        newNode.setPrev(cur.prev());
        if (cur.prev() == null) {
            head = newNode;
        } else {
            cur.prev().setNext(newNode);
        }
        cur.setPrev(newNode);
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
        Node<E> node = getNode(index);
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

    public int size() {
        return size;
    }

    // a space after every element so the numbers do not run together
    public String toString() {
        String result = "";
        Node<E> cur = head;
        while (cur != null) {
            result += cur.get() + " ";
            cur = cur.next();
        }
        return result;
    }
}
