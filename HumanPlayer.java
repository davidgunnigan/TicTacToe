package tictactoe;

import java.util.Scanner;

public class HumanPlayer  extends  Player {

    private Scanner scanner;

    public HumanPlayer(char character, Scanner scanner) {
        super(character);
        this.scanner = scanner;
    }

    @Override
    public BoardPosition getNextBoardPosition() {
        System.out.println("Enter a row and column number in the format ROW,COLUMN [0-2] for your next move");
        // split is
        String[] entered = scanner.nextLine().split(",");
        //Add more validation here, should fail gracefully
        return new BoardPosition(Integer.parseInt(entered[0]), Integer.parseInt(entered[1]));
    }
}
