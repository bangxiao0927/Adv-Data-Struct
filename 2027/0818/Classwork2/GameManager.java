public class GameManager{
    private String[][] grid = new String[3][3];

    public GameManager(){
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++){
                grid[i][j] = "*";
            }
        }
    }

    public void resetGrid() {
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++){
                grid[i][j] = "*";
            }
        }
    }

    public void printGrid() {
        for(int i = 0 ; i < this.grid.length; i++) {
            for(int j = 0; j < this.grid.length; j++) {
                System.out.print(this.grid[i][j]);
            }
            System.out.print("\n");
        }
    }

    public boolean insertX(int row, int col){
        if (grid[row-1][col-1].equals("*")){
            grid[row-1][col-1] = "X";
            return true;
        }
        return false;
    }

    public boolean insertO(int row, int col){
        if (grid[row-1][col-1].equals("*")){
            grid[row-1][col-1] = "O";
            return true;
        }
        return false;     
    }

    public boolean checkWin() {
        // diagnal
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][0] != "*"
                    && grid[i][0] == grid[i][1]
                    && grid[i][1] == grid[i][2]) {
                return true;
            }

            if (grid[0][i] != "*"
                    && grid[0][i] == grid[1][i]
                    && grid[1][i] == grid[2][i]) {
                return true;
            }
        }

        // start from origin
        if (grid[0][0] != "*"
                && grid[0][0] == grid[1][1]
                && grid[1][1] == grid[2][2]) {
            return true;
        }

        // start from then end corner
        return grid[0][2] != "*"
                && grid[0][2] == grid[1][1]
                && grid[1][1] == grid[2][0];
    }

    public void computerTurn(){
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++){
                if (grid[i][j].equals("*")){
                    insertO(i,j);
                    i = grid.length;
                    j = grid[i].length;
                }
            }
        }
    }

    // WOWWWOWO
    // public boolean checkWin() {
    //     return checkWinHelper(0, 0, 0);
    // }

    // public boolean checkWinHelper(int row, int col, int type) {
    //     if ((row == 0 && col == 2) || (row == 2 && col == 0) || (row == 2 && col == 2)) {
    //         if (type == 1) {
    //             return true;
    //         } else if (type == 0) {
    //             return false;
    //         }
    //     }

    //     boolean a = checkWinHelper(row+1, col, type);
    //     boolean b = checkWinHelper(row, col+1, type);

    //     boolean result = a || b;
    //     return result;
    // }
}
