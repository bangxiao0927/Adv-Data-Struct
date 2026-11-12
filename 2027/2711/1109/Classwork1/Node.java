import java.io.Serializable;

// One link in the list. It holds a task and points to the nodes before and after it.
// Node is Serializable so the whole list can be saved to a file.
public class Node<E> implements Serializable {
    private static final long serialVersionUID = 1L;
    private E obj;
    private Node<E> next, prev;

    public Node(E obj) {
        this.obj = obj;
        next = null;
        prev = null;
    }

    public Node<E> getNext() {
        return next;
    }

    public Node<E> getPrev() {
        return prev;
    }

    public E getObj() {
        return obj;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public void setPrev(Node<E> prev) {
        this.prev = prev;
    }

    public void setObj(E obj) {
        this.obj = obj;
    }

    public String toString() {
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }
}
