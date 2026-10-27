// Doubly linked list. head and tail are empty sentinel nodes, so the real data
// lives between them: head.next() is index 0 and tail.prev() is index size-1.
public class DLList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DLList() {
        head = new Node<E>(null);
        tail = new Node<E>(null);
        size = 0;
        head.setNext(tail);
        head.setPrev(null);
        tail.setPrev(head);
        tail.setNext(null);
    }

    // The sentinels. A walk starts at getHead().next() and stops at getTail().
    public Node<E> getHead() {
        return head;
    }

    public Node<E> getTail() {
        return tail;
    }

    public boolean add(E element) {
        Node<E> before = tail.prev();
        Node<E> newNode = new Node<E>(element);
        before.setNext(newNode);
        newNode.setPrev(before);
        newNode.setNext(tail);
        tail.setPrev(newNode);
        size++;
        return true;
    }

    public E get(int index) {
        return getNode(index).get();
    }

    public E remove(int index) {
        Node<E> currentNode = getNode(index);
        E result = currentNode.get();
        Node<E> before = currentNode.prev();
        Node<E> after = currentNode.next();
        before.setNext(after);
        after.setPrev(before);
        size--;
        return result;
    }

    public void clear() {
        // The old nodes are simply left for the garbage collector.
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }

    public int size() {
        return size;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index " + index + ", size " + size);
        }
    }

    // Walks from the closer end to get to the node at index.
    private Node<E> getNode(int index) {
        checkIndex(index);
        Node<E> currentNode;
        if (index <= (size / 2)) {
            currentNode = head.next();
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next();
            }
        } else {
            currentNode = tail.prev();
            for (int i = size - 1; i > index; i--) {
                currentNode = currentNode.prev();
            }
        }
        return currentNode;
    }
}
