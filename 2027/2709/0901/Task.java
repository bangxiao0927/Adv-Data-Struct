// Task Class
// This class stores the task name and rank.  Add methods and instance variables as you see fit. 
// Your task class should override the equals method so + contains(Object) : boolean and + remove(E) : E  can work properly.  It should also have a toString to return the name and rank of the Task.
// @Override
// public boolean equals(Object o){
// 	//cast o to the Task type ( Task other = (Task) o; )
// 	//Call the get name of the other task( other.getName() ) 
// //return true if the names are the same and false otherwise.
// }

public class Task {
    private String name;
    private int rank;

    public Task(String n, int r) {
        this.name = n;
        this.rank = r;
    }

    public String getName() {
        return this.name;
    }

    public int getRank() {
        return this.rank;
    }

    @Override
    public String toString() {
        return this.name + "\t" + this.rank;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task other = (Task) o;

        return this.name.equals(other.getName());
    }

}
