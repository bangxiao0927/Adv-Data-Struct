public class DLList {
    private Node<E> head;
    private Node<E> tail;
    private int size;
    
    public DLList{
        head = null;
        tail = null;
        size = 0;
    }

    private Node<E> getNode(int ind) {
        Node<E> curr;
        if (ind < size / 2) {
            curr = head;
            for (int i = 0; i < ind; i++) {
                curr = curr.next;
            }
        } else {
            curr = tail;
            for (int i = size - 1; i > ind; i--) {
                curr = curr.prev;
            }
        }
        return curr;
    }

    public boolean add(E ele){
        Node<E> newNode = new Node<>(e);

        if (head == null){
            head = tail = newNode;
        } else{
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        return true;
    }

    public void add(int ind, E ele){
        if (ind == 0){
            Node<E> newNode = newNode<>(e);
            
            if (head == null){
                head = tail = newNode;
            } else{
                newNode.next = head;
                head.prv = newNode;
                head = newNode;
            }
        } else if (ind == size){
            add(e);
            return;
        } else{
            Node<E> current = getNode(ind);
            Node<E> newNode = new Node<>(e);

            newNode.prev = current.prev;
            newNode.next = current;
            current.prev.next = newNode;
            current.prev = newNode;
        }
        size++;
    }

    //idk
    public E get(int ind){
        return getNode(ind).data;
    }

    public boolean contains(Object o){
        Node<E> current = head;
        while (current != null){
            if (o == null){
                if (current.data == null){
                    return true;
                }
            } else{
                if (o.equals(current.data)){
                    return true;
                }
            }
            current = current.next;
        }
        return false;
    }
    public E remove(int ind){
        Node<E> target = getNode(ind);
        E oldData = target.data;

        if (size == 1) {
            head = null;
            tail = null;
        } else if (target == head) {
            head = head.next;
            head.prev = null;
        } else if (target == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            target.prev.next = target.next;
            target.next.prev = target.prev;
        }
        size--;
        return oldData;
    }

    public int size(){
        return size;
    }

    //idk
    public E set(int ind, E ele){
        Node<E> target = getNode(ind);
        E oldData = target.data;
        target.data = element;
        return oldData;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.data);
            if (curr.next != null) {
                sb.append(", ");
            }
            curr = curr.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
