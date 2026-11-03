// A hash table whose buckets are linked lists, so one key can hold many values.
public class MyHashTable<K, V> {

    private DLList<V>[] table;     // index = key.hashCode() -> that key's values
    private MyHashSet<K> keySet;   // every key that has ever been put
    private int size;              // number of distinct keys

    @SuppressWarnings("unchecked")
    public MyHashTable() {
        table = (DLList<V>[]) new DLList<?>[10000];
        // keySet uses the same capacity as table
        keySet = new MyHashSet<K>(table.length);
        size = 0;
    }

    /** Adds value to the key's list, creating the bucket and the key when needed. */
    public void put(K key, V value) {
        int index = key.hashCode();
        if (table[index] == null) {
            table[index] = new DLList<V>();
        }
        table[index].add(value);

        if (keySet.add(key)) {
            size++;
        }
    }

    /** Returns the list of values for key, or null when the key has no bucket. */
    public DLList<V> get(K key) {
        return table[key.hashCode()];
    }

    public MyHashSet<K> keySet() {
        return keySet;
    }

    /** Removes one value from the key's list, and the empty bucket with it. */
    public void remove(K key, V value) {
        int index = key.hashCode();
        DLList<V> values = table[index];
        if (values == null) {
            return;
        }

        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).equals(value)) {
                values.remove(i);
                break;
            }
        }

        if (values.size() == 0) {
            table[index] = null;
        }
    }

    /** Removes the key and its whole bucket. */
    public void remove(K key) {
        table[key.hashCode()] = null;
        if (keySet.remove(key)) {
            size--;
        }
    }

    public boolean containsKey(K key) {
        return keySet.contains(key);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
