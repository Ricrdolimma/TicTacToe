public class TicTac {

    private char[][] board;

    public TicTac() {
        board = new char[3][3];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = '-';
    }

    public void printBoard() {
        System.out.println("\nBoard:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
    }

    //returns true if the selected space has not been used yet
    public boolean isEmpty(int row, int col) {
        return board[row][col] == '-';
    }

    //puts x or o in the selected row and column
    public void placeMove(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    //returns the board so the logic class can check it
    public char[][] getBoard() {
        return board;
    }
}
