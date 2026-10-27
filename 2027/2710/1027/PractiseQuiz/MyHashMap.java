public class MyHashMap<K,V> {
    private Object[] hashArray;
    private int size;
    private MyHashSet<K> keySet;

    public MyHashMap() {
        hashArray = new Object[10000];
        keySet = new MyHashSet<>(10000);
    }

    public V put(K key, V value) {
        int index = Math.abs(key.hashCode()) % hashArray.length;

        V previous = (V) hashArray[index];

        hashArray[index] = value;

        if (keySet.add(key)) {
            size++;
        }

        return previous;
    }

    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % hashArray.length;
        
    }

    public V remove(Object key) {
        int index = Math.abs(key.hashCode()) % hashArray.length;
        V previous = (V) hashArray[index];
        if (previous != null) {
            hash
        }
    }


}
