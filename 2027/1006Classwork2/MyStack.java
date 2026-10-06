// A last-in first-out stack. push puts a value on the top and pop takes the
// most recent one back off.
public class MyStack<E> {
    private static final int INITIAL_CAPACITY = 10;

    private Object[] elements;
    private int size;

    public MyStack() {
        elements = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    public void push(E value) {
        ensureCapacity();
        elements[size] = value;
        size++;
    }

    @SuppressWarnings("unchecked")
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot pop from an empty stack");
        }
        size--;
        E value = (E) elements[size];
        elements[size] = null;
        return value;
    }

    @SuppressWarnings("unchecked")
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot peek at an empty stack");
        }
        return (E) elements[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            Object[] largerArray = new Object[elements.length * 2];
            for (int i = 0; i < elements.length; i++) {
                largerArray[i] = elements[i];
            }
            elements = largerArray;
        }
    }
}
