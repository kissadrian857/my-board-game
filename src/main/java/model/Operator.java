package model;

import java.util.List;

/**
 * Class that represents operators.
 */
public class Operator {
    private Position from;
    private int n;
    private int m;

    /**
     * Creates a {@code Operator} object.
     *
     * @param from the left down corner of the rectangle
     * @param n    the horizontal length of the rectangle
     * @param m    the vertical length of the rectangle
     */
    public Operator(Position from, int n, int m) {
        this.from = from;
        this.n = n;
        this.m = m;
    }

    /**
     * Determines whether the operator is applicable on a given {@code State} object.
     *
     * @param state the state that we want to know whether the operator is applicable or not
     * @return {@code true} if the operator is applicable on the given {@code State} object;
     * {@code false} otherwise
     */
    public boolean isApplicable(State state) {
        return state.colourOfSquare(from).equals(Colour.BLUE);
    }

    /**
     * Applies the operator on a given {@code State} object.
     *
     * @param state the state on which we want to apply the operator
     * @return the same object reference as the parameter on which we applied the operator
     */
    public State apply(State state) {
        int rowBound = from.getRow() - m + 1;
        int colBound = from.getCol() + n - 1;

        for (int i = rowBound; i <= from.getRow(); i++) {
            for (int j = from.getCol(); j <= colBound; j++) {
                Position p = new Position(i, j);
                if (state.colourOfSquare(p) == Colour.BLUE) {
                    state.modifySquareColour(p, Colour.RED);
                } else {
                    state.modifySquareColour(p, Colour.BLUE);
                }
            }
        }
        return state;
    }

    @Override
    public boolean equals(Object operator) {
        if (operator == this) {
            return true;
        }

        return (operator instanceof Operator o) && o.from.equals(this.from) && o.n == this.n && o.m == this.m;
    }

    /**
     * A function that returns an {@code Operator} object,if the positions in the given parameter form a rectangle in the game.
     *
     * @param positions a list that contains {@code Position} objects
     * @return {@code null} if there isn't any operators that can be created from the given positions;
     * {@code Operator} object otherwise
     */
    public static Operator of(List<Position> positions) {
        if (positions == null || positions.isEmpty()) {
            return null;
        }
        int minRow = Integer.MAX_VALUE;
        int maxRow = Integer.MIN_VALUE;
        int minCol = Integer.MAX_VALUE;
        int maxCol = Integer.MIN_VALUE;

        for (var p : positions) {
            if (p.getRow() > maxRow) maxRow = p.getRow();
            if (p.getRow() < minRow) minRow = p.getRow();
            if (p.getCol() > maxCol) maxCol = p.getCol();
            if (p.getCol() < minCol) minCol = p.getCol();
        }

        for (int i = minRow; i <= maxRow; i++) {
            for (int j = minCol; j <= maxCol; j++) {
                Position p = new Position(i, j);
                if (!positions.contains(p)) {
                    return null;
                }
            }
        }
        return new Operator(new Position(maxRow, minCol), maxCol - minCol + 1, maxRow - minRow + 1);
    }
}
