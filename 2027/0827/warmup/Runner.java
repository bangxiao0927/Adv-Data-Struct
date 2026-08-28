import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {
        ArrayList<MyItems<String, Double>> mylist = new ArrayList<>();

        MyItems<String, Double> item1 = new MyItems<String, Double>("One", 0.0);
        MyItems<String, Double> item2 = new MyItems<String, Double>("Two", 1.0);
        MyItems<String, Double> item3 = new MyItems<String, Double>("Three", 2.0);

        mylist.add(item1);
        mylist.add(item2);
        mylist.add(item3);

        for (int i = 0; i < mylist.size(); i++) {
            System.out.println(mylist.get(i));
        }
    }
}
