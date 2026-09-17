public class Runner {
    public static void main(String[] args) {
        Node cat = new Node("cat");
        Node dog = new Node("dog");
        Node bird = new Node("bird");
        Node bear = new Node("bear");
        Node pig = new Node("pig");

        cat.setNext(dog);
        dog.setNext(bird);
        bird.setNext(bear);
        bear.setNext(pig);
        pig.setNext(null);

        Node current = cat;
        while (current != null) {
            System.out.println(current.get());
            current = current.next();
        }
    }
}
