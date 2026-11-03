public class MyStack<E> { 
    private DLList<E> myStack; 

    public MyStack(){ 
        myStack = new DLList<>();
    } 

    // O(1) - Uses your existing add() which appends to the tail
    public E push(E item) {
        myStack.add(item);
        return item;
    }

    // O(1) - Removes from the very end of the list (index: size - 1)
    public E pop() {
        if (isEmpty()) {
            return null; 
        }
        return myStack.remove(myStack.size() - 1);
    }

    // O(1) - Looks at the very end of the list (index: size - 1)
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return myStack.get(myStack.size() - 1);
    }

    // O(1)
    public int size() {
        return myStack.size();
    }

    // O(1)
    public boolean isEmpty() {
        return myStack.size() == 0; 
    }
}
