public class Runner {
    public static void main(String[] args) {
        MyHashMap<Integer, String> studentMap = new MyHashMap<>();

        // add 3 students
        studentMap.put(1, "Bang");
        studentMap.put(2, "James");
        studentMap.put(3, "John");

        DLList<Integer> ids = studentMap.keySet().toDLList();
        for (int i = 0; i < ids.size(); i++) {
            int id = ids.get(i);
            System.out.println("ID: " + id + "  Name: " + studentMap.get(id));
        }
        System.out.println("Size: " + studentMap.size());
        System.out.println();

        // add a student with an existing ID, the name should be replaced
        studentMap.put(2, "James Lee");

        ids = studentMap.keySet().toDLList();
        for (int i = 0; i < ids.size(); i++) {
            int id = ids.get(i);
            System.out.println("ID: " + id + "  Name: " + studentMap.get(id));
        }
        System.out.println("Size: " + studentMap.size());
        System.out.println();

        // remove a student that exists
        studentMap.remove(1);

        ids = studentMap.keySet().toDLList();
        for (int i = 0; i < ids.size(); i++) {
            int id = ids.get(i);
            System.out.println("ID: " + id + "  Name: " + studentMap.get(id));
        }
        System.out.println("Size: " + studentMap.size());
        System.out.println();

        // remove a student that does not exist, nothing should change
        studentMap.remove(99);

        ids = studentMap.keySet().toDLList();
        for (int i = 0; i < ids.size(); i++) {
            int id = ids.get(i);
            System.out.println("ID: " + id + "  Name: " + studentMap.get(id));
        }
        System.out.println("Size: " + studentMap.size());
    }
}