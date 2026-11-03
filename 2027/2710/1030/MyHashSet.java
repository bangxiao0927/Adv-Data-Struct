// A set with one slot per hash code, which is enough here because the country
// abbreviations hash to 0-675 and the array has 10000 slots.
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

    /** Returns true if added; false if it was already present. */
    public boolean add(E value) {
        int index = indexOf(value);
        if (elements[index] != null) {
            if (elements[index].equals(value)) {
                return false;
            }
            // Never overwrite another key: a shared slot would lose it silently.
            throw new IllegalStateException("Two keys hash to slot " + index);
        }
        elements[index] = value;
        size++;
        return true;
    }

    public boolean contains(Object value) {
        int index = indexOf(value);
        return elements[index] != null && elements[index].equals(value);
    }

    public boolean remove(Object value) {
        int index = indexOf(value);
        if (elements[index] == null || !elements[index].equals(value)) {
            return false;
        }
        elements[index] = null;
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
                @SuppressWarnings("unchecked")
                E element = (E) elements[i];
                list.add(element);
            }
        }
        return list;
    }

    private int indexOf(Object value) {
        return Math.floorMod(value.hashCode(), elements.length);
    }
}
