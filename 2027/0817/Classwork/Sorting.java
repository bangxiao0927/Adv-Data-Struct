
import java.util.Random;

public class Sorting {

    public void sortName(Profile[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].getName().compareTo(arr[j + 1].getName()) > 0) {
                    Profile temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public void sortAge(Profile[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].getAge() > arr[j + 1].getAge()) {
                    Profile temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    

    public void scramble(Profile[] arr) {
        Random rand = new Random();

        for (int i = 0; i < arr.length; i++) {
            int j = rand.nextInt(i + 1);

            Profile temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp; 
        }
    }    
}
