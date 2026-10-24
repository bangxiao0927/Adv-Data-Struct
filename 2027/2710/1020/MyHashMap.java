/** A hash map that stores keys and values with linear probing. */
public class MyHashMap<K, V> {
    private static final int INITIAL_CAPACITY = 101;
    private static final double MAX_LOAD_FACTOR = 0.70;

    /** One array position holds both parts of a mapping. */
    private static class Entry<K, V> {
        private final K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Object[] hashArray;
    private int size;
    private MyHashSet<K> keySet;

    /** Sets up an empty map and its corresponding key set. */
    public MyHashMap() {
        hashArray = new Object[INITIAL_CAPACITY];
        size = 0;
        keySet = new MyHashSet<K>();
    }

    /**
     * Adds a mapping or replaces the value for an existing key.
     *
     * @return the previous value, or null when the key was not present
     */
    public V put(K key, V value) {
        requireKey(key);
        int index = findIndex(key);
        if (index >= 0) {
            Entry<K, V> entry = entryAt(index);
            V oldValue = entry.value;
            entry.value = value;
            return oldValue;
        }

        if (size + 1 > hashArray.length * MAX_LOAD_FACTOR) {
            resize();
        }
        insert(key, value);
        keySet.add(key);
        return null;
    }

    /** Returns the value mapped to a key, or null if the key is absent. */
    public V get(Object key) {
        if (key == null) {
            return null;
        }
        int index = findIndex(key);
        return index < 0 ? null : entryAt(index).value;
    }

    /**
     * Removes a mapping and its key from the key set.
     *
     * @return the removed value, or null when the key was not present
     */
    public V remove(Object key) {
        if (key == null) {
            return null;
        }

        int index = findIndex(key);
        if (index < 0) {
            return null;
        }

        Entry<K, V> removed = entryAt(index);
        hashArray[index] = null;
        size--;
        keySet.remove(removed.key);

        // A deletion leaves a gap. Reinsert the following cluster so lookups
        // for colliding keys do not stop at that gap.
        int next = (index + 1) % hashArray.length;
        while (hashArray[next] != null) {
            Entry<K, V> displaced = entryAt(next);
            hashArray[next] = null;
            size--;
            insert(displaced.key, displaced.value);
            next = (next + 1) % hashArray.length;
        }
        return removed.value;
    }

    /** Returns the number of key/value mappings in the map. */
    public int size() {
        return size;
    }

    /** Returns the set of keys currently stored in this map. */
    public MyHashSet<K> keySet() {
        return keySet;
    }

    private void requireKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Keys cannot be null.");
        }
    }

    private int findIndex(Object key) {
        int index = bucketIndex(key);
        for (int probes = 0; probes < hashArray.length; probes++) {
            Object item = hashArray[index];
            if (item == null) {
                return -1;
            }

            Entry<K, V> entry = entryAt(index);
            if (entry.key.equals(key)) {
                return index;
            }
            index = (index + 1) % hashArray.length;
        }
        return -1;
    }

    private void insert(K key, V value) {
        int index = bucketIndex(key);
        while (hashArray[index] != null) {
            index = (index + 1) % hashArray.length;
        }
        hashArray[index] = new Entry<K, V>(key, value);
        size++;
    }

    private int bucketIndex(Object key) {
        return Math.floorMod(key.hashCode(), hashArray.length);
    }

    private void resize() {
        Object[] oldArray = hashArray;
        hashArray = new Object[oldArray.length * 2 + 1];
        size = 0;
        for (Object item : oldArray) {
            if (item != null) {
                Entry<K, V> entry = castEntry(item);
                insert(entry.key, entry.value);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private Entry<K, V> entryAt(int index) {
        return (Entry<K, V>) hashArray[index];
    }

    @SuppressWarnings("unchecked")
    private Entry<K, V> castEntry(Object item) {
        return (Entry<K, V>) item;
    }
}
