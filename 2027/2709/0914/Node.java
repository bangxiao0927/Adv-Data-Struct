public class Node {
    private String data;
    private Node next;

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
