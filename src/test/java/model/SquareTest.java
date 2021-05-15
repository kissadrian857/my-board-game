package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {
    Position position;

    @BeforeEach
    void init(){
        position=new Position(0,0);
    }
    @Test
    void testToString() {
        assertEquals("R",new Square(position,Colour.RED).toString());
        assertEquals("B",new Square(position,Colour.BLUE).toString());
    }
}