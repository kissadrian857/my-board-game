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
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if ((i + j) % 2 == 0){
                    assertFalse(boardGameModel.isOver());
                    boardGameModel.makeStep(new Operator(new Position(i,j),1,1));

                }
            }
        }
        assertTrue(boardGameModel.isOver());
    }

    @Test
    void colourOfSquare() {
        assertEquals(Colour.RED, boardGameModel.colourOfSquare(new Position(0, 1)));
        assertEquals(Colour.BLUE, boardGameModel.colourOfSquare(new Position(0, 0)));
    }
}