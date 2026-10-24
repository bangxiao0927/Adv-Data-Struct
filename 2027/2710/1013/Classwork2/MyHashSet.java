// A simple hash set. It uses an array to store values.
public class MyHashSet<E> {
    private static final int INITIAL_CAPACITY = 10_007;
    private static final double MAX_LOAD_FACTOR = 0.70;

    private Object[] hashArray;
    private int size;

    public MyHashSet() {
        hashArray = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    // Add a value. A set does not allow duplicates.
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

    // Removes everything from the set.
    public void clear() {
        hashArray = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    public boolean contains(Object element) {
        return element != null && findIndex(element) >= 0;
    }

    // Removes the element if it is in the set.
    public boolean remove(Object element) {
        int index = element == null ? -1 : findIndex(element);
        if (index < 0) {
            return false;
        }

        // Reinsert the values after the empty spot.
        // Otherwise, a search could stop at the empty spot too early.
        hashArray[index] = null;
        size--;
        int next = (index + 1) % hashArray.length;
        while (hashArray[next] != null) {
            Object value = hashArray[next];
            hashArray[next] = null;
            size--;
            insert(value);
            next = (next + 1) % hashArray.length;
        }
        return true;
    }

    public int size() {
        return size;
    }

    // Copy the set's values into a doubly linked list.
    public DLList<E> toDLList() {
        DLList<E> list = new DLList<E>();
        for (Object item : hashArray) {
            if (item != null) {
                @SuppressWarnings("unchecked")
                E element = (E) item;
                list.add(element);
            }
        }
        return list;
    }

    private int findIndex(Object element) {
        int index = bucketIndex(element);
        while (hashArray[index] != null) {
            Object current = hashArray[index];
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

    // Make a bigger array and add every old value to it again.
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
