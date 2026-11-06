public class MyHashMap<K, V> {

    private Object[] hashArray;
    private MyHashSet<K> keySet;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        hashArray = new Object[676];
        keySet = new MyHashSet<K>();
        size = 0;
    }

    // Inserts the pair, replacing any old value for the same key.
    // Returns the previous value, or null if there was none.
    @SuppressWarnings("unchecked")
    public V put(K key, V value) {
        int index = key.hashCode();
        DLList<V> bucket = (DLList<V>) hashArray[index];
        V previous = null;
        if (bucket == null) {
            bucket = new DLList<V>();
            hashArray[index] = bucket;
            bucket.add(value);
        } else if (bucket.size() == 0) {
            bucket.add(value);
        } else {
            previous = bucket.get(0);
            bucket.set(0, value);
        }
        if (keySet.add(key)) {
            size++;
        }
        return previous;
    }

    @SuppressWarnings("unchecked")
    public V get(Object key) {
        DLList<V> bucket = (DLList<V>) hashArray[key.hashCode()];
        if (bucket == null || bucket.size() == 0) {
            return null;
        }
        return bucket.get(0);
    }

    @SuppressWarnings("unchecked")
    public V remove(Object key) {
        int index = key.hashCode();
        DLList<V> bucket = (DLList<V>) hashArray[index];
        if (bucket == null || bucket.size() == 0) {
            return null;
        }
        V removed = bucket.remove(0);
        if (bucket.size() == 0) {
            hashArray[index] = null;
        }
        if (keySet.remove(key)) {
            size--;
        }
        return removed;
    }

    public boolean containsKey(Object key) {
        return keySet.contains(key);
    }

    public MyHashSet<K> keySet() {
        return keySet;
    }

    public int size() {
        return size;
    }

    // Same as remove(key), kept for callers that remove by key and value.
    public V remove(K key, V value) {
        V current = get(key);
        if (current != null && current.equals(value)) {
            return remove(key);
        }
        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
