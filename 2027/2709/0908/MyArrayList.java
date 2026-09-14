public class MyArrayList<E>{
    private Object[] list;
    private int size;
    private int capacity;

    public MyArrayList(){
        list = new Object[10];
        size = 0;
    }

    public boolean add(E element){
        if (size == list.length){
            Object[] expand = new Object[list.length * 2];
            for (int i = 0; i < list.length; i++){
                expand[i] = list[i];
            }
            list = expand;
        }
        list[size++] = element;
        return true;
    }

    @SuppressWarnings("unchecked")
    public E get(int index){
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
        
        return (E) list[index];
    }

    public int size(){
        return size;
    }


    public void set(int i, E element) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index: " + i + ", size: " + size);
        }
        list[i] = element;
    }
}
