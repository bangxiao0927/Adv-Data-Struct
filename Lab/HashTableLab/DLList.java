class Node<E> {
    private E obj;
    private Node<E> next, prev;
    
    public Node(E obj) {
        // make obj
        this.obj = obj;

        // set next and prev to null
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
        try {
            return obj.toString();
        } catch (NullPointerException e) {
            return null;
        }
    }
}

public class DLList<E> {
    public Node<E> head, tail;
    private int size;

    public DLList() {
        head = new Node<E> (null);
        tail = new Node<E> (null);

        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }

    public void add(E obj) {
        Node<E> newNode = new Node<E> (obj);
        
        newNode.setPrev(tail.getPrev());
        newNode.setNext(tail);
        tail.getPrev().setNext(newNode);
        tail.setPrev(newNode);

        size++;
    }

    public void add(int i, E obj) {
        if (size == 0 || i == size) {
            add(obj);
            return;
        }

        Node<E> newNode = new Node<E> (obj);
        Node<E> current = getNode(i);

        newNode.setPrev(current.getPrev());
        newNode.setNext(current);
        current.getPrev().setNext(newNode);
        current.setPrev(newNode);

        size++;
    }

    public E get(int i) {
        return getNode(i).getObj();
    }

    public Node<E> getHead() {
        return head;
    }

    public Node<E> getNode(int i) {
        int halfSize = (size + 1) / 2;
        
        if (i == 0) {
            return head.getNext();
        }
        if (i == size - 1) {
            return tail.getPrev();
        }
        
        Node<E> current;
        if (i < halfSize) {
            current = head;
            for (int j = 0; j <= i; j++) {
                current = current.getNext();
            }
        } else {
            current = tail;
            for (int j = size; j > i; j--) {
                current = current.getPrev();
            }
        }

        return current;
        
    }

    public E remove(int i) {
        Node<E> current = getNode(i);
        E tempObj = current.getObj();

        current.getPrev().setNext(current.getNext());
        current.getNext().setPrev(current.getPrev());
        current.setNext(null);
        current.setPrev(null);
        size--;
        
        return tempObj;
    }

    public int size() {
        return size;
    }

    public void set(int i, E obj) {
        getNode(i).setObj(obj);
    }
}
