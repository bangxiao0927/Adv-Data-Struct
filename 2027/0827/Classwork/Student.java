public class Student {
//     Instance Variables
// - name : String - Name
// Constructor
// + Student(String) - Sets up the instance variable.
// Methods
// + toString : String - Return the name of the student.
// Add more methods and instance variable as needed.
    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
    
}