package model;

public enum Colour {
    BLUE,
    RED;

    @Override
    public String toString() {
        if (this == BLUE) return "BLUE";
        else return "RED";
    }
}
