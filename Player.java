package tictactoe;

import java.awt.*;

public abstract class Player {

    private char character;

    public Player(char character) {
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }

    public abstract BoardPosition getNextBoardPosition();
}
