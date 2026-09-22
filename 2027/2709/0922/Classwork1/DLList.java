public class DLList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DLList() { }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
    }

    private Node<E> getNode(int index) {
        checkElementIndex(index);
        if (index < size / 2) {
            Node<E> current = head;
            for (int i = 0; i < index; i++) current = current.next();
            return current;
        }
        Node<E> current = tail;
        for (int i = size - 1; i > index; i--) current = current.prev();
        return current;
    }

    public boolean add(E element) {
        Node<E> newNode = new Node<>(element);
        if (tail == null) head = tail = newNode;
        else { tail.setNext(newNode); newNode.setPrev(tail); tail = newNode; }
        size++;
        return true;
    }

    public void add(int index, E element) {
        checkPositionIndex(index);
        if (index == size) { add(element); return; }
        Node<E> current = getNode(index);
        Node<E> newNode = new Node<>(element);
        newNode.setNext(current);
        newNode.setPrev(current.prev());
        if (current.prev() == null) head = newNode;
        else current.prev().setNext(newNode);
        current.setPrev(newNode);
        size++;
    }

    public E get(int index) { return getNode(index).get(); }

    public E set(int index, E element) {
        Node<E> target = getNode(index);
        E oldData = target.get();
        target.setData(element);
        return oldData;
    }

    public boolean contains(Object object) {
        Node<E> current = head;
        while (current != null) {
            if (object == null ? current.get() == null : object.equals(current.get())) return true;
            current = current.next();
        }
        return false;
    }

    public boolean remove(Object object) {
        Node<E> current = head;
        while (current != null) {
            if (object == null ? current.get() == null : object.equals(current.get())) {
                unlink(current);
                return true;
            }
            current = current.next();
        }
        return false;
    }

    public E remove(int index) { return unlink(getNode(index)); }

    private E unlink(Node<E> target) {
        Node<E> previous = target.prev();
        Node<E> next = target.next();
        if (previous == null) head = next; else previous.setNext(next);
        if (next == null) tail = previous; else next.setPrev(previous);
        size--;
        return target.get();
    }

    public void clear() { head = null; tail = null; size = 0; }
    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<E> current = head;
        while (current != null) {
            result.append(current.get());
            if (current.next() != null) result.append(", ");
            current = current.next();
        }
        return result.append("]").toString();
    }
}
