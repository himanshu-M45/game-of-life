package org.example;

import org.example.enums.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    @Test
    void testCellInitialization() {
        Cell cell = new Cell();
        assertFalse(cell.isAlive());
    }

    @Test
    void testCellState() {
        Cell cell = new Cell();
        cell.setState(State.ALIVE);
        assertTrue(cell.isAlive());
    }

    @Test
    void testCellPrintValueWithAliveState() {
        Cell cell = new Cell();
        cell.setState(State.ALIVE);
        assertDoesNotThrow(cell::printValue);
    }

    @Test
    void testCellPrintValueWithDeadState() {
        Cell cell = new Cell();
        cell.setState(State.DEAD);
        assertDoesNotThrow(cell::printValue);
    }

    @Test
    void testCellStateUpdate() {
        Cell cell = new Cell();
        assertFalse(cell.isAlive());
        cell.setState(State.ALIVE);
        assertTrue(cell.isAlive());
    }
}