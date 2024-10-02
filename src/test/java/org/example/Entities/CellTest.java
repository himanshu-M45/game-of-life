package org.example.Entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    @Test
    void TestCellInitialization() {
        Cell cell = new Cell();
        assertFalse(cell.isAlive());
    }

    @Test
    void TestSetCellStateToAlive() {
        Cell cell = new Cell();
        cell.setCellState(3); // 3 neighbors should make a dead cell alive
        assertTrue(cell.isAlive());
    }

    @Test
    void TestSetCellStateToDeadFromAlive() {
        Cell cell = new Cell();
        cell.setInitialAlive();
        cell.setCellState(1); // Less than 2 neighbors should make an alive cell dead
        assertFalse(cell.isAlive());
    }

    @Test
    void TestSetCellStateToDeadFromAliveWithMoreThan3Neighbors() {
        Cell cell = new Cell();
        cell.setInitialAlive();
        cell.setCellState(4); // More than 3 neighbors should make an alive cell dead
        assertFalse(cell.isAlive());
    }

    @Test
    void TestCellLifeThroughDifferentNumberOfNeighbours() {
        Cell cell = new Cell();
        cell.setInitialAlive();

        cell.setCellState(2); // 2 neighbors should keep an alive cell alive
        assertTrue(cell.isAlive());

        cell.setCellState(5); // More than 3 neighbors should make an alive cell dead
        assertFalse(cell.isAlive());

        cell.setCellState(3); // 2 neighbors should keep an alive cell alive
        assertTrue(cell.isAlive());
    }
}