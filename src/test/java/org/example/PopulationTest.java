package org.example;

import org.example.Exceptions.AllCellsAreDeadException;
import org.example.Exceptions.InvalidPercentageException;
import org.example.Exceptions.InvalidRowColumnValueException;
import org.example.Exceptions.NoNewGenerationCanBeCreated;
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

    @Test
    void testTotalPopulation() {
        Population population = new Population(10, 20);
        assertEquals(200, population.totalPopulation(10, 20));
    }

    @Test
    void tesPopulationGenerationAndPrintPopulation() {
        Population population = new Population(10, 20);
        assertDoesNotThrow(population::printPopulation);
    }

    @Test
    void testPopulationWithNegativePercentage() {
        Population population = new Population(10, 20);
        assertThrows(InvalidPercentageException.class, () -> population.seedPopulation(-10));
    }

    @Test
    void test200PercentAlivePopulationThrowsException() {
        Population population = new Population(10, 20);
        assertThrows(InvalidPercentageException.class, () -> population.seedPopulation(200));
    }

    @Test
    void test10PercentAlivePopulationWhichIs20AliveCells() {
        Population population = new Population(10, 20);
        population.seedPopulation(10);
        int expectedTotalAliveCells = 20;
        assertEquals(expectedTotalAliveCells, population.getTotalAliveCells());
        assertDoesNotThrow(population::printPopulation);
    }

    @Test
    void test100PercentAlivePopulationWhichIs200AliveCells() {
        Population population = new Population(10, 20);
        population.seedPopulation(100);
        int expectedTotalAliveCells = 200;
        assertEquals(expectedTotalAliveCells, population.getTotalAliveCells());
        assertDoesNotThrow(population::printPopulation);
    }

    @Test
    void testStartGameWith10GenerationsOfPopulationWith30PercentSeed() throws AllCellsAreDeadException {
        Population population = new Population(10, 20);
        population.seedPopulation(30);
        assertDoesNotThrow(() -> population.simulateGenerations(10));
    }

    @Test
    void testStartGameWith20GenerationsOfPopulationWith40PercentSeed() throws AllCellsAreDeadException {
        Population population = new Population(10, 20);
        population.seedPopulation(40);
        assertDoesNotThrow(() -> population.simulateGenerations(10));
    }

    @Test
    void testStartGameWith20GenerationsOfPopulationWith8PercentSeedThrowsException() throws AllCellsAreDeadException {
        Population population = new Population(10, 20);
        population.seedPopulation(1);
        assertThrows(AllCellsAreDeadException.class, () -> population.simulateGenerations(20));
    }

    @Test
    void testStartGameUntilAllCellsAreDeadWith0PercentSeedThrowsException() throws AllCellsAreDeadException {
        Population population = new Population(5, 5);
        population.seedPopulation(0);
        assertThrows(AllCellsAreDeadException.class, population::simulateGenerations);
    }

    @Test
    void testStartGameUntilAllCellsAreDeadWith100PercentSeed() {
        Population population = new Population(10, 20);
        population.seedPopulation(100);
        assertThrows(NoNewGenerationCanBeCreated.class, population::simulateGenerations);
    }

    @Test
    void testStartGameUntilAllCellsAreDeadWith60PercentSeed() {
        Population population = new Population(10, 20);
        population.seedPopulation(60);
        assertThrows(NoNewGenerationCanBeCreated.class, population::simulateGenerations);
    }
}