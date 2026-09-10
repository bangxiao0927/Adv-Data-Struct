public class MyArrayList <E> {
    private Object[] list;
    private int size;
    private int capacity;

    public MyArrayList(){
        capacity = 10;
        list = new Object[capacity];
        size = 0;
    }

    public boolean add(E element){
        if (size == capacity){
            Object[] expandedList = new Object[capacity * 2];
            for (int i = 0; i < list.length; i++) {
                expandedList[i] = list[i];
            }
            list = expandedList;
        }

        list[size++] = element;
        return true;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
        return (E) list[index];
    }

    public int size() {
        return size;
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
}
