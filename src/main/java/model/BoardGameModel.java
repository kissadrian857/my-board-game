package model;

import java.util.ArrayList;
import java.util.List;

public class BoardGameModel {
    public static final int BOARD_SIZE = 4;
    private static final List<String> PLAYERS = new ArrayList<>();
    private List<Operator> operators;
    private State actualState;

    public BoardGameModel() {
        actualState = State.startState();

        operators = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                for (int n = 1; n <= BOARD_SIZE - j; n++) {
                    for (int m = i+1; m >= 1; m--) {
                        Position p = new Position(i, j);
                        Operator o = new Operator(p, n, m);
                        operators.add(o);
                    }
                }
            }
        }
    }

    public boolean isValidStep(Operator operator) {
        for (var o : operators) {
            if (operator.equals(o) && operator.isApplicable(actualState)) {
                return true;
            }
        }
        return false;
    }

    public BoardGameModel makeStep(Operator operator) {
        operator.apply(actualState);
        return this;
    }

    public boolean isOver() {
        return actualState.isGoal();
    }

    public Colour colourOfSquare(Position position) {
        return actualState.colourOfSquare(position);
    }

    public static List<String> getPlayers() {
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < PLAYERS.size(); i++) {
            String tempstr = new String(PLAYERS.get(i));
            temp.add(tempstr);
        }
        return temp;
    }

    public static void setPlayerNames(String playerOne, String playerTwo) {
        if (BoardGameModel.PLAYERS.size() != 2) {
            PLAYERS.add(playerOne);
            PLAYERS.add(playerTwo);
        } else {
            throw new ArrayStoreException();
        }

    }

    @Override
    public String toString() {
        return actualState.toString();
    }
}
