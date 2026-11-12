public class BubbleList {
    private Bubble[] items;
    private int size;

    public BubbleList() {
        items = new Bubble[10];
        size = 0;
    }

    public synchronized void add(Bubble b) {
        if (size == items.length) {
            Bubble[] tmp = new Bubble[items.length * 2];
            System.arraycopy(items, 0, tmp, 0, size);
            items = tmp;
        }
        items[size++] = b;
    }

    public synchronized Bubble get(int i) {
        if (i < 0 || i >= size) {
            return null;
        }
        return items[i];
    }

    public synchronized int size() {
        return size;
    }

    public synchronized boolean isEmpty() {
        return size == 0;
    }

    public synchronized void remove(int i) {
        if (i < 0 || i >= size) {
            return;
        }
        for (int j = i; j < size - 1; j++) {
            items[j] = items[j + 1];
        }
        items[--size] = null;
    }

    public synchronized void clear() {
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }
        size = 0;
    }

    // return a snapshot of the current bubbles so painting can iterate safely
    public synchronized Bubble[] toArray() {
        Bubble[] copy = new Bubble[size];
        System.arraycopy(items, 0, copy, 0, size);
        return copy;
    }
}
