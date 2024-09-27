package org.example;

import org.example.Exceptions.InvalidRowColumnValueException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PopulationTest {
    @Test
    void testPopulationInitializationWith0RowsAnd0Columns() {
        assertThrows(InvalidRowColumnValueException.class, () -> new Population(0, 0));
    }

    @Test
    void testPopulationInitializationWithNegativeRowsAndNegativeColumns() {
        assertThrows(InvalidRowColumnValueException.class, () -> new Population(-10, -20));
    }

    // initialize population at global level to avoid repetition and to further regenerate population
    Population population = new Population(10, 20);

    @Test
    void testPopulationInitialization() {
        assertEquals(10, population.getRows());
        assertEquals(20, population.getColumns());
    }

    @Test
    void testTotalPopulation() {
        assertEquals(200, population.getRows() * population.getColumns());
    }

    @Test
    void tesPopulationGenerationAndPrintPopulation() {
        assertDoesNotThrow(population::printPopulation);
    }

    @Test
    void test10PercentAlivePopulationWhichIs20AliveCells() {
        population.generateInitialPopulation(10);
        int expectedTotalAliveCells = 20;
        assertEquals(expectedTotalAliveCells, population.getTotalAliveCells());
        assertDoesNotThrow(population::printPopulation);
    }

    @Test
    void test100PercentAlivePopulationWhichIs200AliveCells() {
        population.generateInitialPopulation(100);
        int expectedTotalAliveCells = 200;
        assertEquals(expectedTotalAliveCells, population.getTotalAliveCells());
        assertDoesNotThrow(population::printPopulation);
    }
}