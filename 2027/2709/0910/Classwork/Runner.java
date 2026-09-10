import java.util.Scanner;

public class Runner {
    private static int recursiveCalls;

    public static void main(String[] args) {
        MyArrayList<Integer> myNumbers = new MyArrayList<Integer>();

        // Add 5,000 random numbers from 0 through 9,999.
        for (int i = 0; i < 5000; i++) {
            myNumbers.add((int) (Math.random() * 10000));
        }
        Scanner keyboard = new Scanner(System.in);
        Sort sort = new Sort();
        int choice = -1;
        boolean sorted = false;

        while (choice != 6) {
            System.out.println("\n1. Display the list");
            System.out.println("2. Scramble the list");
            System.out.println("3. Bubble sort");
            System.out.println("4. Merge sort");
            System.out.println("5. Binary search");
            System.out.println("6. Quit");
            System.out.print("Choose an option: ");

            while (!keyboard.hasNextInt()) {
                System.out.print("Please enter a number from 1-6: ");
                keyboard.next();
            }
            
            choice = keyboard.nextInt();

            if (choice == 1) {
                displayList(myNumbers);
            } else if (choice == 2) {
                sort.scramble(myNumbers);
                sorted = false;
                System.out.println("The list has been scrambled.");
            } else if (choice == 3) {
                sort.bubbleSort(myNumbers);
                sorted = true;
                System.out.println("The list has been sorted with BubbleSort.");
            } else if (choice == 4) {
                sort.mergeSort(myNumbers);
                sorted = true;
                System.out.println("The list has been sorted with MergeSort.");
            } else if (choice == 5) {
                if (!sorted) {
                    System.out.println("Sort the list before using binary search.");
                    continue;
                }

                System.out.print("Enter a number from 0-9999: ");
                while (!keyboard.hasNextInt()) {
                    System.out.print("Please enter an integer: ");
                    keyboard.next();
                }

                int target = keyboard.nextInt();
                recursiveCalls = 0;
                int location = binarySearch(myNumbers, target, 0,
                        myNumbers.size() - 1);

                if (location == -1) {
                    System.out.println(target + " was not found.");
                } else {
                    System.out.println(target + " was found at index "
                            + location + ".");
                }
                System.out.println("Recursive calls: " + recursiveCalls);
                System.out.println("A binary search of 5,000 items takes at "
                        + "most 13 comparisons because 2^12 < 5000 < 2^13.");
            } else if (choice != 6) {
                System.out.println("That option is not available.");
            }
        }

        System.out.println("Goodbye!");
        keyboard.close();
    }

    private static void displayList(MyArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            System.out.print(i == list.size() - 1 ? "\n" : ", ");
        }
    }

    private static int binarySearch(MyArrayList<Integer> list, int target,
                                    int low, int high) {
        if (low > high) {
            return -1;
        }

        recursiveCalls++;

        int middle = low + (high - low) / 2;
        int middleValue = list.get(middle);

        if (middleValue == target) {
            return middle;
        } else if (target < middleValue) {
            return binarySearch(list, target, low, middle - 1);
        } else {
            return binarySearch(list, target, middle + 1, high);
        }
    }
}
