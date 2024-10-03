package org.example.Entities;

import org.example.Exceptions.AllCellsAreDeadException;
import org.example.Exceptions.InvalidInitializationValueException;
import org.example.Exceptions.NoNewGenerationCanBeCreated;
import org.example.Service.IOOperation;

import java.util.*;

public class Population {
    private final Cell[][] cellGrid;
    private final int rows;
    private final int columns;

    public Population(int rows, int columns, int percentageAlive) {
        if (rows <= 0 || columns <= 0 || percentageAlive < 0 || percentageAlive > 100) {
            throw new InvalidInitializationValueException("Rows and columns should be greater than 0");
        }
        this.rows = rows;
        this.columns = columns;
        this.cellGrid = new Cell[rows][columns];
        // generate initial population with percentageAlive
        generateInitialPopulation(percentageAlive);
    }

    private void generateInitialPopulation(int percentageAlive) {
        int totalAliveCells = (rows * columns) * percentageAlive / 100;
        for (int i = 0; i < rows; i++) { // initialise all cells and set initial alive cells
            for (int j = 0; j < columns; j++) {
                cellGrid[i][j] = new Cell();
                if (totalAliveCells > 0 && Math.random() < (double) totalAliveCells / ((rows * columns) - (i * columns + j))) {
                    cellGrid[i][j].setInitialAlive();
                    totalAliveCells--;
                }
            }
        }
    }

    public void simulateGenerations() { // start game
        Queue<Integer> queue = new ArrayDeque<>(5);
        while (getTotalAliveCells() != 0) {
            IOOperation.printPopulation(cellGrid);
            evaluateNextGeneration(); // generate next generation

            // Preventing infinite loop by checks for duplicate generations.
            checkForDuplicateGenerations(queue);
        }
        throw new AllCellsAreDeadException("All cells are dead. Exiting the simulation.");
    }

    private void checkForDuplicateGenerations(Queue<Integer> queue) {
        if (queue.size() == 5) {
            queue.poll(); // Remove the front element
        }
        queue.offer(getTotalAliveCells()); // Add the new element to the back

        // Check if all elements in the queue are the same
        if (queue.size() == 5 && queue.stream().distinct().count() == 1) {
            throw new NoNewGenerationCanBeCreated("No new generation can be created. Exiting the simulation.");
        }
    }

    public void evaluateNextGeneration() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Cell cell = cellGrid[row][col]; // extract cell from the grid
                cell.setCellState(getCountOfNeighbouringCells(row, col)); // set the state of the cell
            }
        }
    }

    private int getCountOfNeighbouringCells(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < rows && j >= 0 && j < columns && !(i == row && j == col)) {
                    if (cellGrid[i][j].isAlive()) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public int getTotalAliveCells() {
        int totalAliveCells = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (cellGrid[row][col].isAlive()) {
                    totalAliveCells++;
                }
            }
        }
        return totalAliveCells;
    }
}