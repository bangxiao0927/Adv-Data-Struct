public class MyQueue<E> {
    private DLList<E> myQueue;

    public MyQueue(){
        myQueue = new DLList<>();
    }

    public boolean add(E item){
        myQueue.addLast(item);
        return true;
    }

    public E poll(){
        if (isEmpty()){
            return null;
        }
        return myQueue.getFirst();
    }

    public int size(){
        return myQueue.size();
    }

    public boolean isEmpty(){
        return myQueue.isEmpty();
    }
}
