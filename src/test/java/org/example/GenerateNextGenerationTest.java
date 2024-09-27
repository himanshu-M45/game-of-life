package org.example;

import org.example.Exceptions.AllCellsAreDeadException;
import org.example.Exceptions.NoNewGenerationCanBeCreated;
import org.example.enums.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GenerateNextGenerationTest {
    @Test
    void testEvaluateLife() {
        Population population = new Population(10, 20);
        population.seedPopulation(40);

        assertDoesNotThrow(() -> population.simulateGenerations(2));
    }

    @Test
    void testEvaluateLifeWithAllDeadCells() {
        Population population = new Population(10, 20);
        population.seedPopulation(0);

        assertThrows(AllCellsAreDeadException.class, () -> population.simulateGenerations(2));
    }

    @Test
    void testEvaluateLifeWithAllAliveCells() {
        Population population = new Population(10, 20);
        population.seedPopulation(100);

        assertDoesNotThrow(() -> population.simulateGenerations(2));
    }

    @Test
    void testEvaluateLifeWithAllDeadCellsAndOneAliveCell() {
        Population population = new Population(10, 20);
        population.seedPopulation(0);
        population.getCellGrid().get(5).get(5).setState(State.ALIVE);

        assertThrows(AllCellsAreDeadException.class, () -> population.simulateGenerations(1));
    }

    @Test
    void testEvaluateLifeWithAllAliveCellsAndOneDeadCell() {
        Population population = new Population(10, 20);
        population.seedPopulation(100);
        population.getCellGrid().get(5).get(5).setState(State.DEAD);

        assertThrows(NoNewGenerationCanBeCreated.class, population::simulateGenerations);
    }
}