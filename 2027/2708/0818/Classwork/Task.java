public class Task {
    private String task;
    private int rank = 1;
    
    public Task (String ts, int rk){
        task = ts;
        rank = rk;
    }


    // Methods
    // +getTask() : String - Return the task.
    // +getRank() : int - Return the rank.
    // +toString() : String - Return the rank and task as a String. 
    
    public String getTask() {
        return task;
    }

    public int getRank() {
        return rank;
    }

    public String toString() {
        return "task: " + task + "rank: " + rank;
    }
}
