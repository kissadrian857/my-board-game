package model;

import static model.BoardGameModel.BOARD_SIZE;

public class State {
    private Square[][] squares = new Square[BoardGameModel.BOARD_SIZE ][BoardGameModel.BOARD_SIZE ];

    public State() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Position p = new Position(i, j);
                squares[i][j] = new Square(p, null);
            }
        }
    }

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

    public State modifySquareColour(Position position, Colour colour) {
        int row = position.getRow();
        int col = position.getCol();
        squares[row][col].setColour(colour);
        return this;
    }

    public Colour colourOfSquare(Position position) {
        int row = position.getRow();
        int col = position.getCol();
        return squares[row][col].getColour();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                s.append(squares[i][j].toString()).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public boolean isGoal(){
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (squares[i][j].getColour() == Colour.BLUE) return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
