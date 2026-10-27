public class MyHashMap<K, V> {

    private Object[] hashArray;    // index = hashCode % length -> holds the value
    private MyHashSet<K> keySet;   // every key ever put into the map
    private int size;              // number of distinct keys

    public MyHashMap() {
        hashArray = new Object[10000];
        // keySet uses the same capacity as hashArray
        keySet = new MyHashSet<K>(hashArray.length);
        size = 0;
    }

    /**
     * Stores key/value and returns the value that was mapped to key before
     * (null if there was none).
     */
    public V put(K key, V value) {
        int index = indexFor(key);

        // 1. Grab the old value first -- it is the return value
        V previous = (V) hashArray[index];

        // 2. Put the new value at the hashCode slot
        hashArray[index] = value;

        // 3. Only a brand-new key bumps size, and it must join keySet
        if (keySet.add(key)) {
            size++;
        }

        return previous;
    }

    /**
     * Returns the value mapped to key, or null when that slot is empty.
     */
    public V get(Object key) {
        int index = indexFor(key);          // 1. 算出这个 key 在数组里的位置
        Object previous = hashArray[index];    // 2. 取出那个位置上的东西
        if (previous == null) {               // 3. 位置是空的
            return null;                     //    -> 没找到，返回 null
        }
        return (V) previous;                   // 4. 有东西 -> 强转回 V 返回
    }
    /**
     * Removes key and returns its old value (null if absent).
     */
    public V remove(Object key) {
        int index = indexFor(key);
        V previous = (V) hashArray[index];
        if (previous != null) {
            hashArray[index] = null;
            keySet.remove(key);   // keep keySet in sync
            size--;
        }
        return previous;
    }

    public boolean containsKey(Object key) {
        return keySet.contains(key);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns all keys. The assignment asks for MyHashSet<K>, so the field goes straight back. */
    public MyHashSet<K> keySet() {
        return keySet;
    }

    /**
     * hashCode can be negative, so take the absolute value before the modulo.
     * Guarantees 0 <= index < hashArray.length.
     */
    private int indexFor(Object key) {
        return Math.abs(key.hashCode()) % hashArray.length;
    }
}
