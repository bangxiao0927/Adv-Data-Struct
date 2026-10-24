public class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int hashCode() {
        return (int) name.charAt(0) * 31 + age;
    }

    @Override
    public boolean equals(Object obj) {
        Student other = (Student) obj;
        return name.charAt(0) == other.name.charAt(0)
                && age == other.age;
    }

    @Override
    public String toString() {
        return name + " " + age;
    }
}
