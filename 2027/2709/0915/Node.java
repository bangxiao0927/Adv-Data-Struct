public class Node {
    private String data;
    private Node next;

//     Instance Variables
// - data : E - Generic data object
// - next : Node<E> - Points to the next node within the LinkedList chain.  If it is at the end, it will point to null. 
// Constructor
// + Node(E) - Takes in a generic object.  Sets data to what is passed in, and next to null.
// Methods
// + get() : E - Returns data.
// + next() : Node<E> - Returns the next node.
// + setNext(Node<E>) : void - Sets the next node to what is passed in.
// + setData(E) : void  - Set the data to what is passed in.


    public Node(String data){
        this.data = data;
        next = null;
    }

    public String get(){
        return data;
    }

    public Node next(){
        return next;
    }

    public void setNext(Node passed){
        next = passed;
    }
}
