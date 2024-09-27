package org.example;

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

}