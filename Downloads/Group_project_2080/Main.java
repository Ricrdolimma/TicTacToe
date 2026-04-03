import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        new Main().start();
    }

    public void start() {

        //creating scanner and the objects needed to run the game
        Scanner scanner = new Scanner(System.in);
        TicTac board = new TicTac();
        GameLogic logic = new GameLogic();

        System.out.print("Enter Player 1 name: ");
        if (!scanner.hasNextLine()) {
            System.out.println("No input provided.");
            return;
        }
        String name1 = scanner.nextLine();

        //this loop keeps asking until the user chooses player or ai
        boolean playWithAI;
        while (true) {
            System.out.print("Play against another player or AI? (P/A): ");
            if (!scanner.hasNext()) {
                System.out.println("No input provided.");
                return;
            }
            String mode = scanner.next().toUpperCase();

            if (mode.length() == 1 && (mode.charAt(0) == 'P' || mode.charAt(0) == 'A')) {
                playWithAI = (mode.charAt(0) == 'A');
                break;
            }
            System.out.println("Invalid input.");
        }

        //if user chose ai, player 2 name is set automatically
        //otherwise it asks for the second player's name
        String name2;
        if (playWithAI) {
            name2 = "AI";
        } else {
            scanner.nextLine();
            System.out.print("Enter Player 2 name: ");
            if (!scanner.hasNextLine()) {
                System.out.println("No input provided.");
                return;
            }
            name2 = scanner.nextLine();
        }

        //player 1 chooses the symbol and player 2 gets the other one
        char symbol1;
        while (true) {
            System.out.print(name1 + ", choose X or O: ");
            if (!scanner.hasNext()) {
                System.out.println("No input provided.");
                return;
            }
            String input = scanner.next().toUpperCase(); 
        

            //need to put length == 1 so only one char is accepted
            //if = x or = O, symbol1 will be the first choice
            if (input.length() == 1 && (input.charAt(0) == 'X' || input.charAt(0) == 'O')) {
                symbol1 = input.charAt(0);
                break;
            }
            System.out.println("Invalid input.");
        }


        char symbol2 = (symbol1 == 'X') ? 'O' : 'X';

        //creating both players with their names and symbols
        Player p1 = new Player(name1, symbol1);
        Player p2 = new Player(name2, symbol2);

        //x always starts first in tic tac toe(regra do jogo da velha)
        char current = 'X';
        boolean gameOver = false;

        //this loop keeps running until someone wins or the board is full
        while (!gameOver) {

            board.printBoard();

            Player currentPlayer = (current == p1.getSymbol()) ? p1 : p2; //will return the current symol for either player 1 or 2

            //if ai mode is on and it is player 2 turn, the ai picks the move
            if (playWithAI && currentPlayer == p2) {
                System.out.println(currentPlayer.getName() + "'s turn (" + current + ")");
                int[] move = logic.findBestMove(board, p2.getSymbol(), p1.getSymbol()); //minimax used through findBestMove()
                board.placeMove(move[0], move[1], current);
                System.out.println("AI chose: " + move[0] + " " + move[1]);
            } else {
                //if it is not the ai turn, ask the current player for row and column
                System.out.println(currentPlayer.getName() + "'s turn (" + current + ")");
                System.out.print("Enter row and column (0-2 0-2): ");

                if (!scanner.hasNextInt()) {
                    if (!scanner.hasNext()) {
                        System.out.println("No input provided.");
                        return;
                    }
                    System.out.println("Invalid input. Please enter two numbers.");
                    scanner.next();
                    continue;
                }
                int row = scanner.nextInt();

                if (!scanner.hasNextInt()) {
                    if (!scanner.hasNext()) {
                        System.out.println("No input provided.");
                        return;
                    }
                    System.out.println("Invalid input. Please enter two numbers");
                    scanner.next();
                    continue;
                }
                int col = scanner.nextInt();

                if (!logic.isValidMove(board, row, col)) {
                    System.out.println("Invalid move.");
                    continue;
                }

                //if the move is valid it'll place it on the board
                board.placeMove(row, col, current);
            }

            //after each turn check if there is a winner or a draw
            if (logic.checkWin(board, current)) {
                board.printBoard();
                System.out.println(currentPlayer.getName() + " wins!");
                gameOver = true;

            } else if (logic.isBoardFull(board)) {
                board.printBoard();
                System.out.println("Draw!");
                gameOver = true;

            } else {
                //switch turn from x to o or from o to x
                current = (current == 'X') ? 'O' : 'X';
            }
        }
    }
}
