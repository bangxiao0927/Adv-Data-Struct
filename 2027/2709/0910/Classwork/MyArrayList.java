public class MyArrayList<E> {
    private static final int INITIAL_CAPACITY = 10;

    private Object[] elements;
    private int size;

    public MyArrayList() {
        elements = new Object[INITIAL_CAPACITY];
    }

    public void add(E value) {
        ensureCapacity();
        elements[size] = value;
        size++;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    public void set(int index, E value) {
        checkIndex(index);
        elements[index] = value;
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

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index
                    + ", Size: " + size);
        }
    }
}
