import java.util.Scanner;

public class Runner {
    
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        Profile[] profiles = new Profile[10];

        profiles[0] = new Profile("Ayla", 16);
        profiles[1] = new Profile("Bang", 16);
        profiles[2] = new Profile("Amy", 20);
        profiles[3] = new Profile("Jack", 54);
        profiles[4] = new Profile("Caleb", 27);
        profiles[5] = new Profile("John", 81);
        profiles[6] = new Profile("Hanna", 18);
        profiles[7] = new Profile("Ben", 42);
        profiles[8] = new Profile("Troy", 41);
        profiles[9] = new Profile("Jane", 36);

        System.out.println("Select 1 to sort by age, 2 to scramble list, 3 to sort by name");
        int sel = sc.nextInt();

        Sorting sorting = new Sorting();

        if (sel == 1) {
            sorting.sortAge(profiles);
        } else if (sel == 2) {
            sorting.scramble(profiles);
        } else if (sel == 3) {
            sorting.sortName(profiles);
        } else {
            System.out.println("Invalid selection.");
            sc.close();
            return;
        }

        for (Profile profile : profiles) {
            System.out.println(profile);
        }

        sc.close();
    }

}
