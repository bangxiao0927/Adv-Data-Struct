
// Animal
// Instance Variable(s)
// 	- type: String
// - name : String
// - age : int 
// Constructor(s)
// + Animal(String, String int) -  Sets the instance variables respectively.
// Method(s)
// + toString() : String - return the name, type, and age
// + setName(String) : void - change the name.
// + setAge(int) : void - change the age.

public class Animal {
    private static String type;
    private static String name;
    private static int age;

    public Animal(String _type, String _name, int _age) {
        this.type = _type;
        this.name = _name;
        this.age = _age;
    }

    public String toString() {
        return "name: " + name + " type: " + type + " age: " + age;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setAge(int newAge) {
        this.age = newAge;
    }
}
