package model;

public class Square {
    private Position position;
    private Colour colour;

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public Square(Position position, Colour colour) {
        this.position = position;
        this.colour = colour;
    }

    public Position getPosition() {
        return position;
    }

    public Colour getColour() {
        return colour;
    }

    @Override
    public String toString() {
        if (this.colour == Colour.BLUE) return "B";
        else return "R";
    }
}
