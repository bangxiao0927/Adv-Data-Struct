// Application and Creation
// Classwork
// Individually, write the DLList class as practice for the quiz.

// DLList<E> Class
// Instance Variables
// - head : Node<E>
// - tail : Node<E>
// - size : int
// Constructor
// + DLList - Instantiate the head and tail nodes. Connect them.  Set size to 0.
// Methods
// + add(E) : boolean - Add an element at the end of the list.  Return true.
// + get(int) : E - Return the element at a specific location. Need to utilize getNode(int).
// + remove(int) : E - Remove the element given the index.  Return the element removed. Need to utilize getNode(int).
// + size() : int - Return the size.
// - getNode(int) : Node<E> - Returns the node given an index.  Depending on the index, it will traverse forwards or backwards.

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