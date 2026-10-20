// A hash set that uses an array and linear probing to handle collisions.
public class MyHashSet<E> {
    private static final int INITIAL_CAPACITY = 10_007;
    private static final double MAX_LOAD_FACTOR = 0.70;

    private Object[] hashArray;
    private int size;

    public MyHashSet() {
        hashArray = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    // Adds the element if it is not already in the set.
    public boolean add(E element) {
        if (element == null || findIndex(element) >= 0) {
            return false;
        }

        if (size + 1 > hashArray.length * MAX_LOAD_FACTOR) {
            resize(hashArray.length * 2 + 1);
        }
        insert(element);
        return true;
    }

    // Removes everything from the set.
    public void clear() {
        hashArray = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    // Checks if the set has this element.
    public boolean contains(Object element) {
        return element != null && findIndex(element) >= 0;
    }

    // Removes the element if it is in the set.
    public boolean remove(Object element) {
        int index;
        if (element == null) {
            index = -1;
        } else {
            index = findIndex(element);
        }
        if (index < 0) {
            return false;
        }

        // Reinsert the rest of this group so searches still work after the gap.
        hashArray[index] = null;
        size--;
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

    public int size() {
        return size;
    }

    // Copies the set's elements into a doubly linked list.
    public DLList<E> toDLList() {
        DLList<E> items = new DLList<E>();
        for (Object item : hashArray) {
            if (item != null) {
                @SuppressWarnings("unchecked")
                E element = (E) item;
                items.add(element);
            }
        }
        return items;
    }

    private int findIndex(Object element) {
        int index = bucketIndex(element.hashCode());
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
        int index = bucketIndex(element.hashCode());
        while (hashArray[index] != null) {
            index = (index + 1) % hashArray.length;
        }
        hashArray[index] = element;
        size++;
    }

    private int bucketIndex(int hashCode) {
        return Math.floorMod(hashCode, hashArray.length);
    }

    private void resize(int newCapacity) {
        Object[] oldArray = hashArray;
        hashArray = new Object[newCapacity];
        int oldSize = size;
        size = 0;
        for (Object item : oldArray) {
            if (item != null) {
                insert(item);
            }
        }
        if (size != oldSize) {
            throw new IllegalStateException("Hash set size changed during resize.");
        }
    }
}
