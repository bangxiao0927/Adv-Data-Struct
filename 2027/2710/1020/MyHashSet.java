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

    /** Returns true if added; false if it was already present or null. */
    public boolean add(E value) {
        if (value == null || contains(value)) {
            return false;
        }
        if (size + 1 > elements.length * 0.70) {
            resize();
        }
        insert(value);
        return true;
    }

    public boolean contains(Object value) {
        if (value == null) {
            return false;
        }
        int index = bucket(value);
        while (elements[index] != null) {
            if (elements[index].equals(value)) {
                return true;
            }
            index = (index + 1) % elements.length;
        }
        return false;
    }

    public boolean remove(Object value) {
        if (!contains(value)) {
            return false;
        }
        elements[value.hashCode()] = null;
        size--;
        return true;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // Copy the set's values into a doubly linked list (used by Runner).
    public DLList<E> toDLList() {
        DLList<E> list = new DLList<E>();
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != null) {
                E element = (E) elements[i];
                list.add(element);
            }
        }
        return list;
    }

    private int bucket(Object value) {
        return Math.floorMod(value.hashCode(), elements.length);
    }

    private void insert(Object value) {
        int index = bucket(value);
        while (elements[index] != null) {
            index = (index + 1) % elements.length;
        }
        elements[index] = value;
        size++;
    }

    // Grow the backing array when it gets too full, then re-insert everything.
    private void resize() {
        Object[] old = elements;
        elements = new Object[old.length * 2 + 1];
        size = 0;
        for (int i = 0; i < old.length; i++) {
            if (old[i] != null) {
                insert(old[i]);
            }
        }
    }
}
