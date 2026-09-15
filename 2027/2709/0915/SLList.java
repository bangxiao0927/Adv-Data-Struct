
public class SLList<E> {
    private Node<E> head;
    private int size;

    public SLList(){
        head = null;
        size = 0;
    }

    public E get(int loc){
        checkIndex(loc);
        Node<E> current = head;
        for (int i = 0; i < loc; i++){
            current = current.next();
        }
        return current.get();
    }

    public boolean contains(Object ele){
        Node<E> current = head;
        while (current != null){
            if ((ele == null && current.get() == null) //data and the node is null, reached end
                    || (current.get() != null && current.get().equals(ele))) { //node is not null and the current have data
                return true;
            }
            current = current.next();
        }
        return false;
    }

    public boolean add(E ele){
        Node<E> resultNode = new Node<E>(ele);

        if (head == null){
            head = resultNode;
        }
        else{
            Node<E> current = head;
            while(current.next() != null){
                current = current.next();
            }
            current.setNext(resultNode);
        }

        size++;
        return true;
    }

    public void add(int ind, E ele){
        if (ind < 0 || ind > size) {
            throw new IndexOutOfBoundsException("Index: " + ind + ", size: " + size);
        }

        Node<E> resultNode = new Node<E>(ele);

        if (ind == 0){
            resultNode.setNext(head);
            head = resultNode;
        } else{
            Node<E> current = head;
            for (int i = 0; i < ind-1; i++){
                current = current.next();
            }

            resultNode.setNext(current.next());
            current.setNext(resultNode);
        }
        size++;
    }

    public E remove(int ind){
        checkIndex(ind);
        E removed;

        if (ind == 0){
            removed = head.get();
            head = head.next();
        }

        else{
            Node<E> current = head;
            for (int i = 0; i < ind -1; i++){
                current = current.next();
            }

            removed = current.next().get();
            current.setNext(current.next().next());
        }
        size--;
        return removed;
    }

    public boolean remove(Object obj){
        if (head == null) return false;

        if (head.get() != null && head.get().equals(obj)){
            head = head.next();
            size--;
            return true;
        }

        Node<E> current = head;
        while (current.next() != null){
            if ((current.next().get() == null && obj == null)
                    || (current.next().get() != null
                    && current.next().get().equals(obj))){
                current.setNext(current.next().next());
                size--;
                return true;
            }
            current = current.next();
        }
        return false;
    }

    public E set(int ind, E element){
        checkIndex(ind);
        Node<E> current = head;
        for (int i = 0; i < ind; i++){
            current = current.next();
        }

        E old = current.get();
        current.setData(element);
        return old;
    }

    public int size(){
        return size;
    }

    //idek
    public String toString(){
        if (head == null){
            return "[]";
        } 

        String result = "[";
        Node<E> current = head;

        while (current != null){
            result += current.get();
            if (current.next() != null){
                result += ", ";
            }

            current = current.next();
        }
        result += "]";
        return result;
    }

    private void checkIndex(int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index: " + index
                    + ", size: " + size);
        }
    }
}
