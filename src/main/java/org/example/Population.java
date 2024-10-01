package org.example;

import org.example.Exceptions.AllCellsAreDeadException;
import org.example.Exceptions.InvalidInitializationValueException;
import org.example.Exceptions.NoNewGenerationCanBeCreated;
import org.example.Service.IOOperation;

import java.util.*;

public class Population {
    private final List<List<Cell>> cellGrid;
    private final int rows;
    private final int columns;

    public Population(int rows, int columns, int percentageAlive) {
        if (rows <= 0 || columns <= 0 || percentageAlive < 0 || percentageAlive > 100) {
            throw new InvalidInitializationValueException("Rows and columns should be greater than 0");
        }
        this.rows = rows;
        this.columns = columns;
        this.cellGrid = new ArrayList<>(rows);
        // generate initial population with percentageAlive
        int totalAliveCells = (rows * columns) * percentageAlive / 100;
        for (int i = 0; i < rows; i++) { // initialise all cells and set initial alive cells
            List<Cell> row = new ArrayList<>(columns);
            for (int j = 0; j < columns; j++) {
                Cell cell = new Cell();
                row.add(cell);
                if (totalAliveCells > 0 && Math.random() < (double) totalAliveCells / ((rows * columns) - (i * columns + j))) {
                    cell.setInitialAlive();
                    totalAliveCells--;
                }
            }
            cellGrid.add(row);
        }
    }

    public void simulateGenerations() {
        Queue<Integer> queue = new ArrayDeque<>(5);
        while (getTotalAliveCells() != 0) {
            IOOperation.printPopulation(cellGrid);
            evaluateNextGeneration();

            if (queue.size() == 5) {
                queue.poll(); // Remove the front element
            }
            queue.offer(getTotalAliveCells()); // Add the new element to the back

            // Check if all elements in the queue are the same
            if (queue.size() == 5 && queue.stream().distinct().count() == 1) {
                throw new NoNewGenerationCanBeCreated("No new generation can be created. Exiting the simulation.");
            }
        }
        throw new AllCellsAreDeadException("All cells are dead. Exiting the simulation.");
    }

    public void evaluateNextGeneration() {
        for (int row = 0; row < cellGrid.size(); row++) {
            for (int column = 0; column < cellGrid.get(row).size(); column++) {
                Cell cell = cellGrid.get(row).get(column); // extract cell from the grid
                cell.setState(getCountOfNeighbouringCells(row, column)); // set the state of the cell
            }
        }
    }

    private int getCountOfNeighbouringCells(int row, int column) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue; // Skip the cell itself
                int newRow = row + i;
                int newColumn = column + j;
                if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns) {
                    if (cellGrid.get(newRow).get(newColumn).isAlive()) {
                        count++;
                    }
                }
            }
        }
        return count;
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

    public List<List<Cell>> getCellGrid() {
        return cellGrid;
    }

}