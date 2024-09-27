package org.example;

import org.example.enums.State;
import org.junit.jupiter.api.Test;

class GenerateNextGenerationTest {
    @Test
    void testEvaluateLife() {
        Population population = new Population(10, 20);
        population.seedPopulation(40);
        population.printPopulation(); // For testing purpose only

        GenerateNextGeneration generateNextGeneration = new GenerateNextGeneration();
        generateNextGeneration.evaluateNextGeneration(population.getCellGrid());
        population.printPopulation(); // For testing purpose only
    }

    @Test
    void testEvaluateLifeWithAllDeadCells() {
        Population population = new Population(10, 20);
        population.seedPopulation(0);
        population.printPopulation(); // For testing purpose only

        GenerateNextGeneration generateNextGeneration = new GenerateNextGeneration();
        generateNextGeneration.evaluateNextGeneration(population.getCellGrid());
        population.printPopulation(); // For testing purpose only
    }

    @Test
    void testEvaluateLifeWithAllAliveCells() {
        Population population = new Population(10, 20);
        population.seedPopulation(100);
        population.printPopulation(); // For testing purpose only

        GenerateNextGeneration generateNextGeneration = new GenerateNextGeneration();
        generateNextGeneration.evaluateNextGeneration(population.getCellGrid());
        population.printPopulation(); // For testing purpose only
    }

    @Test
    void testEvaluateLifeWithAllDeadCellsAndOneAliveCell() {
        Population population = new Population(10, 20);
        population.seedPopulation(0);
        population.getCellGrid().get(5).get(5).setState(State.ALIVE);
        population.printPopulation(); // For testing purpose only

        GenerateNextGeneration generateNextGeneration = new GenerateNextGeneration();
        generateNextGeneration.evaluateNextGeneration(population.getCellGrid());
        population.printPopulation(); // For testing purpose only
    }

}