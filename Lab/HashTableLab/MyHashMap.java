public class MyHashMap<K, V> {

    private DLList<V>[] table;
    private MyKeySet<K> keySet;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        table = (DLList<V>[]) new DLList<?>[676];
        keySet = new MyKeySet<K>();
        size = 0;
    }

    public void put(K key, V value) {
        int index = key.hashCode();
        if (keySet.contains(key)) {
            table[index].set(0, value);
        } else {
            if (table[index] == null) {
                table[index] = new DLList<V>();
            }
            table[index].add(value);
        }

        if (keySet.add(key)) {
            size++;
        }
    }

    public DLList<V> get(K key) {
        return table[key.hashCode()];
    }

    public MyKeySet<K> keySet() {
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
            if (keySet.remove(key)) {
                size--;
            }
        }
    }

    public void remove(K key) {
        if (keySet.remove(key)) {
            table[key.hashCode()] = null;
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
