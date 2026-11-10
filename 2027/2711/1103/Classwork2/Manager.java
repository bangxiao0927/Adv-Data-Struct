import java.util.Random;

public class Manager {
    private int[] numberList;
    private int index;

    public Manager() {
        numberList = new int[15];
        Random rand = new Random();
        for (int i = 0; i < numberList.length; i++) {
            numberList[i] = rand.nextInt(10) + 1; // random numbers from 1 to 10
        }
        index = 0;
    }

    /**
     * Doubles numberList[index], then increases index by 1.
     *
     * Variants to test (uncomment one):
     *  1) No synchronization: remove 'synchronized' keyword below
     *  2) Synchronized method (current): 'synchronized void doubleIt()'
     *  3) Synchronized block on the changing variables only
     */
    public synchronized void doubleIt() {
        try {
            Thread.sleep(1000); // wait for 1 second
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        numberList[index] = numberList[index] * 2;
        index = index + 1;
    }

    /*
     * Variant 3 - synchronized block on the variables that are changing:
     public void doubleIt() {
         try {
             Thread.sleep(1000);
         } catch (InterruptedException ex) {
             Thread.currentThread().interrupt();
         }
         synchronized (this) {
             numberList[index] = numberList[index] * 2;
             index = index + 1;
         }
     }
     */

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberList.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(numberList[i]);
        }
        return sb.toString();
    }
}