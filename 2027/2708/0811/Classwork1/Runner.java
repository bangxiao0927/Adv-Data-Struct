import java.util.Scanner;

public class Runner {
    public static void main(String[] args){
        String type = null;
        String name = null;
        int age = 0;
        int decide;

        Animal an = null;
        Scanner sc = new Scanner(System.in);

        boolean quit = false;

        while (!quit){
            System.out.println("Enter 1 to create an animal, 2 to print animal, 3 to change name, 4 to change the age, 5 to quit");

            decide = sc.nextInt();

            if (decide == 1){
                System.out.println("Enter animal type: ");
                type = sc.next();
                System.out.println("Enter animal name: ");
                name = sc.next();
                System.out.println("Enter animal age: ");
                age = sc.nextInt();

                an = new Animal(type,name,age);

            }

            else if (decide == 2){
                if (an == null){
                    System.out.println("No animal is present");
                    }
                else{
                    System.out.println(an);
                }

            }

            else if (decide == 3){
                System.out.println("Enter new name: ");
                name = sc.next();

                if (an == null){
                    System.out.println("No animal is present");
                }
                else{
                    an.setName(name);
                }
            }

            else if (decide == 4){
                System.out.println("Enter new age: ");
                age = sc.nextInt();

                if (an == null){
                    System.out.println("No animal is present");
                }
                else{
                    an.setAge(age);
                }
            }

            else{
                quit = true;
            }
        }
    }
}