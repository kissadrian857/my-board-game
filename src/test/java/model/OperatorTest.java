package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OperatorTest {
    State state;

    @BeforeEach
    void init() {
        state = State.startState();
    }

    @Test
    void isApplicable() {
        Operator o1 = new Operator(new Position(1, 1), 1, 1);
        assertTrue(o1.isApplicable(state));
        Operator o2 = new Operator(new Position(1, 0), 2, 2);
        assertFalse(o2.isApplicable(state));
    }

    @Test
    void apply() {
        Operator o = new Operator(new Position(0, 0), 1, 1);
        assertEquals(o.apply(state).colourOfSquare(new Position(0, 0)), Colour.RED);
        state = State.startState();
        o = new Operator(new Position(0, 1), 1, 1);
        assertEquals(o.apply(state).colourOfSquare(new Position(0, 1)), Colour.BLUE);
        state = State.startState();
        o = new Operator(new Position(1, 0), 2, 2);
        assertEquals(o.apply(state).colourOfSquare(new Position(1, 1)), Colour.RED);
        assertEquals(state.colourOfSquare(new Position(0, 0)), Colour.RED);
        assertEquals(state.colourOfSquare(new Position(0, 1)), Colour.BLUE);
        assertEquals(state.colourOfSquare(new Position(1, 0)), Colour.BLUE);
    }

    @Test
    void testEquals() {
        Operator o = new Operator(new Position(0, 0), 1, 1);
        assertTrue(o.equals(o));
        assertTrue(o.equals(new Operator(new Position(0, 0), 1, 1)));
        assertFalse(o.equals(new Operator(new Position(Integer.MAX_VALUE, 0), 1, 1)));
        assertFalse(o.equals((new Operator(new Position(0, Integer.MIN_VALUE), 1, 1))));
        assertFalse(o.equals((new Operator(new Position(0, 0), Integer.MAX_VALUE, 1))));
        assertFalse(o.equals((new Operator(new Position(0, 0), 1, Integer.MIN_VALUE))));
        assertFalse(o.equals((new Operator(new Position(Integer.MAX_VALUE, 0), Integer.MAX_VALUE, 1))));
        assertFalse(o.equals((new Operator(new Position(0, Integer.MIN_VALUE), 1, Integer.MIN_VALUE))));
        assertFalse(o.equals(null));
        assertFalse(o.equals("Hello World"));
    }

    @Test
    void of() {
        List<Position> positions = fillPositions(new Position(0, 0));
        assertEquals(new Operator(new Position(0, 0), 1, 1), Operator.of(positions));
        positions = fillPositions(new Position(0, 1),
                new Position(0, 0),
                new Position(1, 0),
                new Position(1, 1));
        assertEquals(new Operator(new Position(1, 0), 2, 2), Operator.of(positions));
        positions = fillPositions(new Position(1, 1),
                new Position(2, 2));
        assertEquals(null, Operator.of(positions));
        assertEquals(null, Operator.of(null));
    }

    List<Position> fillPositions(Position... p) {
        List<Position> positions = new ArrayList<>();
        for (var x : p) {
            positions.add(x);
        }
        return positions;
    }
}