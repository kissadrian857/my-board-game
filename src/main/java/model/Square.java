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

    @Override
    public boolean equals(Object square) {
        if(square == this){
            return true;
        }

        return (square instanceof Square s) && s.position.equals(this.position) && s.colour == this.colour;
    }
}
