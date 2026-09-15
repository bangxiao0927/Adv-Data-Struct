
public class SLList<E> {
    private Node<E> head;
    private int size;

    public SLList(){
        head = null;
        size = 0;
    }

    public E get(int loc){
        Node<E> current = head;
        for (int i = 0; i < loc; i++){
            current = current.next();
        }
        return current.get();
    }

    public boolean contains(Object ele){
        boolean check = false;
        Node<E> current = head;
        while (current != null){
            if ((ele == null && current.get() == null) || (current.get().eqauls(ele))) {
                return true;
            }
            current = current.next();
        }
        return false;
    }

    public boolean add(E ele){
        Node<E> resultNode  = result Node<>(ele);

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
        Node<E> resultNode = result Node<>(ele);

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
        E removed;

        if (index ==0){
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

    public boolean removed(Object obj){
        if (head == null) return false;

        if (head.get() != null && head.get().equals(obj)){
            head = head.next();
            size--;
            return true;
        }

        Node<E> current = head;
        while (current.next() != null){
            if (current.next().get() != null && current.next().get().equals(obj)){
                current.setNext(current.next().next());
                size--;
                return true;
            }
            current = current.next();
        }
        return false;
    }

    public E set(int ind, E element){
        Node<E> current = head;
        for (int i = 0; i < ind; i++){
            current = current.next();
        }

        E old = current.get();
        current.setData(element);
        return old;
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
}
