// One link of the list. It holds data and points to the nodes around it.
public class Node<E> {
    private E data;
    private Node<E> next;
    private Node<E> prev;

    public Node(E element) {
        data = element;
        next = null;
        prev = null;
    }

    public E get() {
        return data;
    }

    public Node<E> next() {
        return next;
    }

    public Node<E> prev() {
        return prev;
    }

    public void setData(E element) {
        data = element;
    }

    public void setNext(Node<E> node) {
        next = node;
    }

    public void setPrev(Node<E> node) {
        prev = node;
    }
}
