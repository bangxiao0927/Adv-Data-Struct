import java.util.Scanner;

public class Runner{
    public static void main(String[] args){
        SLList<Integer> list = new SLList<>();
        for(int i = 0;i < 5000;i++) {
            int randomIndex = (int) (Math.random() * (list.size() + 1));
            list.add(randomIndex, (int) (Math.random() * 9998 + 1));
        }

        for(int i = 0;i < 5000;i++) {
            System.out.print(list.get(i) + "; ");
        }

        System.out.println(list.size());

        Scanner sc = new Scanner(System.in);

        boolean stop = false;

        while(!stop){
            System.out.println("Enter 1 to Display List, 2 to Scramble, 3 to Sort, 4 to Remove til Empty, and 5 to Quit");
            int choice = sc.nextInt();

            if (choice == 1){
                System.out.println(list.toString());
            } else if (choice == 2){
                for (int i = 0; i < list.size(); i++) {
                    int randomIndex = (int) (Math.random() * list.size());
                    int temp = list.get(i);
                    list.set(i, list.get(randomIndex));
                    list.set(randomIndex, temp);
                }
            } else if (choice == 3){
                quickSort(list, 0, list.size() - 1);
            } else if (choice == 4){
                while (list.size() > 0){
                    int rand = (int) (Math.random() * list.size());
                    list.remove(rand);
                }
            } else{
                System.out.println("Quitting");
                stop = true;
            }
        }
    }

    private static void quickSort(SLList<Integer> list, int low, int high) {
        if (low >= high) {
            return;
        }

        int left = low;
        int right = high;
        int pivot = list.get(low + (high - low) / 2);

        while (left <= right) {
            while (list.get(left) < pivot) {
                left++;
            }
            while (list.get(right) > pivot) {
                right--;
            }

            if (left <= right) {
                int temp = list.get(left);
                list.set(left, list.get(right));
                list.set(right, temp);
                left++;
                right--;
            }
        }

        if (low < right) {
            quickSort(list, low, right);
        }
        if (left < high) {
            quickSort(list, left, high);
        }
    }
}
