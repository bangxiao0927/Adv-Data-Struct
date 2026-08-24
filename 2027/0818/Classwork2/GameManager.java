public class GameManager {
    private static final String EMPTY = "*";
    private final String[][] grid = new String[3][3];

    public GameManager(){
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++){
                grid[i][j] = EMPTY;
            }
        }
    }

    public void resetGrid() {
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++){
                grid[i][j] = EMPTY;
            }
        }
    }

    public void printGrid() {
        for(int i = 0 ; i < this.grid.length; i++) {
            for(int j = 0; j < this.grid[i].length; j++) {
                System.out.print(this.grid[i][j]);
            }
            System.out.print("\n");
        }
    }

    public boolean insertX(int row, int col){
        return insert(row, col, "X");
    }

    public boolean insertO(int row, int col){
        return insert(row, col, "O");
    }

    private boolean insert(int row, int col, String mark) {
        if (row < 1 || row > grid.length || col < 1 || col > grid[0].length) {
            return false;
        }

        if (grid[row - 1][col - 1].equals(EMPTY)) {
            grid[row - 1][col - 1] = mark;
            return true;
        }
        return false;
    }

    public boolean checkWin() {
        for (int i = 0; i < grid.length; i++) {
            if (!grid[i][0].equals(EMPTY)
                    && grid[i][0].equals(grid[i][1])
                    && grid[i][1].equals(grid[i][2])) {
                return true;
            }

            if (!grid[0][i].equals(EMPTY)
                    && grid[0][i].equals(grid[1][i])
                    && grid[1][i].equals(grid[2][i])) {
                return true;
            }
        }

        if (!grid[0][0].equals(EMPTY)
                && grid[0][0].equals(grid[1][1])
                && grid[1][1].equals(grid[2][2])) {
            return true;
        }

        return !grid[0][2].equals(EMPTY)
                && grid[0][2].equals(grid[1][1])
                && grid[1][1].equals(grid[2][0]);
    }

    public boolean isFull() {
        for (String[] row : grid) {
            for (String cell : row) {
                if (cell.equals(EMPTY)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void computerTurn() {
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++){
                if (grid[i][j].equals(EMPTY)){
                    insertO(i + 1, j + 1);
                    return;
                }
            }
        }
    }
}
