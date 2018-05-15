package tictactoe;

import java.util.Arrays;

import static tictactoe.TicTacToeConstants.EMPTY;
import static tictactoe.TicTacToeConstants.O;
import static tictactoe.TicTacToeConstants.X;

public class TicTacToeBoard {

    private char[][] board;

    private final String winningXCombination;
    private final String winningOCombination;

    public TicTacToeBoard() {
        board = new char[3][3];
        emptyBoard();
        winningXCombination = "" + X + X + X;
        winningOCombination = "" + O + O + O;
    }

    public boolean checkForWinner () {
        return checkVerticals() || checkHorizontals() || checkLeftDiagonal() || checkRightDiagonal();
    }

    public void insert(char p, BoardPosition boardPosition) throws OutOfBoardBoundsException, PositionFilledException {
        int row = boardPosition.getRow();
        int column = boardPosition.getColumn();

        validatePositionInBounds(row);
        validatePositionInBounds(column);

        if (board[row][column] != EMPTY) {
            throw new PositionFilledException();
        }

        board[row][column] = p;//.getCharacter();
    }
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public void emptyBoard() {
        for (int i = 0; i < 3; ++i) {
            for (int j =0; j < 3; ++j) {
                board[i][j] = EMPTY;
            }
        }
    }

    public boolean isBoardEmpty() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public char[][] getBoard() {
        return Arrays.copyOf(board, board.length);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                sb.append(board[row][column]).append(" | ");
            }
            sb.deleteCharAt(sb.length() - 2);
            sb.append("\n");
        }
        return sb.toString();
    }

    private void validatePositionInBounds(int position) throws OutOfBoardBoundsException {
        if (position < 0 || position > 2) {
            throw new OutOfBoardBoundsException();
        }
    }

    private boolean checkVerticals() {
        for (int column = 0; column < 3; column++) {
            String columnContents = "";
            for (int row = 0; row < 3; ++row) {
                columnContents += board[row][column];
            }
            if (columnContents.equals(winningXCombination) || columnContents.equals(winningOCombination)) {
                return true;
            }
        }

        return false;
    }

    private boolean checkHorizontals() {
        for (int row = 0; row < 3; row++) {
            String rowContents = new String(board[row]);
            if (rowContents.equals(winningOCombination) || rowContents.equals(winningXCombination)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkLeftDiagonal() {
        String diagonalContents = "";
        for (int i = 0; i < 3; i++) {
            diagonalContents += board[i][i];
        }
        return diagonalContents.equals(winningXCombination) || diagonalContents.equals(winningOCombination);
    }

    private boolean checkRightDiagonal() {
        String diagonalContents = "";
        int row = 0;
        int column = 2;
        while (row < 3 && column >= 0) {
            diagonalContents += board[row][column];
            row++;
            column--;
        }
        return diagonalContents.equals(winningXCombination) || diagonalContents.equals(winningOCombination);
    }

}
