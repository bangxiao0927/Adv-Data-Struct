public class MyArrayList <E> {
    private Object[] list;
    private int size;

    public MyArrayList(){
        list = new Object[10];
        size = 0;
    }

    public boolean add(E element){
        if (size == list.length){
            Object[] expandedList = new Object[list.length * 2];
            System.arraycopy(list, 0, expandedList, 0, list.length);
            list = expandedList;
        }

        list[size++] = element;
        return true;
    }

    public void add(int i, E element) {
        if (i < 0 || i > size) {
            throw new IndexOutOfBoundsException("Index: " + i + ", size: " + size);
        }
        
        if (size == list.length) {
            Object[] expandedList = new Object[list.length * 2];
            System.arraycopy(list, 0, expandedList, 0, list.length);
            list = expandedList;
        }

        for (int j = size; j > i; j--) {
            list[j] = list[j - 1];
        }

        list[i] = element;
        size++;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
        return (E) list[index];
    }

// + remove(int) : E - Remove an element at a specific location.  Return the element that was removed.
// + contains(Object) : boolean - Takes in an object variable.  
// Goes through each element in the list and calls the equals method, passing in that object variable.  Return true if any of them result in a true.
// + remove(Object) : boolean - Remove an element that is equal to the given element.  You will need to call the equals method of the object passed in.  If it returns true, then remove the element.  Return true if successfully removed and false otherwise.
// + set(i,E) : void - Replace an element at specific location.  
// + toString() : String - Return a string of all the elements within the list
// + size() : int - Return the size variable.

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }

        E removed = get(index);
        int moved = size - index - 1;
        if (moved > 0) {
            System.arraycopy(list, index + 1, list, index, moved);
        }
        list[--size] = null;
        return removed;
    }

    public boolean remove(Object object) {
        for (int i = 0; i < size; i++) {
            if (object.equals(list[i])) {
                remove(i);
                return true;
            }
        }

        return false;
    }

    public void set(int i, E element) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index: " + i + ", size: " + size);
        }
        list[i] = element;
    }

    public boolean contains(Object object) {
        for (int i = 0; i < size; i++) {
            if (object.equals(list[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i < size; i++) {
            if (i > 0) result += ", ";
            result += list[i];
        }
        return result + "]";
    }

    public int size() {
        return size;
    }

}
