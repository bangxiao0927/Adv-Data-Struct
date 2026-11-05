// Keeps the keys separate from the values so we can list them on their own.
public class MyKeySet<E> {
    private Object[] objs;
    private int size;
    
    public MyKeySet() {
        objs = new Object[676];
        size = 0;
    }

    public boolean add(E obj) {
        int hash = obj.hashCode();
        if (objs[hash] == null) {
            objs[hash] = obj;
            size++;
            return true;
        }
        // Same state again: keep the newest key (its name may have changed).
        objs[hash] = obj;
        return false;
    }

    public void clear() {
        objs = new Object[676];
        size = 0;
    }

    public boolean contains(Object o) {
        int hash = o.hashCode();
        if (objs[hash] == null) {
            return false;
        }
        return objs[hash].equals(o);
    }

    public boolean remove(Object o) {
        if (!contains(o)) {
            return false;
        }
        objs[o.hashCode()] = null;
        size--;
        return true;
    }

    public int size() {
        return size;
    }

    public String toString() {
        String all = "";
        for (int i = 0; i < objs.length; i++) {
            if (objs[i] != null) {
                all += objs[i].toString() + "\n";
            }
        }
        return all;
    }
}
