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
}
