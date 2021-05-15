package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardGameModelTest {
    BoardGameModel boardGameModel;

    @BeforeEach
    void init() {
        boardGameModel = new BoardGameModel();
    }

    @Test
    void isValidStep() {
        Operator o = new Operator(new Position(0, 0), 1, 1);
        assertTrue(boardGameModel.isValidStep(o));
        o = new Operator(new Position(0, 0), 2, 2);
        assertFalse(boardGameModel.isValidStep(o));
        assertFalse(boardGameModel.isValidStep(null));
    }

    @Test
    void makeStep() {

    }

    @Test
    void isOver() {
    }

    @Test
    void colourOfSquare() {
    }
}