package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {
    Grid grid = new Grid(10, 20);

    @Test
    void tesGridGenerationAndPrintGrid() {
        assertDoesNotThrow(grid::printGrid);
    }

}