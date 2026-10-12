public class MyQueue<E> {
    private DLList<E> myQueue;

    public MyQueue() {
        myQueue = new DLList<>();
    }

    // O(1) - Uses your existing add() which appends to the tail
    public boolean add(E item) {
        myQueue.add(item);
        return true;
    }

    // O(1) - Removes from the very front of the list (index: 0)
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        return myQueue.remove(0);
    }

    // O(1) - Looks at the very front of the list (index: 0)
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return myQueue.get(0);
    }

    // O(1)
    public int size() {
        return myQueue.size();
    }

    // O(1)
    public boolean isEmpty() {
        return myQueue.size() == 0;
    }
}
