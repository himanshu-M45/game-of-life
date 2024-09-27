package org.example;

import org.junit.jupiter.api.Test;

class EvaluateLifeTest {
    @Test
    void testEvaluateLife() {
        Population population = new Population(10, 20);
        population.generateInitialPopulation(40);
        population.printPopulation(); // For testing purpose only

        EvaluateLife evaluateLife = new EvaluateLife();
        evaluateLife.evaluateNextGeneration(population.getCellGrid());
        population.printPopulation(); // For testing purpose only
    }

}