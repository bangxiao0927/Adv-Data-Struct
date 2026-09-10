public class Sort {
    public void scramble(MyArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            int randomIndex = (int) (Math.random() * list.size());
            int temp = list.get(i);
            list.set(i, list.get(randomIndex));
            list.set(randomIndex, temp);
        }
    }

    public void bubbleSort(MyArrayList<Integer> list) {
        for (int pass = 0; pass < list.size() - 1; pass++) {
            for (int i = 0; i < list.size() - 1 - pass; i++) {
                if (list.get(i) > list.get(i + 1)) {
                    int temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                }
            }
        }
    }

    public void mergeSort(MyArrayList<Integer> list) {
        mergeSort(list, 0, list.size() - 1);
    }

    private void mergeSort(MyArrayList<Integer> list, int first, int last) {
        if (first < last) {
            int middle = (first + last) / 2;
            mergeSort(list, first, middle);
            mergeSort(list, middle + 1, last);
            merge(list, first, middle, last);
        }
    }

    private void merge(MyArrayList<Integer> list, int first, int middle,
                       int last) {
        MyArrayList<Integer> temp = new MyArrayList<Integer>();
        int left = first;
        int right = middle + 1;

        while (left <= middle && right <= last) {
            if (list.get(left) < list.get(right)) {
                temp.add(list.get(left));
                left++;
            } else {
                temp.add(list.get(right));
                right++;
            }
        }

        while (left <= middle) {
            temp.add(list.get(left));
            left++;
        }

        while (right <= last) {
            temp.add(list.get(right));
            right++;
        }

        for (int i = 0; i < temp.size(); i++) {
            list.set(first + i, temp.get(i));
        }
    }
}
