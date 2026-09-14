import java.util.Arrays;
import java.util.Random;

public class Sort {
    public void scramble(MyArrayList<Integer> list) {
        Random rand = new Random();
        for (int i = list.size() - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }

    public void bubbleSort(MyArrayList<Integer> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    int temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    void mergeSort(int[] stuff, int front, int back) {
    // Base Case: If the subarray has 0 or 1 elements, it's already sorted
    if (back - front <= 1) {
        return;
    }
    
    int mid = (front + back) / 2;
    
    // Sort the left and right halves
    mergeSort(stuff, front, mid);
    mergeSort(stuff, mid, back);
    
    // Merge them together
    merge(stuff, front, back);
}

    public void merge(int[] stuff, int front, int back) {
        int[] temp = new int[back - front];
        int i = front;
        int j = (front + back) / 2;
        int k = 0;
        int mid = j;
        
        // Copy the smaller element from either the left or right half
        while (i < mid && j < back) {
            if (stuff[i] < stuff[j]) {
                temp[k++] = stuff[i++];
            } else {
                temp[k++] = stuff[j++];
            }
        }
        
        // Copy any remaining elements from the left half
        while (i < mid) {
            temp[k++] = stuff[i++];
        }
        
        // Copy any remaining elements from the right half
        while (j < back) {
            temp[k++] = stuff[j++];
        }
        
        // Copy the sorted elements back into the original array
        for (i = 0; i < back - front; ++i) {
            stuff[front + i] = temp[i];
        }
    }
}
