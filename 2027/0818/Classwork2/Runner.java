import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        GameManager gm = new GameManager();

        Scanner sc = new Scanner(System.in);

        boolean over = false;
        int turn = 1;
        System.out.println("Tic-Tac-Toe: You will first enter the row of the position you want to select, then the column.");

        while (!over){

            gm.printGrid();

            if (turn == 1){
                System.out.println("To select a position, enter the number of the row (1 is the top)");
                int row = sc.nextInt();
                System.out.println("Now enter the column (1 is left)");
                int col = sc.nextInt();
                gm.insertX(row, col);
                turn = 2;
            } else{
                gm.computerTurn();
                turn = 1;
            }
        }
    }
}