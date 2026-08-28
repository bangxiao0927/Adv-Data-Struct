public class MyItems<S,D> {
    private S name;
    private D price;

    public MyItems(S name, D price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "name: " + name + "\tprice: " + price;
    }
    
}
