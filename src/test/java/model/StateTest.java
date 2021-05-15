package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.BoardGameModel.BOARD_SIZE;
import static org.junit.jupiter.api.Assertions.*;

class StateTest {
    State state;

    @BeforeEach
    void init() {
        state = new State();

    }

    @Test
    void startState() {
        state = State.startState();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Position position = new Position(i,j);
                if ((i + j) % 2 == 0) {
                    assertEquals(Colour.BLUE,state.colourOfSquare(position));
                }else{
                    assertEquals(Colour.RED,state.colourOfSquare(position));
                }
            }
        }
    }

    @Test
    void modifySquareColour() {
        state.modifySquareColour(new Position(0, 0), Colour.RED);
        assertEquals(Colour.RED, state.colourOfSquare(new Position(0, 0)));
        state.modifySquareColour(new Position(1, 1), Colour.BLUE);
        assertEquals(Colour.BLUE, state.colourOfSquare(new Position(1, 1)));
    }

    @Test
    void colourOfSquare() {
        state = State.startState();
        assertEquals(Colour.BLUE, state.colourOfSquare(new Position(0, 0)));
        assertEquals(Colour.RED, state.colourOfSquare(new Position(1, 0)));
    }

//    @Test
//    void testToString() {
//    }

    @Test
    void isGoal() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Position position = new Position(i,j);
                state.modifySquareColour(position,Colour.RED);
            }
        }

        assertTrue(state.isGoal());
    }
}