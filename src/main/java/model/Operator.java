package model;

import java.util.List;
import java.util.StringJoiner;

public class Operator {
    private Position from;
    private int n;
    private int m;

    public Operator(Position from, int n, int m) {
        this.from = from;
        this.n = n;
        this.m = m;
    }

    public boolean isApplicable(State state) {
        return state.colourOfSquare(from).equals(Colour.BLUE);
    }

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

//    @Override
//    public String toString() {
//        StringBuilder s = new StringBuilder();
//        StringJoiner sj = new StringJoiner(",", "(", ")");
//        sj.add(Integer.toString(from.getRow()))
//                .add(Integer.toString(from.getCol()))
//                .add(Integer.toString(n))
//                .add(Integer.toString(m));
//
//        return s.append(sj.toString()).toString();
//    }

    @Override
    public boolean equals(Object operator) {
        if (operator == this) {
            return true;
        }

        return (operator instanceof Operator o) && o.from.equals(this.from) && o.n == this.n && o.m == this.m;
    }

    public static Operator of(List<Position> positions) {
        if(positions==null || positions.isEmpty()){
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
