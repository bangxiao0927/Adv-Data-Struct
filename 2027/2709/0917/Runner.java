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
                for (int pass = 0; pass < list.size() - 1; pass++) {
                    for (int i = 0; i < list.size() - 1 - pass; i++) {
                        if (list.get(i) > list.get(i + 1)) {
                            int temp = list.get(i);
                            list.set(i, list.get(i + 1));
                            list.set(i + 1, temp);
                        }
                    }
                }
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
}