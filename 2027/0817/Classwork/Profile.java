
public class Profile {
    private String name;
    private int age;

    public Profile(String nm, int ag){
        name = nm;
        age = ag;
    }
    
    public String getName(){
        return name;
    }



    // +getAge() : int - Return the age.
    // +toString() : String - Return the name and age.
    public int getAge() {
        return this.age;
    }

    public String toString() {
        return name + " (age " + age + ")";
    }


}


