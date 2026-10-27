public class MyHashSet<E> {

    private Object[] elements;
    private int size;

    public MyHashSet() {
        this(10000);
    }

    public MyHashSet(int capacity) {
        elements = new Object[capacity];
        size = 0;
    }

    /** Returns true if added; false if it was already present (no duplicates). */
    public boolean add(E value) {
        int index = value.hashCode();
        if (elements[index].equals(value)) {
            return false;
        }
        elements[index] = value;
        size++;
        return true;
    }

    public boolean contains(Object value) {
        int index = value.hashCode();
        if (elements[index].equals(value)) {
            return true;
        }
        return false;
    }

    public boolean remove(Object value) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(value)) {
                // shift everything after it one slot left to keep the array packed
                for (int j = i; j < size - 1; j++) {
                    elements[j] = elements[j + 1];
                }
                elements[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
