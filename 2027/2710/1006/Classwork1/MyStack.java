public class MyStack<E>{
    private DLList<E> myStack;

    public MyStack(){
        myStack = new DLList<>();
    }

    public E push(E item){
        myStack.addLast(item);
        return item;
    }

    public E pop(){
        if (empty()){
            return null;
        }
        return myStack.removeLast();
    }

    public E peek(){
        if (empty()){
            return null;
        }
        return myStack.getLast();
    }

    public int size(){
        return myStack.size();
    }

    public boolean empty(){
        return myStack.isEmpty();
    }
}