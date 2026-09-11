public class MyArrayList<E> {
    // The array stores the items in the list.
    private E[] elements;
    private int size;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        elements = (E[]) new Object[10];
        size = 0;
    }

    public boolean add(E element) {
        // Make room and place the new item at the end.
        ensureCapacity(size + 1);
        elements[size++] = element;
        return true;
    }

    public void add(int index, E element) {
        checkPositionIndex(index);
        ensureCapacity(size + 1);
        // Move items right to make an empty space.
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    public E get(int index) {
        checkElementIndex(index);
        return elements[index];
    }

    public E remove(int index) {
        checkElementIndex(index);
        E removed = elements[index];
        // Move the remaining items to the left.
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null;
        return removed;
    }

    public boolean remove(Object object) {
        // Find the first equal object and remove it.
        for (int i = 0; i < size; i++) {
            if (object == null) {
                if (elements[i] == null) {
                    remove(i);
                    return true;
                }
            } else if (object.equals(elements[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    public void set(int index, E element) {
        checkElementIndex(index);
        elements[index] = element;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                result += ", ";
            }
            result += elements[i];
        }
        return result + "]";
    }

    private void ensureCapacity(int neededCapacity) {
        if (neededCapacity <= elements.length) {
            return;
        }
        // Double the array when it becomes full.
        int newCapacity = elements.length * 2;
        while (newCapacity < neededCapacity) {
            newCapacity *= 2;
        }

        @SuppressWarnings("unchecked")
        E[] largerArray = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            largerArray[i] = elements[i];
        }

        elements = largerArray;
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
    }
}
