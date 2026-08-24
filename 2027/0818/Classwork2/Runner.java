import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        GameManager gm = new GameManager();

        Scanner sc = new Scanner(System.in);

        int turn = 1;
        System.out.println("Tic-Tac-Toe: You will first enter the row of the position you want to select, then the column.");

        while (true) {
            gm.printGrid();

            if (turn == 1) {
                System.out.println("To select a position, enter the number of the row (1 is the top)");
                int row = readNumber(sc);
                System.out.println("Now enter the column (1 is left)");
                int col = readNumber(sc);

                if (!gm.insertX(row, col)) {
                    System.out.println("That position is invalid or already occupied. Try again.");
                    continue;
                }

                if (gm.checkWin()) {
                    gm.printGrid();
                    System.out.println("You win!");
                    break;
                }
                turn = 2;
            } else {
                gm.computerTurn();

                if (gm.checkWin()) {
                    gm.printGrid();
                    System.out.println("The computer wins!");
                    break;
                }
                turn = 1;
            }

            if (gm.isFull()) {
                gm.printGrid();
                System.out.println("It's a draw!");
                break;
            }
        }

        sc.close();
    }

    private static int readNumber(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Please enter a whole number.");
            sc.next();
        }
        return sc.nextInt();
    }
}
