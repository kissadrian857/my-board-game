package model;

import org.tinylog.Logger;

import static model.BoardGameModel.BOARD_SIZE;

/**
 * Represents a game state.
 */
public class State {
    private Square[][] squares = new Square[BoardGameModel.BOARD_SIZE][BoardGameModel.BOARD_SIZE];

    /**
     * Constructor that creates a {@code State} object and initializes all its squares' row and column coordinates
     * with the positions from 0 to board size.
     */
    public State() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Position p = new Position(i, j);
                squares[i][j] = new Square(p, null);
            }
        }
        Logger.debug("State object initialized.");
    }

    /**
     * Creates a start {@code State} object for the game.
     *
     * @return the start state of a game.
     */
    public static State startState() {
        State s = new State();

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if ((i + j) % 2 == 0) {
                    s.squares[i][j].setColour(Colour.BLUE);
                } else {
                    s.squares[i][j].setColour(Colour.RED);
                }
            }
        }

        return s;
    }

    /**
     * Modifies a {@code Square} object on the selected position with the given colour.
     *
     * @param position the position of the square which colour we want to modify
     * @param colour   the colour that we want to replace the original with
     * @return the same {@code State} object on which this method is applied,but the colour of square on {@paramref position} is
     * set to {@paramref colour}
     */
    public State modifySquareColour(Position position, Colour colour) {
        int row = position.getRow();
        int col = position.getCol();
        squares[row][col].setColour(colour);
        return this;
    }

    /**
     * Returns the colour of square on a given position.
     *
     * @param position the position of the square which colour we want to know
     * @return the colour of the square on {@paramref position}
     */
    public Colour colourOfSquare(Position position) {
        int row = position.getRow();
        int col = position.getCol();
        return squares[row][col].getColour();
    }

    /**
     * Determines whether the state is goal state or not.
     *
     * @return {@code true} if it's a goal state
     * {@code false} otherwise
     */
    public boolean isGoal() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (squares[i][j].getColour() == Colour.BLUE) return false;
            }
        }
        return true;
    }

}
