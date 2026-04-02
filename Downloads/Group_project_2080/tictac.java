import java.util.Scanner;

public class TicTac {

    private char[][] board;
    private String player1;
    private String player2;
    private char symbol1;
    private char symbol2;
    private Scanner scanner;

    public TicTac() {
        board = new char[3][3];
        scanner = new Scanner(System.in);
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void startGame() {
        setupPlayers();
        playGame();
    }

    private void setupPlayers() {
        System.out.print("Enter Player 1 name: ");
        player1 = scanner.nextLine();

        System.out.print("Enter Player 2 name: ");
        player2 = scanner.nextLine();

        System.out.print(player1 + ", choose your symbol (X/O): ");
        symbol1 = scanner.next().toUpperCase().charAt(0);
        symbol2 = (symbol1 == 'X') ? 'O' : 'X';

        scanner.nextLine(); 
    }

    private void playGame() {
        boolean gameOver = false;
        char current = 'X';
        String currentPlayer;

        while (!gameOver) {

            printBoard();

            currentPlayer = (current == symbol1) ? player1 : player2;

            System.out.println(currentPlayer + "'s turn (" + current + ")");
            System.out.print("Enter row (0-2): ");
            int row = scanner.nextInt();
            System.out.print("Enter column (0-2): ");
            int col = scanner.nextInt();

            if (!isValidMove(row, col)) {
                System.out.println("Invalid move. Please, try again");
                continue;
            }

            board[row][col] = current;

            if (checkWin(current)) {
                printBoard();
                System.out.println(currentPlayer + " wins!");
                gameOver = true;

            } else if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw!");
                gameOver = true;

            } else {
                current = (current == 'X') ? 'O' : 'X';
            }
        }
    }

    private void printBoard() {
        System.out.println("\nBoard:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 &&
               col >= 0 && col < 3 &&
               board[row][col] == '-';
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkWin(char s) {

        // rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == s &&
                board[i][1] == s &&
                board[i][2] == s) {
                return true;
            }
        }

        // columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == s &&
                board[1][j] == s &&
                board[2][j] == s) {
                return true;
            }
        }

        // diagonals
        if (board[0][0] == s &&
            board[1][1] == s &&
            board[2][2] == s) {
            return true;
        }

        if (board[0][2] == s &&
            board[1][1] == s &&
            board[2][0] == s) {
            return true;
        }

        return false;
    }
}