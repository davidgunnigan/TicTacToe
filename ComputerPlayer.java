package tictactoe;

import java.util.Random;

public class ComputerPlayer extends Player {

    public ComputerPlayer(char character) {
        super(character);
    }

    @Override
    public BoardPosition getNextBoardPosition() {
        Random random = new Random();
        int row = random.nextInt(3);
        int column = random.nextInt(3);
        return new BoardPosition(row, column);
    }

}
