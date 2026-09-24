// Fills a DLList with 3000 random numbers, scrambles it, then empties it.
public class Runner {
    public static void main(String[] args) {
        DLList<Integer> list = new DLList<Integer>();

        // add 3000 random numbers, each one at a random index
        for (int i = 0; i < 3000; i++) {
            int number = (int) (Math.random() * 999) + 1;
            int index = (int) (Math.random() * (list.size() + 1));
            list.add(index, number);
        }
        System.out.println("After adding 3000 numbers:");
        System.out.println(list);

        // scramble: pull a random number out and put it back at a random index
        for (int i = 0; i < 3000; i++) {
            int index = (int) (Math.random() * list.size());
            Integer number = list.remove(index);
            int newIndex = (int) (Math.random() * (list.size() + 1));
            list.add(newIndex, number);
        }
        System.out.println("Scrambled:");
        System.out.println(list);

        // empty the list one random index at a time
        for (int i = 0; i < 3000; i++) {
            int index = (int) (Math.random() * list.size());
            System.out.println("Removed " + list.remove(index));
        }
        System.out.println("After removing everything:");
        System.out.println(list);
        System.out.println("Size is " + list.size());
    }
}
