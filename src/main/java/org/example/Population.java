package org.example;

import org.example.Exceptions.AllCellsAreDeadException;
import org.example.Exceptions.InvalidPercentageException;
import org.example.Exceptions.InvalidRowColumnValueException;
import org.example.Exceptions.NoNewGenerationCanBeCreated;
import org.example.enums.State;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Population {
    private final List<List<Cell>> cellGrid;
    private final int rows;
    private final int columns;

    public Population(int rows, int columns) {
        if (rows <= 0 || columns <= 0) {
            throw new InvalidRowColumnValueException("Rows and columns should be greater than 0");
        }
        this.rows = rows;
        this.columns = columns;
        this.cellGrid = new ArrayList<>(rows);
        for (int i = 0; i < rows; i++) { // initialise all cells with DEAD state
            List<Cell> row = new ArrayList<>(columns);
            for (int j = 0; j < columns; j++) {
                row.add(new Cell());
            }
            cellGrid.add(row);
        }
    }

    public void printPopulation() { // for testing purpose only
        for (List<Cell> row : cellGrid) {
            for (Cell cell : row) {
                cell.printValue();
            }
            System.out.println();
        }
        System.out.println();
    }

    public void seedPopulation(int percentageAlive) {
        // generate initial population with percentageAlive
        if (percentageAlive < 0 || percentageAlive > 100) {
            throw new InvalidPercentageException("Percentage should be between 0 and 100");
        }
        int totalAliveCells = (totalPopulation(rows, columns) * percentageAlive) / 100;
        while (totalAliveCells > 0) {
            int randomRow = (int) (Math.random() * rows);
            int randomColumn = (int) (Math.random() * columns);
            Cell cell = cellGrid.get(randomRow).get(randomColumn);
            if (!cell.isAlive()) {
                cell.setState(State.ALIVE);
                totalAliveCells--;
            }
        }
    }

    public int totalPopulation(int rows, int columns) {
        return rows * columns;
    }

    public void simulateGenerations(int generations) {
        GenerateNextGeneration generateNextGeneration = new GenerateNextGeneration();
        for (int i = 0; i < generations; i++) {
            printPopulation();
            generateNextGeneration.evaluateNextGeneration(cellGrid);
            if (getTotalAliveCells() == 0) {
                throw new AllCellsAreDeadException("All cells are dead. Exiting the simulation.");
            }
        }
    }

    public void simulateGenerations() {
        GenerateNextGeneration generateNextGeneration = new GenerateNextGeneration();
        Queue<Integer> queue = new ArrayDeque<>(3);
        while (getTotalAliveCells() != 0) {
            printPopulation();
            generateNextGeneration.evaluateNextGeneration(cellGrid);

            if (queue.size() == 3) {
                queue.poll(); // Remove the front element
            }
            queue.offer(getTotalAliveCells()); // Add the new element to the back

            // Check if all elements in the queue are the same
            if (queue.size() == 3 && queue.stream().distinct().count() == 1) {
                throw new NoNewGenerationCanBeCreated("No new generation can be created. Exiting the simulation.");
            }
        }
        throw new AllCellsAreDeadException("All cells are dead. Exiting the simulation.");
    }

    public int getTotalAliveCells() {
        int totalAliveCells = 0;
        for (List<Cell> row : cellGrid) {
            for (Cell cell : row) {
                if (cell.isAlive()) {
                    totalAliveCells++;
                }
            }
        }
        return totalAliveCells;
    }

    public void setCellState(int row, int column, State state) {
        if (row >= 0 && row < rows && column >= 0 && column < columns) {
            cellGrid.get(row).get(column).setState(state);
            return;
        }
        throw new IndexOutOfBoundsException("Invalid row or column value");
    }
}