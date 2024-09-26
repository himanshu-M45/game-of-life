package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Grid {
    private final ArrayList<ArrayList<Cell>> grid;
    private final int rows;
    private final int columns;

    public Grid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new ArrayList<>(rows);
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            ArrayList<Cell> row = new ArrayList<>(columns);
            for (int j = 0; j < columns; j++) {
                row.add(new Cell(random.nextInt(2)));
            }
            grid.add(row);
        }
    }

    public void printGrid() { // for testing purpose only
        for (ArrayList<Cell> row : grid) {
            for (Cell cell : row) {
                System.out.print(cell.value + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------");
    }
}
