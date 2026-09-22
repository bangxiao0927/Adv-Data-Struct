public class Node<E> {
    private E data;
    private Node<E> next;
    private Node<E> prev;

    public Node(E ele){
        data = ele;
        next = null;
        prev = null;
    }

    public E get(){
        return data;
    }

    public void setData(E newData){
        data = newData;
    }

    public Node<E> next(){
        return next;
    }

    public Node<E> prev(){
        return prev;
    }

    public void setNext(Node<E> newNode){
        next = newNode;
    }

    public void setPrev(Node<E> newNode){
        prev = newNode;
    }
}
