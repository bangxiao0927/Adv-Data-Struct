import java.util.random.*;
public class Runner {
    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i =0;i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 99) + 1;
        }

        for (int i : arr) {
            System.out.println(i);
        }
    }
}
