import java.util.Scanner;

public class Runner{
    public static void main(String[] args){
        MyArrayList<Task> list = new MyArrayList<>();

        Scanner sn = new Scanner(System.in);

        for (int i = 1; i <= 100; i++){
            String nameTask = "task"+i;
            int numTask = (int) (Math.random() * 29) + 1;
            list.add(new Task(nameTask, numTask));
        }

        for (int i = 0; i < list.size() - 1; i++) {
            int smallest = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).getRank() < list.get(smallest).getRank()) {
                    smallest = j;
                }
            }
            Task temp = list.get(i);
            list.set(i, list.get(smallest));
            list.set(smallest, temp);
        }

        System.out.println("Sorted Taks");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }


        boolean end = false;

        while(!end){
            System.out.println("1. View Task List");
            System.out.println("2. Add Task");
            System.out.println("3. Remove Task");
            System.out.println("4. Add Task at Index");
            System.out.println("5. Remove Task at Index");
            System.out.println("6. Replace Task at Index");
            System.out.println("7. Test Removal at Random");
            System.out.println("8. Quit");

            int choice = sn.nextInt();

            if (choice == 1){
                for (int i = 0; i < list.size(); i++) {
                        System.out.println("[" + i + "] " + list.get(i));
                    }
            } else if (choice == 2){
                System.out.print("Enter task name: ");
                String nameAdd = sn.nextLine();
                System.out.print("Enter task rank (1-30): ");
                int rankAdd = sn.nextInt();
                sn.nextLine();
                Task tempTask = new Task(nameAdd, rankAdd);
                if (list.contains(tempTask)) {
                    System.out.println("A duplicate task already exists!");
                } else {
                    list.add(tempTask);
                    System.out.println("Successfully added");
                }
            
            } else if (choice == 3){
                System.out.print("Enter task name to remove: ");
                String nameRemove = sn.nextLine();
                Task findTask = new Task(nameRemove, 0); 
                    
                if (list.remove(findTask)) {
                    System.out.println("Successfully removed");
                } else {
                    System.out.println("Task not found.");
                }

            }else if (choice == 4){
                System.out.print("Enter index location (0 to " + list.size() + "): ");
                int indexAdd = sn.nextInt();
                sn.nextLine();
                
                if (indexAdd < 0 || indexAdd > list.size()) {
                    System.out.println("Invalid index choice.");
                } else {
                    System.out.print("Enter task name: ");
                    String nameIdx = sn.nextLine();
                    System.out.print("Enter task rank (1-30): ");
                    int rankIdx = sn.nextInt();
                    sn.nextLine();

                    list.add(indexAdd, new Task(nameIdx, rankIdx));
                    System.out.println("Task inserted at index " + indexAdd);
                }
            }else if (choice == 5){
                System.out.print("Enter index to remove (0 to " + (list.size() - 1) + "): ");
                int indexRemove = sn.nextInt();
                sn.nextLine();
                
                if (indexRemove < 0 || indexRemove >= list.size()) {
                    System.out.println("Invalid index choice.");
                } else {
                    Task removed = list.remove(indexRemove);
                    System.out.println("Removed: " + removed);
                }
            }else if (choice == 6){
                System.out.print("Enter index to replace (0 to " + (list.size() - 1) + "): ");
                int indexSet = sn.nextInt();
                sn.nextLine();
                
                if (indexSet < 0 || indexSet >= list.size()) {
                    System.out.println("Invalid index choice.");
                } else {
                    System.out.print("Enter new task name: ");
                    String nameSet = sn.nextLine();
                    System.out.print("Enter new task rank (1-30): ");
                    int rankSet = sn.nextInt();
                    sn.nextLine();

                    list.set(indexSet, new Task(nameSet, rankSet));
                    System.out.println("Task at index " + indexSet + " replaced.");
                }
            }else if (choice == 7){
                System.out.println("\n--- Testing Random Removal Until Empty ---");
                while (list.size() > 0) {
                    int randIndex = (int) (Math.random() * list.size());
                    Task removedRand = list.remove(randIndex);
                    System.out.println("Removed from index [" + randIndex + "]: " + removedRand);
                }
                System.out.println("The list is now completely empty.");
            }else {
                end = true;
            }
        }

    }
}
