package tictactoe;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static tictactoe.TicTacToeConstants.O;
import static tictactoe.TicTacToeConstants.X;

public class TicTacToe {
    /**
     * Main class
     * USes a scanner to talk to the user to setup and play the game
     * This scanner will be passed to the HumanPlayer in order to get the next position
     */

    public static final String INVALID_INPUT_MESSAGE = "Your input is invalid...";
    public static  final String PLAYER_CREATION_SUCCESS_MESSAGE = "Successfully created the player...";

    private Scanner scanner;
    private PrintStream output;
    private TicTacToeBoard board;
    private Player player1, player2;

    public TicTacToe(InputStream inputStream, PrintStream output) {
        scanner = new Scanner(inputStream);
        this.output = output;
    }

    public void setupGame() {
        output.println("Starting new game..");

        output.println("Setting up player 1");
        player1 = setupPlayer(X);

        output.println("Setting up player 2");
        player2 = setupPlayer(O);

        board = new TicTacToeBoard();
        output.println("Starting game...");
    }

    public void playGame() {
        Player currentPlayer = player1;
        int playerNum = 1;

        //Game loop
        //Keeps the game going - keep switching from 1 player to another until the game is over
        while (true) {
            //Put below in a method - take currentPlayer  & playerNum as a param
            //Keep asking a player to make a move until it's valid
            while (true) {
                output.println("Player " + playerNum + "'s turn...");
                BoardPosition boardPosition = currentPlayer.getNextBoardPosition();
                try {
                    board.insert(currentPlayer.getCharacter(), boardPosition);
                    break;
                } catch (OutOfBoardBoundsException e) {
                    System.out.println("You entered a position out of bounds of the board");
                } catch (PositionFilledException e) {
                    System.out.println("This position is already filled");
                }

            }

            System.out.println("Current board state:");
            System.out.println(board);

            if (board.checkForWinner()) {
                System.out.println("The game has been won. Game over.");
                break;
            } else if (board.isBoardFull()) {
                System.out.println("There has been a draw. Game over.");
                break;
            }


            if (playerNum == 1) {
                playerNum = 2;
                currentPlayer = player2;
            } else {
                playerNum = 1;
                currentPlayer = player1;
            }
        }

    }

    private Player setupPlayer(char symbol) {
        while (true) {
            output.println("Enter H for human or C for computer..");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("h")) {
                output.println(PLAYER_CREATION_SUCCESS_MESSAGE);
                return new HumanPlayer(symbol, scanner);
            } else if (input.equalsIgnoreCase("c")) {
                output.println(PLAYER_CREATION_SUCCESS_MESSAGE);
                return new ComputerPlayer(symbol);
            } else {
                output.println(INVALID_INPUT_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe(System.in, System.out);
        game.setupGame();
        game.playGame();
    }

}
