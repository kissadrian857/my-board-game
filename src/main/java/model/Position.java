package model;

import org.tinylog.Logger;

public class Position {
    private int row;
    private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        return (o instanceof Position p) && p.row == this.row && p.col == this.col;
    }

    @Override
    public String toString() {
        return "(" + row+","+col+")";
    }
}
