package model;

import javafx.beans.property.ObjectProperty;

import java.util.ArrayList;
import java.util.List;

public class BoardGameModel {
    public enum Player {
        PLAYER1, PLAYER2;

        public Player alter() {
            return switch (this) {
                case PLAYER1 -> Player.PLAYER2;
                case PLAYER2 -> Player.PLAYER1;
            };
        }
    }

    public static final int BOARD_SIZE = 4;
    private List<Operator> operators;
    private State actualState;
    private Player nextPlayer;
//    private ObjectProperty<Player>nextPlayer;

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
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public boolean isValidStep(Operator operator) {
        if(operator == null){
            return false;
        }
        for (var o : operators) {
            if (operator.equals(o) && operator.isApplicable(actualState)) {
                return true;
            }
        }
        return false;
    }

    public BoardGameModel makeStep(Operator operator) {
        if (isValidStep(operator)) {
            operator.apply(actualState);
            nextPlayer = nextPlayer.alter();
        }
        return this;
    }

    public boolean isOver() {
        return actualState.isGoal();
    }

    public Colour colourOfSquare(Position position) {
        return actualState.colourOfSquare(position);
    }

//    @Override
//    public String toString() {
//        return actualState.toString();
//    }
}
