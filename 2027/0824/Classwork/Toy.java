public class Toy {
//     Toy Class
// Instance Variables
// 	- name : String - Food name.
// - ageGroup : int - age the toy is designed for
// Constructor
// 	+ Toy(String, ageGroup) - Sets up the instance variables accordingly.
// Methods
// 	+ toString() : String - Return the name and age group.

    private String name;

    private int ageGroup;

    public Toy(String name, int ageGroup) {
        this.name = name;
        this.ageGroup = ageGroup;
    }

    public String toString() {
        return "name: " + name + " ageGroup: " + ageGroup + ", ";
    }
}