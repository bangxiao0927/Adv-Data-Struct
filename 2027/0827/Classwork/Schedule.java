import java.util.ArrayList;

public class Schedule{
    private ArrayList<Pair<Integer, String>> mySchedule;

    public Schedule() {
        this.mySchedule = new ArrayList<>();
    }

    public void addCourse(int period, String course) {
        mySchedule.add(new Pair<>(period, course));
    }

    public int size() {
        return mySchedule.size();
    }

    public Pair<Integer, String> getCourse(int index) {
        return mySchedule.get(index);
    }

    public String toString() {
        String ret = "";
        for (int i = 0; i < mySchedule.size(); i++){
            ret += mySchedule.get(i).getKey() + ". ";
            ret += mySchedule.get(i).getValue()+"\n";
        }
        return ret;
    }
}
