package org.example;

import org.example.Enum.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    @Test
    void TestCellInitialization() {
        Cell cell = new Cell();
        assertFalse(cell.isAlive());
    }

    @Test
    void TestSetStateToAlive() {
        Cell cell = new Cell();
        cell.setState(3); // 3 neighbors should make a dead cell alive
        assertTrue(cell.isAlive());
    }

    @Test
    void TestSetStateToDeadFromAlive() {
        Cell cell = new Cell();
        cell.setInitialAlive();
        cell.setState(1); // Less than 2 neighbors should make an alive cell dead
        assertFalse(cell.isAlive());
    }

    @Test
    void TestSetStateToDeadFromAliveWithMoreThan3Neighbors() {
        Cell cell = new Cell();
        cell.setInitialAlive();
        cell.setState(4); // More than 3 neighbors should make an alive cell dead
        assertFalse(cell.isAlive());
    }

    @Test
    void TestCellLifeThroughDifferentNumberOfNeighbours() {
        Cell cell = new Cell();
        cell.setInitialAlive();

        cell.setState(2); // 2 neighbors should keep an alive cell alive
        assertTrue(cell.isAlive());

        cell.setState(5); // More than 3 neighbors should make an alive cell dead
        assertFalse(cell.isAlive());

        cell.setState(3); // 2 neighbors should keep an alive cell alive
        assertTrue(cell.isAlive());
    }
}