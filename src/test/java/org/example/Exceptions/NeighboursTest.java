package org.example.Exceptions;

import org.example.Cell;
import org.example.Population;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NeighboursTest {
    static Population population = new Population(10, 20);

    @BeforeAll
    static void setUp() {
        population.generateInitialPopulation(15);
    }

    @Test
    void testGetNeighboursOfProvidedCell() {
        List<List<Cell>> cellGrid = population.getCellGrid();
        Cell cell = cellGrid.get(4).get(10);
        Neighbours neighbours = new Neighbours();

        int aliveNeighbours = neighbours.getNeighbours(cellGrid, cell);
        population.printPopulation(); // For testing purpose only

        assertTrue(aliveNeighbours >=0 && aliveNeighbours <= 8);
    }

    @Test
    void testGetNeighboursOfProvidedOutOfBoundCell() {
        List<List<Cell>> cellGrid = population.getCellGrid();
        Cell cell = cellGrid.get(7).get(10);
        Neighbours neighbours = new Neighbours();

        int aliveNeighbours = neighbours.getNeighbours(cellGrid, cell);
        population.printPopulation(); // For testing purpose only

        assertTrue(aliveNeighbours >=0 && aliveNeighbours <= 8);
    }
}