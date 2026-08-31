public class Runner {
//     Runner
// Create a Runner.  Instantiate a  MyArrayList<Task> object.  
// Add 100 tasks in a loop.  The task name will consist of the word "task" + an integer (e.g. "task1",, "task2", "task3", etc..). 
// Have random numbers from 1 to 30 for the ranks.  Print out all the tasks in the list.

    public static void main(String[] args) {
        MyArrayList<Task> myList = new MyArrayList<>();

        System.out.println("TASK:\tRANK:");

        for (int i = 1; i <= 100; i++) {
            int rank = (int) (Math.random() * 29) + 1;

            myList.add(new Task("task" + i, rank));
        }

        for (int i = 0; i < myList.size(); i++) {
            System.out.println(myList.get(i));
        }
    }

}
