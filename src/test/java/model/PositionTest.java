package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    static Stream<Position> positionProvider() {
        return Stream.of(new Position(0, 0),
                new Position(1, 1),
                new Position(3, 2),
                new Position(1, 4));
    }

    void assertPosition(int expectedRow, int expectedCol, Position position) {
        assertAll("position",
                () -> assertEquals(expectedRow, position.getRow()),
                () -> assertEquals(expectedCol, position.getCol())
        );
    }

    @ParameterizedTest
    @MethodSource("positionProvider")
    void testEquals(Position position) {
        assertTrue(position.equals(position));
        assertTrue(position.equals(new Position(position.getRow(), position.getCol())));
        assertFalse(position.equals(new Position(Integer.MIN_VALUE, position.getCol())));
        assertFalse(position.equals(new Position(position.getRow(), Integer.MAX_VALUE)));
        assertFalse(position.equals(new Position(Integer.MIN_VALUE, Integer.MAX_VALUE)));
        assertFalse(position.equals(null));
        assertFalse(position.equals("Hello, World!"));
    }

    @ParameterizedTest
    @MethodSource("positionProvider")
    void testToString(Position position) {
        assertEquals(String.format("(%d,%d)", position.getRow(), position.getCol()), position.toString());
    }
}