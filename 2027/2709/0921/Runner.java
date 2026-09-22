import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Node<String> head = new Node<String>(null);
        Node<String> tail = new Node<String>(null);

        Node<String> cow = new Node<String>("cow");
        Node<String> cat = new Node<String>("cat");
        Node<String> dog = new Node<String>("dog");
        Node<String> bird = new Node<String>("bird");
        Node<String> bear = new Node<String>("bear");

        head.setNext(cow);
        cow.setPrev(head);

        cow.setNext(cat);
        cat.setPrev(cow);

        cat.setNext(dog);
        dog.setPrev(cat);

        dog.setNext(bird);
        bird.setPrev(dog);

        bird.setNext(bear);
        bear.setPrev(bird);

        bear.setNext(tail);
        tail.setPrev(bear);

        System.out.println("Forward:");
        Node<String> current = head;
        while (current != null) {
            System.out.println(current.get());
            current = current.next();
        }

        System.out.println("Backward:");
        current = tail;
        while (current != null) {
            System.out.println(current.get());
            current = current.prev();
        }

        Scanner input = new Scanner(System.in);
        System.out.print("Enter an animal name: ");
        String animalName = input.nextLine();

        Node<String> newAnimal = new Node<String>(animalName);
        Node<String> oldLast = tail.prev();

        oldLast.setNext(newAnimal);
        newAnimal.setPrev(oldLast);
        newAnimal.setNext(tail);
        tail.setPrev(newAnimal);

        System.out.println("Forward after adding animal:");
        current = head;
        while (current != null) {
            System.out.println(current.get());
            current = current.next();
        }

        input.close();
    }
}
