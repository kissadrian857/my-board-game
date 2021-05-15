package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {
    Position position;

    @BeforeEach
    void init() {
        position = new Position(0, 0);
    }

    @Test
    void testToString() {
        assertEquals("R", new Square(position, Colour.RED).toString());
        assertEquals("B", new Square(position, Colour.BLUE).toString());
    }

    @Test
    void testEquals(){
        Square square = new Square(new Position(0,0),Colour.BLUE);
        assertTrue(square.equals(square));
        assertTrue(square.equals(new Square(new Position(0,0),Colour.BLUE)));
        assertFalse(square.equals(new Square(new Position(1,0),Colour.BLUE)));
        assertFalse(square.equals(new Square(new Position(0,1),Colour.BLUE)));
        assertFalse(square.equals(new Square(new Position(0,0),Colour.RED)));
        assertFalse(square.equals(new Square(new Position(1,1),Colour.RED)));
        assertFalse(square.equals(null));
        assertFalse(square.equals("Hello world"));
    }
}