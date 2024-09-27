package org.example;

import org.example.Exceptions.AllCellsAreDeadException;
import org.example.Exceptions.InvalidPercentageException;
import org.example.Exceptions.InvalidRowColumnValueException;
import org.example.enums.State;

import java.util.ArrayList;
import java.util.List;

public class Population {
    private List<List<Cell>> cellGrid;
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

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public List<List<Cell>> getCellGrid() {
        return cellGrid;
    }

    public void seedPopulation(int percentageAlive) {
        // generate initial population with percentageAlive
        if (percentageAlive <= 0 || percentageAlive > 100) {
            throw new InvalidPercentageException("Percentage should be between 1 and 100");
        }
        int totalCells = rows * columns;
        int totalAliveCells = (totalCells * percentageAlive) / 100;
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
    
    public void simulateGenerations(int generations) {
        for (int i = 0; i < generations; i++) {
            evaluateNextGeneration();
            printPopulation();
            if (getTotalAliveCells() == 0) {
                throw new AllCellsAreDeadException("All cells are dead. Exiting the simulation.");
            }
        }
    }

    public void simulateGenerations() {
        while (getTotalAliveCells() != 0) {
            evaluateNextGeneration();
            printPopulation();
        }
        throw new AllCellsAreDeadException("All cells are dead. Exiting the simulation.");
    }

    private void evaluateNextGeneration() {
        // evaluate next generation
        GenerateNextGeneration generateNextGeneration = new GenerateNextGeneration();
        generateNextGeneration.evaluateNextGeneration(cellGrid);
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
}