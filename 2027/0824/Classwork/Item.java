public class Item<T>{
    private T o;
    private double price;

    public Item(T o, double price){
        this.o = o;
        this.price = price;
    }

    public T getItem(){
        return o;
    }

    public double getPrice(){
        return price;
    }
}