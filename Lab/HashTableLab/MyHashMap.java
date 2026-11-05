public class MyHashMap<K, V> {

    private DLList<V>[] table;
    private MyHashSet<K> keySet;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashTable() {
        table = (DLList<V>[]) new DLList<?>[10000];
        keySet = new MyHashSet<K>(table.length);
        size = 0;
    }

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

    public DLList<V> get(K key) {
        return table[key.hashCode()];
    }

    public MyHashSet<K> keySet() {
        return keySet;
    }
    
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
