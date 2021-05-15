package model;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ColourTest {

    @Test
    void testToString() {
        assertEquals("BLUE", Colour.BLUE.toString());
        assertEquals("RED", Colour.RED.toString());
    }
}