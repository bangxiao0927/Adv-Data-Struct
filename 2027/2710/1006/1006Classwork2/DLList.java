public class DLList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DLList() {
        head = new Node(null);
        tail = new Node(null);
        size = 0;
        head.setNext(tail);
        head.setPrev(null);
        tail.setPrev(head);
        tail.setNext(null);
    }

    public boolean add(E element) {
        Node before = tail.prev();
        Node newNode = new Node(element);
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

    public int size() {
        return size;
    }

    public Node<E> getNode(int index) {
        Node<E> currentNode;
        if (index <= (size/2) ) {
            currentNode = head.next();
            for(int i = 0;i < index; i++) {
                currentNode = currentNode.next();
            }
        } else {
            currentNode = tail.prev();
            for(int i = size - 1; i > index; i--) {
                currentNode = currentNode.prev();
            }
        }
        return currentNode;
    }
}