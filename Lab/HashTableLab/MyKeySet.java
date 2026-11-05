class MyKeySet<E> {
    private Object[] objs;
    private int size;
    
    public MyHashSet() {
        objs = new Object[676];
        size = 0;
    }

    public boolean add(E obj) {
        int hash = obj.hashCode();
        if (objs[hash] != null) {
            return false;
        } else {
            objs[hash] = obj;
            size++;
            return true;
        }
    }

    public void clear() {
        objs = new Object[676];
        size = 0;
    }

    public boolean contains(Object o) {
        int hash = o.hashCode();
        if (objs[hash].equals(o)) {
            return true;
        } else {
            return false;
        }
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
}
