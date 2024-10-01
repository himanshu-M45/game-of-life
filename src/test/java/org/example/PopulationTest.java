package org.example;

import org.example.Exceptions.AllCellsAreDeadException;
import org.example.Exceptions.InvalidInitializationValueException;
import org.example.Exceptions.NoNewGenerationCanBeCreated;
import org.example.Enum.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PopulationTest {
    @Test
    void testPopulationInitializationWith0RowsAnd0Columns() {
        assertThrows(InvalidInitializationValueException.class, () -> new Population(0, 0, 0));
    }

    @Test
    void testPopulationInitializationWithNegativeRowsAndNegativeColumns() {
        assertThrows(InvalidInitializationValueException.class, () -> new Population(-10, -20, 0));
    }

    @Test
    void testPopulationWithNegativePercentage() {
        assertThrows(InvalidInitializationValueException.class, () -> new Population(10, 20, -10));
    }

    @Test
    void test200PercentAlivePopulationThrowsException() {
        assertThrows(InvalidInitializationValueException.class, () -> new Population(10, 20, 200));
    }

    @Test
    void test10PercentAlivePopulationWhichIs20AliveCells() {
        Population population = new Population(10, 20, 10);
        int expectedTotalAliveCells = 20;
        assertEquals(expectedTotalAliveCells, population.getTotalAliveCells());
    }

    @Test
    void test100PercentAlivePopulationWhichIs200AliveCells() {
        Population population = new Population(10, 20, 100);
        int expectedTotalAliveCells = 200;
        assertEquals(expectedTotalAliveCells, population.getTotalAliveCells());
    }

    // ------------------------- next generation tests -------------------------
    @Test
    void testEvaluateLife() {
        Population population = new Population(10, 20, 40);
        assertThrows(NoNewGenerationCanBeCreated.class, population::simulateGenerations);
    }

    @Test
    void testEvaluateLifeWithAllDeadCells() {
        Population population = new Population(10, 20, 0);
        assertThrows(AllCellsAreDeadException.class, population::simulateGenerations);
    }

    @Test
    void testEvaluateLifeWithAllAliveCells() {
        Population population = new Population(10, 20, 100);
        assertThrows(NoNewGenerationCanBeCreated.class, population::simulateGenerations);
    }

    @Test
    void testEvaluateLifeWithAllDeadCellsAndOneAliveCell() {
        Population population = new Population(10, 20, 0);
        population.getCellGrid().get(5).get(5).setState(State.ALIVE);

        assertThrows(AllCellsAreDeadException.class, population::simulateGenerations);
    }

    @Test
    void testEvaluateLifeWithAllAliveCellsAndOneDeadCell() {
        Population population = new Population(10, 20, 100);
        population.getCellGrid().get(5).get(5).setState(State.DEAD);

        assertThrows(NoNewGenerationCanBeCreated.class, population::simulateGenerations);
    }

    // ------------------------- start game tests -------------------------
    @Test
    void TestStartGameWith30PercentSeed() throws AllCellsAreDeadException {
        Population population = new Population(10, 20, 30);
        assertThrows(NoNewGenerationCanBeCreated.class, population::simulateGenerations);
    }

    @Test
    void TestStartGameWith20PercentSeed() throws AllCellsAreDeadException {
        Population population = new Population(10, 20, 40);
        assertThrows(NoNewGenerationCanBeCreated.class, population::simulateGenerations);
    }

    @Test
    void TestStartGameWith8PercentSeedThrowsException() throws AllCellsAreDeadException {
        Population population = new Population(10, 20, 1);
        assertThrows(AllCellsAreDeadException.class, population::simulateGenerations);
    }

    @Test
    void TestStartGameWith0PercentSeedThrowsException() throws AllCellsAreDeadException {
        Population population = new Population(5, 5, 0);
        assertThrows(AllCellsAreDeadException.class, population::simulateGenerations);
    }

    @Test
    void TestStartGameUntilAllCellsAreDeadWith100PercentSeed() {
        Population population = new Population(10, 20, 100);
        assertThrows(NoNewGenerationCanBeCreated.class, population::simulateGenerations);
    }

    @Test
    void TestStartGameUntilAllCellsAreDeadWith60PercentSeed() {
        Population population = new Population(10, 20, 60);
        assertThrows(NoNewGenerationCanBeCreated.class, population::simulateGenerations);
    }
}