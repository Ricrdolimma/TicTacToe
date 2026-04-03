public class GameLogic {

    //checks if the row and column are inside the board and if the spot is empty
    public boolean isValidMove(TicTac board, int row, int col) {
        return row >= 0 && row < 3 &&
               col >= 0 && col < 3 &&
               board.isEmpty(row, col);
    }

    //goes through the whole board to see if there are still empty spaces
    public boolean isBoardFull(TicTac board) {
        char[][] b = board.getBoard();

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (b[i][j] == '-') return false;

        return true;
    }

    //checks rows, columns and diagonals to see if one symbol won
    public boolean checkWin(TicTac board, char s) {
        char[][] b = board.getBoard();

        for (int i = 0; i < 3; i++)
            if (b[i][0] == s && b[i][1] == s && b[i][2] == s)
                return true;

        for (int j = 0; j < 3; j++)
            if (b[0][j] == s && b[1][j] == s && b[2][j] == s)
                return true;

        if (b[0][0] == s && b[1][1] == s && b[2][2] == s)
            return true;

        if (b[0][2] == s && b[1][1] == s && b[2][0] == s)
            return true;

        return false;
    }

    //minimax tests future moves and gives a score back to the ai
    public int minimax(TicTac board, boolean isMax, char aiSymbol, char humanSymbol) {

    if (checkWin(board, aiSymbol)) return 1;
    if (checkWin(board, humanSymbol)) return -1;
    if (isBoardFull(board)) return 0;

    //max means the ai is trying to get the highest score
    if (isMax) {
        int best = Integer.MIN_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (board.isEmpty(i, j)) {
                    board.placeMove(i, j, aiSymbol);

                    int score = minimax(board, false, aiSymbol, humanSymbol);

                    board.placeMove(i, j, '-'); // undo move
                    best = Math.max(best, score);
                }
            }
        }
        return best;

    //min means the human turn is trying to lower the ai score
    } else {
        int best = Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (board.isEmpty(i, j)) {
                    board.placeMove(i, j, humanSymbol);

                    int score = minimax(board, true, aiSymbol, humanSymbol);

                    board.placeMove(i, j, '-');
                    best = Math.min(best, score);
                }
            }
        }
        return best;
    }
    }

    // checks every possible move and keeps the one with the best score
    public int[] findBestMove(TicTac board, char aiSymbol, char humanSymbol) {
        int bestScore = Integer.MIN_VALUE;
        int[] move = {-1, -1};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.isEmpty(i, j)) {
                    board.placeMove(i, j, aiSymbol);
                    int score = minimax(board, false, aiSymbol, humanSymbol);
                    board.placeMove(i, j, '-');

                    if (score > bestScore) {
                        bestScore = score;
                        move[0] = i;
                        move[1] = j;
                    }
                }
            }
        }

        return move;
    }
}
