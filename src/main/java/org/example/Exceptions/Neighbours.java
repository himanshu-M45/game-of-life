package org.example.Exceptions;

import org.example.Cell;

import java.util.List;

public class Neighbours {
    public int getNeighbours(List<List<Cell>> cellGrid, Cell cell) {
        int[] coordinates = findCellCoordinates(cellGrid, cell);
        int row = coordinates[0];
        int column = coordinates[1];
        int count = 0;
        // Check all 8 possible neighbors and count alive ones
        if (row - 1 >= 0 && column - 1 >= 0) {
            if (cellGrid.get(row - 1).get(column - 1).isAlive()) {
                count++;
            }
        }
        if (row - 1 >= 0) {
            if (cellGrid.get(row - 1).get(column).isAlive()) {
                count++;
            }
        }
        if (row - 1 >= 0 && column + 1 < cellGrid.get(row).size()) {
            if (cellGrid.get(row - 1).get(column + 1).isAlive()) {
                count++;
            }
        }
        if (column - 1 >= 0) {
            if (cellGrid.get(row).get(column - 1).isAlive()) {
                count++;
            }
        }
        if (column + 1 < cellGrid.get(row).size()) {
            if (cellGrid.get(row).get(column + 1).isAlive()) {
                count++;
            }
        }
        if (row + 1 < cellGrid.size() && column - 1 >= 0) {
            if (cellGrid.get(row + 1).get(column - 1).isAlive()) {
                count++;
            }
        }
        if (row + 1 < cellGrid.size()) {
            if (cellGrid.get(row + 1).get(column).isAlive()) {
                count++;
            }
        }
        if (row + 1 < cellGrid.size() && column + 1 < cellGrid.get(row).size()) {
            if (cellGrid.get(row + 1).get(column + 1).isAlive()) {
                count++;
            }
        }
        return count;
    }

    private int[] findCellCoordinates(List<List<Cell>> cellGrid, Cell cell) {
        for (int i = 0; i < cellGrid.size(); i++) {
            List<Cell> rowList = cellGrid.get(i);
            for (int j = 0; j < rowList.size(); j++) {
                if (rowList.get(j) == cell) {
                    return new int[]{i, j};
                }
            }
        }
        throw new CellNotFoundException("Cell not found in the grid");
    }


}
