public class Runner {
    public static void main(String[] args) {
        MyArrayList<Integer> myNumbers = new MyArrayList<>();

        // Add 3,000 random integers into the list
        for (int i = 0; i < 3000; i++) {
            // Generates a random number (e.g., between 0 and 9999)
            int rand = (int)(Math.random() *10000);
            myNumbers.add(rand);
        }

        // Print them using a loop and the get method, separated by a comma
        for (int i = 0; i < myNumbers.size(); i++) {
            System.out.print(myNumbers.get(i) + ", ");
        }
    }
}

