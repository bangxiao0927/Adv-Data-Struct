/**
 * A hash set implemented with an array and linear probing.  It supplies the
 * key-set type required by MyHashMap.
 */
public class MyHashSet<E> {
    private static final int INITIAL_CAPACITY = 101;
    private static final double MAX_LOAD_FACTOR = 0.70;

    private Object[] hashArray;
    private int size;

    public MyHashSet() {
        hashArray = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /** Adds an element when it is not already present. */
    public boolean add(E element) {
        if (element == null || contains(element)) {
            return false;
        }
        if (size + 1 > hashArray.length * MAX_LOAD_FACTOR) {
            resize();
        }

        insert(element);
        return true;
    }

    /** Returns whether an element is in the set. */
    public boolean contains(Object element) {
        return element != null && findIndex(element) >= 0;
    }

    /** Removes an element and returns whether it was present. */
    public boolean remove(Object element) {
        if (element == null) {
            return false;
        }

        int index = findIndex(element);
        if (index < 0) {
            return false;
        }

        hashArray[index] = null;
        size--;

        // Reinsert the rest of the probe cluster so its values stay findable.
        int next = (index + 1) % hashArray.length;
        while (hashArray[next] != null) {
            Object displaced = hashArray[next];
            hashArray[next] = null;
            size--;
            insert(displaced);
            next = (next + 1) % hashArray.length;
        }
        return true;
    }

    /** Returns how many elements are in the set. */
    public int size() {
        return size;
    }

    /** Copies all set values into a doubly linked list. */
    public DLList<E> toDLList() {
        DLList<E> values = new DLList<E>();
        for (Object item : hashArray) {
            if (item != null) {
                @SuppressWarnings("unchecked")
                E value = (E) item;
                values.add(value);
            }
        }
        return values;
    }

    private int findIndex(Object element) {
        int index = bucketIndex(element);
        for (int probes = 0; probes < hashArray.length; probes++) {
            Object current = hashArray[index];
            if (current == null) {
                return -1;
            }
            if (current.equals(element)) {
                return index;
            }
            index = (index + 1) % hashArray.length;
        }
        return -1;
    }

    private void insert(Object element) {
        int index = bucketIndex(element);
        while (hashArray[index] != null) {
            index = (index + 1) % hashArray.length;
        }
        hashArray[index] = element;
        size++;
    }

    private int bucketIndex(Object element) {
        return Math.floorMod(element.hashCode(), hashArray.length);
    }

    private void resize() {
        Object[] oldArray = hashArray;
        hashArray = new Object[oldArray.length * 2 + 1];
        size = 0;
        for (Object item : oldArray) {
            if (item != null) {
                insert(item);
            }
        }
    }
}
