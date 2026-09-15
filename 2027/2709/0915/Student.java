public class Student {
    private String name;
    private int id;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return id;
    }

    @Override
    public String toString() {
        return name + "\t" + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        Student other = (Student) o;
        return id == other.getID();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
