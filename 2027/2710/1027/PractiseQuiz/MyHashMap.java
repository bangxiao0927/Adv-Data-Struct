public class MyHashMap<K,V> {
    private Object[] hashArray;
    private int size;
    private MyHashSet<K> keySet;

    public MyHashMap() {
        hashArray = new Object[10000];
        keySet = new MyHashSet<>();
    }

    @SuppressWarnings("unchecked")
    public V put(K key, V value) {
        int index = key.hashCode();

        V previous = (V) hashArray[index];

        hashArray[index] = value;

        if (keySet.add(key)) {
            size++;
        }

        return previous;
    }

    @SuppressWarnings("unchecked")
    public V get(Object key) {
        int index = key.hashCode();

        if(hashArray[index] != null) {
            return (V) hashArray[index];
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public V remove(Object key) {
        int index = key.hashCode();
        V previous = (V) hashArray[index];
        if (previous != null) {
            hashArray[index] = null;
            keySet.remove(key);
            size--;
        }

        return previous;
    }

    public int size() {
        return size;
    }

    public MyHashSet<K> keySet() {
        return keySet;
    }
}
