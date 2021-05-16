package model;

import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents the board game and contains business logic.
 */
public class BoardGameModel {
    /**
     * Enum that represents two players playing the board game.
     */
    public enum Player {
        PLAYER1, PLAYER2;

        public Player alter() {
            return switch (this) {
                case PLAYER1 -> Player.PLAYER2;
                case PLAYER2 -> Player.PLAYER1;
            };
        }
    }

    /**
     * Size of the board
     */
    public static final int BOARD_SIZE = 4;
    private List<Operator> operators;
    private State actualState;
    private Player nextPlayer;

    /**
     * Creates a {@code BoardGameModel} object and creates all the possible valid operators and stores it in a {@code List} object.
     */
    public BoardGameModel() {
        actualState = State.startState();

        nextPlayer = Player.PLAYER1;

        operators = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                for (int n = 1; n <= BOARD_SIZE - j; n++) {
                    for (int m = i + 1; m >= 1; m--) {
                        Position p = new Position(i, j);
                        Operator o = new Operator(p, n, m);
                        operators.add(o);
                    }
                }
            }
        }

        Logger.debug("BoardGameModel object initialized.");
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    /**
     * Checks if the given operator is applicable on the actual state.
     *
     * @param operator the operator to be checked
     * @return {@code true} if possible to apply the given operator on the actual state;
     *         {@code false} otherwise
     */
    public boolean isValidStep(Operator operator) {
        if (operator == null) {
            return false;
        }
        for (var o : operators) {
            if (operator.equals(o) && operator.isApplicable(actualState)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Applies an operator on the actual state,if possible.
     *
     * @param operator the operator to be applied on the actual state
     * @return the same object on which the function is invoked
     */
    public BoardGameModel makeStep(Operator operator) {
        if (isValidStep(operator)) {
            operator.apply(actualState);
            nextPlayer = nextPlayer.alter();
        }
        return this;
    }

    /**
     * Determines whether the game is over or not.
     *
     * @return {@code true} if the game is over;
     *         {@code false} otherwise
     */
    public boolean isOver() {
        return actualState.isGoal();
    }

    /**
     * Returns the colour of the square on the actual position.
     *
     * @param position the position on which we want to know the colour
     * @return colour of the square on position
     */
    public Colour colourOfSquare(Position position) {
        return actualState.colourOfSquare(position);
    }

}
