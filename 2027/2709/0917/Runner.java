public class Runner{
    public static void main(String[] args){
        SSList<Integer> theList = new <Integer>SLList();
        for(int i = 0;i < 5000;i++) {
            theList.add((int)(Math.random() * theList.size() + 1),(int)(Math.random() * 9998 + 1));
        }

        for(int i = 0;i < 5000;i++) {
            System.out.print(theList.get(i) + "; ");
        }

        System.out.print(theList.size());


        boolean stop = false;

        while(!stop){
            if (choice == 1){
                System.out.println(list.toString());
            } else if (choice == 2){
                for (int i = 0; i < list.size(); i++) {
                    int randomIndex = (int) (Math.random() * list.size());
                    int temp = list.get(i);
                    list.set(i, list.get(randomIndex));
                    list.set(randomIndex, temp);
                }
            } else if (choice == 3){
                for (int pass = 0; pass < list.size() - 1; pass++) {
                    for (int i = 0; i < list.size() - 1 - pass; i++) {
                        if (list.get(i) > list.get(i + 1)) {
                            int temp = list.get(i);
                            list.set(i, list.get(i + 1));
                            list.set(i + 1, temp);
                        }
                    }
                }
            } else if (choice == 4){
                while (list.size() > 0){
                    int rand = (int) (Math.random() * list.size());
                    
                }
            } else{

            }
        }
    }
}