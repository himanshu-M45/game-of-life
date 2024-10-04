package org.example.Entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    @Test
    void TestCellInitialization() {
        Cell cell = new Cell(4,5);
        assertFalse(cell.isAlive());
    }

    @Test
    void TestSetCellStateToAlive() {
        Cell cell = new Cell(2,5);
        assertFalse(cell.isAlive());
        cell.setInitialAlive();
        assertTrue(cell.isAlive());
    }

    @Test
    void TestSetCellStateToDeadFromAlive() {
        Cell cell = new Cell(3,4);
        cell.setInitialAlive();
        cell.setCellState();
        assertFalse(cell.isAlive());
    }

    @Test
    void TestSetCellStateToDeadFromAliveWithMoreThan3Neighbors() {
        Cell cell = new Cell(4,7);
        cell.setInitialAlive();
        cell.setCellState(); // More than 3 neighbors should make an alive cell dead
        assertFalse(cell.isAlive());
    }

//    @Test
//    void TestCellLifeThroughDifferentNumberOfNeighbours() {
//        Cell cell = new Cell(5, 8);
//        cell.setInitialAlive();
//
//        cell.setCellState(); // 2 neighbors should keep an alive cell alive
//        assertTrue(cell.isAlive());
//
//        cell.setCellState(); // More than 3 neighbors should make an alive cell dead
//        assertFalse(cell.isAlive());
//
//        cell.setCellState(); // 2 neighbors should keep an alive cell alive
//        assertTrue(cell.isAlive());
//    }

    @Test
    void TestLocationCoordinates() {
        Cell cell = new Cell(3,5);
        Location location = cell.getLocation();
        assertTrue(location.isSameLocation(3, 5));
    }
}