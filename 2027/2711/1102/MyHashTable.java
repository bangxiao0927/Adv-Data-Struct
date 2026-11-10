@SuppressWarnings("unchecked")
public class MyHashTable<K, V> {
    private DLList<V>[] table;
    private MyHashSet<K> keySet;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashTable() {
        table = new DLList[676];
        keySet = new MyHashSet<K>();
        size = 0;
    }

    // Maps the key and value. If there is a duplicate key, add the value to
    // the linked list within the table. Add the key to the keySet.
    @SuppressWarnings("unchecked")
    public void put(K key, V value) {
        int index = key.hashCode();
        DLList<V> bucket = table[index];
        if (bucket == null) {
            bucket = new DLList<V>();
            table[index] = bucket;
        }
        bucket.add(value);
        if (keySet.add(key)) {
            size++;
        }
    }

    // Return the linked list containing the values.
    @SuppressWarnings("unchecked")
    public DLList<V> get(K key) {
        return table[key.hashCode()];
    }

    public MyHashSet<K> keySet() {
        return keySet;
    }

    // Remove the given value within the given key. If a bucket no longer
    // has any values, remove the bucket too.
    @SuppressWarnings("unchecked")
    public void remove(K key, V value) {
        int index = key.hashCode();
        DLList<V> bucket = table[index];
        if (bucket == null || bucket.size() == 0) {
            return;
        }
        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).equals(value)) {
                bucket.remove(i);
                break;
            }
        }
        if (bucket.size() == 0) {
            table[index] = null;
            keySet.remove(key);
            size--;
        }
    }

    // Remove the bucket entirely.
    @SuppressWarnings("unchecked")
    public void remove(K key) {
        int index = key.hashCode();
        if (table[index] != null) {
            table[index] = null;
            keySet.remove(key);
            size--;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}