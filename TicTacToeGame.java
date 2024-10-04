import java.util.InputMismatchException;
import java.util.Scanner;

class Board {

    char[][] board = new char[3][3];

    int playerInput(char playerId, int x, int y) {
        if (emptySpace(x, y)) {
            board[x][y] = playerId;
        } else {
            System.out.println("The grid is filled at this position!");
            return 0;
        }

        if (checkForWinners(x, y, playerId)) {
            return playerId;
        }
        return 0;
    }

    boolean emptySpace(int x, int y) {
        return board[x][y] == '\0';
    }

    boolean checkForWinners(int x, int y, char playerId) {
        if (board[x][0] == playerId && board[x][1] == playerId && board[x][2] == playerId) {
            return true;
        }
        if (board[0][y] == playerId && board[1][y] == playerId && board[2][y] == playerId) {
            return true;
        }
        if (x == y && board[0][0] == playerId && board[1][1] == playerId && board[2][2] == playerId) {
            return true;
        }
        if (x + y == 2 && board[0][2] == playerId && board[1][1] == playerId && board[2][0] == playerId) {
            return true;
        }
        return false;
    }

    void displayBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] == '\0' ? '-' : board[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

public class TicTacToeGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Board gameBoard = new Board();
        int currentPlayer = 1;
        int winner = 0;
        int moves = 0;

        while (moves < 9 && winner == 0) {
            System.out.println("Player " + currentPlayer + "'s turn. Enter row and column (0-2):");
            int x, y;

            try {
                x = sc.nextInt();
                y = sc.nextInt();

                if (x < 0 || x > 2 || y < 0 || y > 2) {
                    throw new InputMismatchException("Coordinates out of bounds.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter valid integer coordinates (0, 1, or 2).");
                sc.nextLine();
                continue;
            }

            if (gameBoard.emptySpace(x, y)) {
                winner = gameBoard.playerInput(currentPlayer == 1 ? 'X' : 'O', x, y);
                gameBoard.displayBoard();

                if (winner == 0) {
                    currentPlayer = (currentPlayer == 1) ? 2 : 1;
                    moves++;
                }
            } else {
                System.out.println("This position is already occupied. Try a different move.");
            }
        }

        if (winner != 0) {
            System.out.println("Player " + (winner == 'X' ? 1 : 2) + " wins!");
        } else {
            System.out.println("It's a draw!");
        }
    }
}
