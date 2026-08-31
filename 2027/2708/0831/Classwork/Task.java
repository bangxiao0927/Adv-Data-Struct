public class Task {
//     Task Class
// This class stores the task name and rank.  Add methods and instance variables as you see fit.  
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
}
